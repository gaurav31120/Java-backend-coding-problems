# Problem 8 — Interview Q&A

## 1. What is Dependency Injection?

Dependency Injection is a design technique where an object receives its dependencies from an external source instead of creating them itself.

Example:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Here, `UserController` receives `UserService` instead of creating it with `new`.

---

## 2. What is a dependency?

A dependency is an object that another object needs to perform its work.

In our example:

```text
UserController
      ↓
UserService
```

`UserController` depends on `UserService`.

---

## 3. What is Constructor Injection?

Constructor Injection is a form of Dependency Injection where dependencies are provided through the constructor.

Example:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

---

## 4. Why is Constructor Injection preferred?

Constructor Injection makes dependencies explicit and allows required dependencies to be provided when the object is created.

It also improves:

- Testability
- Immutability
- Readability
- Maintainability

---

## 5. What is @Service?

`@Service` marks a class as a Spring-managed component, typically used for application or business logic.

Example:

```java
@Service
public class UserService {
}
```

Spring detects the class and creates a Bean for it.

---

## 6. What is a Spring Bean?

A Spring Bean is an object that is created and managed by the Spring IoC container.

For example:

```java
@Service
public class UserService {
}
```

causes Spring to manage an instance of `UserService`.

---

## 7. What is IoC?

IoC stands for Inversion of Control.

It means that the responsibility for creating and managing objects is transferred from application code to a framework such as Spring.

Without Spring:

```java
UserService service = new UserService();
```

With Spring, the framework manages the object.

---

## 8. What is the Spring IoC container?

The Spring IoC container is responsible for creating, configuring, and managing Spring Beans and their dependencies.

Conceptually:

```text
Spring Container
      ↓
Creates Beans
      ↓
Resolves dependencies
      ↓
Injects dependencies
```

---

## 9. What is the difference between Dependency Injection and Inversion of Control?

IoC is the broader principle where control over object creation and management is transferred to a framework.

Dependency Injection is one technique used to implement IoC.

Simple relationship:

```text
IoC
 ↓
Dependency Injection
 ↓
Constructor Injection
```

---

## 10. Why should we avoid `new UserService()` inside the Controller?

If the Controller creates the Service itself:

```java
UserService userService = new UserService();
```

the Controller becomes tightly coupled to the creation of that dependency.

With Dependency Injection:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring manages the dependency.

---

## 11. What is loose coupling?

Loose coupling means classes depend less on the concrete creation or implementation details of other classes.

For example:

```text
Controller
    ↓
receives Service
```

instead of:

```text
Controller
    ↓
creates Service
```

Dependency Injection helps achieve loose coupling.

---

## 12. How does Spring know that UserService should be managed?

Because `UserService` is annotated with:

```java
@Service
```

Spring's component scanning detects the class and registers it as a Spring Bean.

---

## 13. How does Spring inject UserService into UserController?

Spring detects:

```java
@Service
public class UserService {
}
```

and creates a `UserService` Bean.

It also detects the Controller constructor:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring resolves the `UserService` dependency and provides it to the Controller.

---

## 14. Can Constructor Injection use multiple dependencies?

Yes.

Example:

```java
public UserController(
        UserService userService,
        AuditService auditService) {

    this.userService = userService;
    this.auditService = auditService;
}
```

Spring can inject multiple required dependencies through the constructor.

---

## 15. Why are constructor-injected dependencies often declared final?

If a dependency should not change after construction, it can be declared:

```java
private final UserService userService;
```

and assigned once:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

This makes the dependency reference immutable after construction.

---

## 16. What is the difference between Constructor Injection and Field Injection?

### Constructor Injection

```java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

### Field Injection

```java
@Autowired
private UserService userService;
```

Constructor Injection is generally preferred because dependencies are explicit and the class can be instantiated more easily in tests.

---

## 17. What is the responsibility of the Controller and Service?

Controller:

```text
HTTP/API concerns
```

Service:

```text
Business/application logic
```

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

---

## 18. Explain the complete flow of Problem 8.

A request arrives:

```text
GET /users/service
```

Spring routes it to `UserController`.

The Controller uses its injected `UserService`.

The Controller calls:

```java
userService.userService();
```

The Service returns:

```text
User service is working
```

The Controller returns the result to the client.

---

## 19. What happens if Spring cannot find a UserService Bean?

If Spring cannot resolve the required `UserService` dependency, the application context cannot properly create the `UserController`.

The application will typically fail during startup with a dependency-related error.

---

## 20. What is the main lesson from Problem 8?

The main lesson is:

> A class should receive its dependencies instead of creating them itself.

In Spring, Constructor Injection provides a clean way to connect components while allowing the Spring IoC container to manage their objects.