# Problem 18 — HTTP Status Challenge

## 1. What I Learned

This problem demonstrates how a Spring Boot REST API can return different HTTP status codes based on the request and application result.

Endpoint:

`GET /users/{id}`

The API has three outcomes:

| Situation | HTTP Status | Meaning |
|---|---:|---|
| Valid ID and user exists | 200 OK | Request succeeded |
| Valid ID but user doesn't exist | 404 NOT FOUND | Resource doesn't exist |
| ID is `<= 0` | 400 BAD REQUEST | Invalid client request |

---

## 2. ResponseEntity

`ResponseEntity<T>` allows the Controller to control:

- HTTP status
- response body
- HTTP headers

Examples:

```java
return ResponseEntity.ok(user);
```

Returns:

```text
200 OK
```

with the `User` object.

```java
return ResponseEntity.notFound().build();
```

Returns:

```text
404 NOT FOUND
```

without a response body.

```java
return ResponseEntity.badRequest().build();
```

Returns:

```text
400 BAD REQUEST
```

without a response body.

---

## 3. Controller Decision Flow

The Controller validates the ID before calling the Service.

```text
GET /users/{id}
       |
       v
Is id <= 0?
   |          |
  YES         NO
   |           |
   v           v
 400       Call Service
              |
         +----+----+
         |         |
       found    not found
         |         |
         v         v
       200        404
```

This follows the principle of rejecting invalid requests as early as possible.

---

## 4. Layer Responsibilities

### Controller

Responsible for:

- Receiving HTTP requests
- Reading `@PathVariable`
- Validating request-level conditions
- Returning HTTP status codes
- Building HTTP responses

### Service

Responsible for:

- Application/business logic
- Calling the Repository

The Service should not return `ResponseEntity`.

### Repository

Responsible for:

- Accessing stored data
- Searching for users

The Repository should not know about HTTP status codes.

---

## 5. Important Spring Annotations

### `@RestController`

Marks the class as a REST controller.

```java
@RestController
public class UserController {
}
```

### `@GetMapping`

Maps an HTTP GET request to a method.

```java
@GetMapping("/users/{id}")
```

### `@PathVariable`

Reads a value from the URL.

```java
public ResponseEntity<User> findById(@PathVariable int id)
```

For:

```text
/users/101
```

`id` becomes:

```text
101
```

### `@Service`

Marks the Service class as a Spring-managed bean.

### `@Repository`

Marks the Repository as a Spring-managed data-access component.

---

## 6. Constructor Injection

The Controller receives `UserService` through its constructor:

```java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

Advantages:

- Dependency is explicit
- Field can be `final`
- Easier to test
- Encourages clean dependency management

---

## 7. Why Validate Before Calling the Service?

For an invalid ID such as `0` or `-1`, the request is already invalid.

Therefore:

```java
if (id <= 0) {
    return ResponseEntity.badRequest().build();
}
```

should happen before:

```java
userService.findById(id);
```

This avoids unnecessary application and repository work.

---

## 8. Final API Behavior

```text
GET /users/101
→ 200 OK
→ User 101

GET /users/999
→ 404 NOT FOUND

GET /users/0
→ 400 BAD REQUEST

GET /users/-1
→ 400 BAD REQUEST
```

---

## 9. Key Takeaways

- `ResponseEntity` gives the Controller control over HTTP responses.
- `200 OK` represents a successful request with a found resource.
- `404 NOT FOUND` means the requested resource does not exist.
- `400 BAD REQUEST` means the client sent an invalid request.
- HTTP-specific decisions belong in the Controller.
- Service should remain independent of HTTP.
- Repository should remain independent of HTTP.
- Validate invalid input as early as possible.