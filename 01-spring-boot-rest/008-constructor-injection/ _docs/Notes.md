# Problem 8 — Notes

## 1. What Is Dependency Injection?

Dependency Injection (DI) is a design technique where a class receives the objects it depends on instead of creating those objects itself.

Example:

```text
UserController
      ↓
depends on
      ↓
UserService
```

Instead of the Controller creating the Service:

```java
new UserService();
```

Spring creates the Service and provides it to the Controller.

---

## 2. What Is a Dependency?

A dependency is an object that another class needs to perform its work.

In our example:

```text
UserController
      ↓
needs
      ↓
UserService
```

Therefore, `UserService` is a dependency of `UserController`.

---

## 3. What Is Dependency Injection?

Dependency Injection means providing a class with its required dependency from outside the class.

Without DI:

```java
UserService userService = new UserService();
```

The Controller creates the dependency itself.

With DI:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring provides the `UserService` object.

---

## 4. What Is Constructor Injection?

Constructor Injection means providing a dependency through the class constructor.

Example:

```java
private UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

The dependency enters the Controller through its constructor.

---

## 5. Why Use Constructor Injection?

Constructor Injection has several advantages:

- Dependencies are explicit.
- Required dependencies can be enforced when the object is created.
- Classes are easier to test.
- Dependencies can be stored in `final` fields.
- The class does not need to create its dependencies itself.

---

## 6. What Is @Service?

`@Service` tells Spring that a class is a Spring-managed component that typically contains application/business logic.

Example:

```java
@Service
public class UserService {

    public String userService() {
        return "User service is working";
    }
}
```

Spring detects the class and creates a Spring Bean for it.

---

## 7. What Is a Spring Bean?

A Spring Bean is an object that is created and managed by the Spring container.

In our example:

```text
@Service
UserService
     ↓
Spring creates object
     ↓
Spring manages object
```

The Controller can then receive that object through dependency injection.

---

## 8. What Is the Spring IoC Container?

The Spring IoC container is responsible for creating, configuring, and managing Spring Beans.

Conceptually:

```text
Spring IoC Container
        │
        ├── UserService object
        │
        └── UserController object
                │
                └── UserService dependency
```

---

## 9. What Does Inversion of Control Mean?

In traditional Java code, the class often controls the creation of its dependencies.

Example:

```java
UserService service = new UserService();
```

With Spring, object creation and dependency management are handled by the Spring container.

Therefore, control over object creation is moved from application code to the framework.

This is called Inversion of Control (IoC).

---

## 10. Constructor Injection Flow

Our application follows this flow:

```text
Spring starts
     ↓
Finds @Service
     ↓
Creates UserService Bean
     ↓
Finds UserController
     ↓
Sees UserService constructor dependency
     ↓
Provides UserService to UserController
     ↓
UserController is ready
```

---

## 11. Controller-Service Separation

The Controller should mainly handle HTTP/API concerns.

The Service should contain application/business logic.

Example:

```text
Controller
    ↓
HTTP/API handling

Service
    ↓
Business/application logic
```

This separation makes the application easier to maintain and test.

---

## 12. Our Controller

```java
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/service")
    public String getServiceMessage() {
        return userService.userService();
    }
}
```

The Controller does not create `UserService`.

Instead, it receives it through the constructor.

---

## 13. Our Service

```java
@Service
public class UserService {

    public String userService() {
        return "User service is working";
    }
}
```

The Service contains the functionality used by the Controller.

---

## 14. Dependency Flow

```text
Client
   ↓
UserController
   ↓
UserService
   ↓
Response
```

Spring manages the relationship:

```text
Spring
  ↓
UserService Bean
  ↓
injects into
  ↓
UserController
```

---

## 15. Constructor Injection vs Manual Object Creation

### Manual Creation

```java
public UserController() {
    this.userService = new UserService();
}
```

The Controller is responsible for creating the dependency.

### Constructor Injection

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

The dependency is provided from outside.

Spring can manage the dependency lifecycle.

---

## 16. Key Takeaways

Remember:

```text
Dependency
    ↓
Object required by another class

Dependency Injection
    ↓
Provide the dependency from outside

Constructor Injection
    ↓
Provide dependency through constructor

@Service
    ↓
Spring-managed service component

Spring IoC Container
    ↓
Creates and manages Beans
```

The most important idea:

> Constructor Injection allows a class to receive its required dependencies instead of creating them itself.