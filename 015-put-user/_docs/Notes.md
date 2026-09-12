# Problem 15 — PUT User

## 1. What is PUT?

`PUT` is an HTTP method commonly used to update an existing resource.

Example:

```text
PUT /users/101
```

The `101` identifies the resource we want to update.

---

## 2. `@PutMapping`

Spring uses `@PutMapping` to map an HTTP PUT request to a controller method.

```java
@PutMapping("/users/{id}")
public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
    return userService.updateUser(id, updatedUser);
}
```

---

## 3. `@PathVariable`

`@PathVariable` reads a value from the URL path.

```text
/users/101
```

```java
@PathVariable int id
```

Here:

```text
id = 101
```

---

## 4. `@RequestBody`

`@RequestBody` converts the incoming JSON request body into a Java object.

Request:

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

Spring converts it into a `User` object.

```java
@RequestBody User updatedUser
```

---

## 5. Updating an Existing Object

The repository first finds the existing user:

```java
User existingUser = findById(id);
```

Then updates the required field:

```java
existingUser.setName(updatedUser.getName());
```

The existing object in the list is modified.

---

## 6. Why Use the Path ID?

For:

```text
PUT /users/101
```

the path identifies the resource being updated.

The request body contains the new data.

```text
Path → Which user?
Body → What new data?
```

Therefore, the repository updates the existing user's name rather than changing the user's ID.

---

## 7. Layered PUT Flow

```text
HTTP PUT Request
       ↓
UserController
       ↓
UserService
       ↓
UserRepository
       ↓
findById()
       ↓
Update existing User
       ↓
Return updated User
```

### Controller

Receives the HTTP request.

### Service

Coordinates the business operation.

### Repository

Finds and updates the user in the in-memory list.

---

## 8. Constructor Injection

The Controller receives `UserService` through its constructor:

```java
public UserController(UserService userService) {
    this.userService = userService;
}
```

The Service receives `UserRepository` in the same way:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

This allows Spring to inject the required dependencies automatically.

---

## 9. Current Implementation

The application uses an in-memory list:

```java
private final List<User> users = new ArrayList<>();
```

Initial data:

```text
101 → Gaurav
102 → Rahul
```

A successful PUT request can change:

```text
101 → Gaurav Kumar
```

---

## 10. Important Limitation

If the requested user does not exist, the current implementation returns `null`.

Proper HTTP status handling, such as returning `404 Not Found`, will be covered later in the curriculum with `ResponseEntity` and exception handling.

---

## 11. Key Annotations

| Annotation | Purpose |
|---|---|
| `@RestController` | Creates a REST controller |
| `@PutMapping` | Maps HTTP PUT requests |
| `@PathVariable` | Reads values from the URL |
| `@RequestBody` | Reads JSON request data |
| `@Service` | Marks the service layer |
| `@Repository` | Marks the repository layer |

---

## 12. Quick Revision

```text
PUT
 ↓
Update existing resource

@PathVariable
 ↓
Read ID from URL

@RequestBody
 ↓
Read updated data from JSON

Repository
 ↓
Find existing object and modify it
```