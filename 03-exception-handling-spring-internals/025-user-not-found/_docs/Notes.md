# Notes — Problem 25: Custom Exception — UserNotFound

## 1. Custom Exception

A custom exception represents an application-specific error.

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
```

### Why extend `RuntimeException`?

`RuntimeException` is an unchecked exception, so the caller does not have to explicitly declare or catch it.

---

## 2. Throwing the Exception

The **service layer** checks whether the requested user exists.

```java
User user = userRepository.findById(id);

if (user == null) {
    throw new UserNotFoundException(
        "User with id " + id + " not found"
    );
}
```

The repository only performs data access. It returns `null` when the user does not exist.

The service layer decides what that situation means for the application.

---

## 3. Layer Responsibilities

```text
Controller
    ↓
Service
    ↓
Repository
```

### Controller

Receives the HTTP request and calls the service.

### Service

Contains business logic and decides when to throw `UserNotFoundException`.

### Repository

Searches for the user and returns the result.

---

## 4. Constructor Injection

The controller and service use constructor injection.

```java
private final UserService userService;

UserController(UserService userService) {
    this.userService = userService;
}
```

Benefits:

- Dependency is provided when the object is created.
- `final` prevents reassignment.
- Makes dependencies explicit.
- Easy to test.

---

## 5. Request Flow

### Existing User

```text
GET /users/101
       ↓
Controller
       ↓
Service
       ↓
Repository
       ↓
User found
       ↓
User returned
       ↓
HTTP response
```

### Missing User

```text
GET /users/999
       ↓
Controller
       ↓
Service
       ↓
Repository → null
       ↓
UserNotFoundException
       ↓
No exception handler yet
       ↓
HTTP 500
```

The Whitelabel Error Page for the missing user is expected in this problem because exception handling has not been implemented yet.

---

## 6. Important Design Rule

Do **not** throw the application-specific `UserNotFoundException` from the repository in this problem.

```text
Repository → finds data / returns null
Service    → applies business meaning
```

The service is therefore responsible for converting the missing result into an application exception.

---

## 7. Key Takeaways

- Custom exceptions represent application-specific errors.
- `UserNotFoundException` extends `RuntimeException`.
- The service throws the exception when the user does not exist.
- The repository returns `null` when no user is found.
- Constructor injection is used for dependencies.
- Without an exception handler, the exception results in HTTP 500.
- Problem 26 will handle this exception and return a proper error response.