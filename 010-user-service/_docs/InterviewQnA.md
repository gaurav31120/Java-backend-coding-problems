# Problem 10 — Interview Questions & Answers

## 1. What is the Service layer in Spring Boot?

The Service layer contains application or business logic.

It sits between the Controller and other components such as repositories.

A common flow is:

```text
Controller → Service → Repository
```

## 2. Why should business logic not be placed directly inside the Controller?

Controllers should mainly handle HTTP-related responsibilities.

If business logic is placed directly inside Controllers, they can become large and difficult to maintain.

Separating the logic into Services improves organization, reuse, and testability.

## 3. What does `@Service` do?

`@Service` tells Spring that a class is a component that should be managed as a Spring Bean.

Example:

```java
@Service
public class UserService {
}
```

## 4. Is `@Service` fundamentally different from `@Component`?

`@Service` is a specialized form of `@Component`.

Both allow Spring to discover and manage the class as a Bean.

`@Service` communicates the intended role of the class more clearly.

## 5. How does the Controller receive `UserService`?

The Controller uses constructor injection:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring resolves the `UserService` Bean and supplies it to the constructor.

## 6. What is constructor injection?

Constructor injection means providing a class's required dependency through its constructor.

Example:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

## 7. Why is constructor injection preferred in Spring?

Constructor injection makes dependencies explicit.

It also makes required dependencies available when the object is created and generally makes the class easier to test.

## 8. What is separation of responsibilities?

Separation of responsibilities means giving different classes different jobs.

For example:

```text
Controller → HTTP handling

Service → Business logic

Repository → Database access
```

## 9. What happens when `/users/service` is called?

The request reaches `UserController`.

The Controller calls:

```java
userService.getMessage();
```

The Service returns:

```text
User service is working
```

The Controller sends that result back to the client.

## 10. Should the Controller create `UserService` using `new`?

Normally, no.

Instead of:

```java
UserService userService = new UserService();
```

Spring should manage the Service and inject it into the Controller.

This is one of the benefits of Dependency Injection.

## 11. What is the relationship between Dependency Injection and the Service layer?

The Service layer defines reusable business logic, while Dependency Injection provides the required Service object to other components.

For example:

```text
UserController
      ↓
Dependency Injection
      ↓
UserService
```

## 12. Can multiple Controllers use the same Service?

Yes.

A Spring Service Bean can be injected into multiple Controllers or other components when appropriate.

This avoids duplicating the same business logic.

## 13. What is the typical layered architecture of a Spring Boot application?

A common architecture is:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Each layer has a specific responsibility.

## 14. What is the main lesson from this problem?

The main lesson is to avoid putting all application logic inside Controllers.

Use a Service layer to separate business logic from HTTP handling.

The basic mental model is:

```text
Controller → receives request

Service → performs business logic
```