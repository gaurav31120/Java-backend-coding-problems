# Problem 11 — Interview Questions & Answers

## 1. What is the purpose of the Controller layer?

The Controller handles HTTP-related responsibilities such as receiving requests, mapping URLs, and returning responses.

It should generally delegate business logic to the Service layer.

## 2. What is the purpose of the Service layer?

The Service layer contains application or business logic.

It sits between the Controller and lower-level components such as repositories.

A common flow is:

```text
Controller → Service → Repository
```

## 3. Why separate Controller and Service?

Separation makes each class responsible for a specific job.

The Controller handles HTTP communication, while the Service handles business logic.

This makes the application easier to maintain, test, and extend.

## 4. What does `@Service` do?

`@Service` tells Spring that a class should be managed as a Spring Bean.

Example:

```java
@Service
public class UserService {
}
```

## 5. How does Spring inject `UserService` into `UserController`?

The Controller uses constructor injection:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring finds the `UserService` Bean and supplies it to the constructor.

## 6. What is constructor injection?

Constructor injection means providing a class's required dependency through its constructor.

Example:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

## 7. Why is constructor injection useful?

Constructor injection makes dependencies explicit.

It also makes required dependencies available when the object is created and generally makes classes easier to test.

## 8. Should business logic be written inside the Controller?

Generally, no.

Controllers should focus on HTTP handling and delegate business logic to Services.

For example:

```java
return userService.getUserProfile();
```

is preferable to placing the business logic directly in the Controller.

## 9. What is separation of responsibilities?

It means giving different parts of the application different responsibilities.

For example:

```text
Controller → HTTP handling

Service → Business logic

Repository → Database access
```

## 10. Can multiple Controllers use the same Service?

Yes.

A Spring Service Bean can be injected into multiple Controllers when appropriate.

This allows business logic to be reused instead of duplicated.

## 11. What is the typical layered architecture in Spring Boot?

A common architecture is:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

Each layer has a specific responsibility.

## 12. What happens when `/users/profile` is called?

The request reaches `UserController`.

The Controller calls:

```java
userService.getUserProfile();
```

The Service returns:

```text
User profile from service
```

The Controller returns that result to the client.

## 13. Should we create the Service using `new` inside the Controller?

Normally, no.

Instead of:

```java
UserService userService = new UserService();
```

Spring should manage the Service and inject it into the Controller.

This is one of the benefits of Dependency Injection.

## 14. What is the difference between Dependency Injection and Layered Architecture?

Dependency Injection is the mechanism Spring uses to provide required objects to classes.

Layered Architecture is the design that separates responsibilities into layers such as Controller, Service, and Repository.

They work together but are not the same thing.

## 15. What is the main lesson from this problem?

The main lesson is to separate HTTP handling from business logic.

Use:

```text
Controller → handles the request

Service → performs the business logic
```

This creates a cleaner and more maintainable backend architecture.