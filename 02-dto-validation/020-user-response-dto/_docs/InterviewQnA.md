# Problem 20 — UserResponse DTO

## 1. What is a DTO?

DTO stands for **Data Transfer Object**.

A DTO is a simple object used to transfer only the data that is required between different layers of an application or between the backend and client.

Instead of exposing the complete `User` object, we can create a DTO containing only the fields that should be returned.

---

## 2. Why do we need a Response DTO?

Suppose our `User` model contains:

- `id`
- `name`
- `email`

But the API should return only:

- `id`
- `name`

If we directly return `User`, the response would contain the email as well.

Instead, we create:

```java
public class UserResponse {

    int id;
    String name;
}
```

Now the API controls exactly what information is exposed.

---

## 3. Entity/Model vs Response DTO

### User

```java
public class User {

    int id;
    String name;
    String email;
}
```

### UserResponse

```java
public class UserResponse {

    int id;
    String name;
}
```

The `User` contains the application's complete user data.

The `UserResponse` contains only the data that the API wants to expose.

---

## 4. Response DTO Flow

The request flows through the application like this:

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
User
  ↓
Service converts User → UserResponse
  ↓
Controller
  ↓
Client
```

The important point is:

```text
User → UserResponse
```

The conversion happens in the **Service layer** in this problem.

---

## 5. User Model

Our `User` contains:

```java
int id;
String name;
String email;
```

Example:

```java
new User(101, "Gaurav", "gaurav@gmail.com");
```

---

## 6. UserResponse DTO

The response DTO contains only:

```java
int id;
String name;
```

Example:

```java
new UserResponse(101, "Gaurav");
```

The email is intentionally not included.

---

## 7. Repository Responsibility

The repository is responsible for finding the actual `User`.

Example:

```java
public User findById(int id) {

    for (User user : users) {

        if (id == user.getId()) {
            return user;
        }
    }

    return null;
}
```

The repository works with the `User` model.

It does not need to know about `UserResponse`.

---

## 8. Service Responsibility

The service gets the `User` from the repository.

Then it converts the `User` into a `UserResponse`.

```java
public UserResponse getUser(int id) {

    User user = userRepository.findById(id);

    if (user == null) {
        return null;
    }

    UserResponse userResponse =
            new UserResponse(user.getId(), user.getName());

    return userResponse;
}
```

This is the main learning point of Problem 20.

---

## 9. Controller Responsibility

The controller receives the HTTP request and delegates to the service.

```java
@GetMapping("/users/{id}")
public UserResponse getUser(@PathVariable int id) {

    return userService.getUser(id);
}
```

The controller returns the `UserResponse`.

It does not expose the complete `User`.

---

## 10. @GetMapping

`@GetMapping` maps an HTTP GET request to a controller method.

Example:

```java
@GetMapping("/users/{id}")
```

Request:

```text
GET /users/101
```

calls:

```java
getUser(101)
```

---

## 11. @PathVariable

`@PathVariable` extracts a value from the URL.

Example:

```java
@GetMapping("/users/{id}")
public UserResponse getUser(@PathVariable int id)
```

For:

```text
/users/101
```

the value of `id` is:

```text
101
```

---

## 12. Constructor Injection

The controller receives `UserService` through its constructor:

```java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

Similarly, the service receives `UserRepository`:

```java
private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

This is constructor-based Dependency Injection.

---

## 13. Spring Annotations Used

### @RestController

Marks the class as a REST controller.

```java
@RestController
public class UserController {
}
```

### @Service

Marks the class as a service component.

```java
@Service
public class UserService {
}
```

### @Repository

Marks the class as a repository component.

```java
@Repository
public class UserRepository {
}
```

### @GetMapping

Maps an HTTP GET request.

```java
@GetMapping("/users/{id}")
```

### @PathVariable

Reads a value from the URL.

```java
@PathVariable int id
```

---

## 14. API Response

For:

```text
GET /users/101
```

the response is:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Notice that:

```text
email
```

is not returned.

This demonstrates the purpose of a Response DTO.

---

## 15. Why not return User directly?

Returning `User` directly can expose fields that the client should not receive.

For example:

```json
{
  "id": 101,
  "name": "Gaurav",
  "email": "gaurav@gmail.com"
}
```

If the API only requires `id` and `name`, exposing email is unnecessary.

Using `UserResponse` gives the API control over the response structure.

---

## 16. DTO Benefits

Response DTOs help with:

- Controlling API responses
- Hiding unnecessary fields
- Separating API contracts from internal models
- Improving maintainability
- Preventing accidental exposure of fields
- Creating different response structures for different APIs

---

## 17. Request DTO vs Response DTO

### Request DTO

Used for data coming **into** the application.

Example:

```text
Client → UserRequest → Controller
```

Problem 19 introduced:

```java
UserRequest
```

### Response DTO

Used for data going **out** of the application.

Example:

```text
Service → UserResponse → Client
```

Problem 20 introduced:

```java
UserResponse
```

---

## 18. Important Architecture

The application now has this flow:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
```

For the response:

```text
Repository
   ↓
User
   ↓
Service
   ↓
UserResponse
   ↓
Controller
   ↓
Client
```

Each layer has a focused responsibility.

---

## 19. Common Mistakes

### Mistake 1 — Returning User instead of UserResponse

```java
public User getUser(int id)
```

This exposes the complete model.

Prefer:

```java
public UserResponse getUser(int id)
```

---

### Mistake 2 — Adding email to UserResponse

If the requirement is only:

```text
id
name
```

do not add:

```java
String email;
```

The DTO should contain only the required response fields.

---

### Mistake 3 — Repository returning UserResponse

The repository should work with the data/model layer.

Prefer:

```java
User user = userRepository.findById(id);
```

Then convert it in the service.

---

### Mistake 4 — Mixing responsibilities

Do not put repository logic inside the controller.

Do not put HTTP logic inside the repository.

Keep:

```text
Controller → HTTP
Service    → Business logic / conversion
Repository → Data access
```

---

## 20. Key Takeaways

1. DTO means **Data Transfer Object**.
2. A Response DTO controls what the API sends to the client.
3. `User` contains complete user data.
4. `UserResponse` contains only the fields required by the API.
5. The Service converts `User` → `UserResponse`.
6. The Repository works with `User`.
7. The Controller returns `UserResponse`.
8. Constructor Injection is used for dependencies.
9. Response DTOs help prevent unnecessary data exposure.
10. Request DTOs and Response DTOs serve different directions of data flow.

---

## 21. Problem 20 Core Pattern

```text
User
 ↓
UserRepository.findById()
 ↓
UserService
 ↓
UserResponse
 ↓
UserController
 ↓
JSON Response
```

The key concept is:

```text
Internal Model ≠ API Response Model
```

That separation becomes increasingly important as the application grows.