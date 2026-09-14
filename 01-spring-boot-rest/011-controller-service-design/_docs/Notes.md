# Problem 11 — Controller-Service Design

## 1. Main Idea

A backend application should separate different responsibilities into different layers.

The Controller handles HTTP requests, while the Service handles application or business logic.

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

## 2. Controller Layer

The Controller is responsible for handling HTTP requests.

Example:

```java
@GetMapping("/users/profile")
public String getUserProfile() {
    return userService.getUserProfile();
}
```

The Controller receives the request and delegates the work to the Service.

## 3. Service Layer

The Service contains application or business logic.

Example:

```java
@Service
public class UserService {

    public String getUserProfile() {
        return "User profile from service";
    }
}
```

## 4. `@RestController`

`@RestController` marks a class as a REST Controller.

Spring detects the class and allows it to handle HTTP requests.

## 5. `@Service`

`@Service` tells Spring that a class should be managed as a Spring Bean.

Example:

```java
@Service
public class UserService {
}
```

Spring creates and manages the Service object.

## 6. Constructor Injection

The Controller needs `UserService`.

We provide it through the constructor:

```java
private UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

Spring injects the `UserService` Bean into the Controller.

## 7. Separation of Responsibilities

The Controller should not contain business logic.

Instead of:

```java
@GetMapping("/users/profile")
public String getUserProfile() {
    return "User profile from service";
}
```

we delegate the work:

```java
@GetMapping("/users/profile")
public String getUserProfile() {
    return userService.getUserProfile();
}
```

The message is produced by the Service.

## 8. Why Use Layers?

As applications become larger, keeping everything inside Controllers makes the code difficult to maintain.

Layering provides clearer responsibilities:

```text
Controller → HTTP handling

Service → Business logic

Repository → Database access
```

## 9. Final Flow

Our API follows this flow:

```text
GET /users/profile
        ↓
UserController
        ↓
UserService
        ↓
getUserProfile()
        ↓
"User profile from service"
```

## 10. Key Takeaways

1. Controller handles HTTP requests.
2. Service handles business/application logic.
3. `@Service` makes the Service a Spring Bean.
4. Constructor Injection connects Controller and Service.
5. Separation of responsibilities makes code easier to maintain and test.