# Problem 10 — User Service

## 1. Main Idea

A Spring application can separate responsibilities into different layers.

Instead of putting business logic directly inside the Controller, we can place it inside a Service.

The basic flow is:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Business Logic
```

## 2. What Is a Service Layer?

The Service layer contains application or business logic.

For example:

```java
@Service
public class UserService {

    public String getMessage() {
        return "User service is working";
    }
}
```

The Controller does not need to know how the business logic is implemented.

It simply calls the Service.

## 3. `@Service`

`@Service` tells Spring that the class should be managed as a Spring Bean.

```java
@Service
public class UserService {
}
```

Spring creates and manages the `UserService` object.

## 4. Constructor Injection

The Controller needs `UserService`.

We inject it through the constructor:

```java
private UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring sees the dependency and provides the `UserService` Bean.

## 5. Controller Responsibility

The Controller handles HTTP-related responsibilities.

Example:

```java
@GetMapping("/users/service")
public String getMessage() {
    return userService.getMessage();
}
```

The Controller receives the request and delegates the actual work to the Service.

## 6. Service Responsibility

The Service contains the application logic.

```java
public String getMessage() {
    return "User service is working";
}
```

The Controller should not duplicate this business logic.

## 7. Why Separate Controller and Service?

Without a Service:

```text
Controller
   ↓
HTTP + Business Logic
```

As the application grows, the Controller can become difficult to maintain.

With a Service:

```text
Controller
   ↓
Service
   ↓
Business Logic
```

Each layer has a clearer responsibility.

## 8. Final Flow

Our API works like this:

```text
GET /users/service
        ↓
UserController
        ↓
userService.getMessage()
        ↓
UserService
        ↓
"User service is working"
```

## 9. Important Concepts

### `@RestController`

Marks a class as a REST Controller.

### `@Service`

Marks a class as a Spring Service Bean.

### Constructor Injection

Provides required dependencies through the constructor.

### Separation of Responsibilities

Keeps HTTP handling and business logic in separate classes.

## 10. Key Takeaways

1. Controller handles HTTP requests.
2. Service handles application/business logic.
3. `@Service` makes a class a Spring-managed Bean.
4. Constructor Injection connects Controller and Service.
5. Separating responsibilities makes applications easier to maintain and test.