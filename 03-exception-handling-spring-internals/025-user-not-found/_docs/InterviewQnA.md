# Interview Q&A — Problem 25: Custom Exception — UserNotFound

## 1. Why do we create a custom exception?

A custom exception represents a specific application-level error.

For example, `UserNotFoundException` clearly communicates that the requested user does not exist.

---

## 2. Why does `UserNotFoundException` extend `RuntimeException`?

Because `RuntimeException` is an unchecked exception.

The caller does not need to explicitly catch or declare an unchecked exception.

---

## 3. Where should `UserNotFoundException` be thrown?

In the **service layer**.

The repository only searches for the user and returns `null` when the user is not found.

The service interprets that result and throws the application-specific exception.

---

## 4. Why not throw the exception from the repository?

The repository should focus on data access.

Keeping the exception decision in the service separates:

```text
Data access → Repository
Business logic → Service
HTTP handling → Controller
```

---

## 5. What happens when `/users/999` is requested?

The repository cannot find user `999` and returns `null`.

The service then executes:

```java
throw new UserNotFoundException(
    "User with id " + id + " not found"
);
```

Because no exception handler has been created yet, Spring returns an HTTP 500 response.

---

## 6. What is constructor injection?

Constructor injection means dependencies are provided through the class constructor.

Example:

```java
private final UserService userService;

UserController(UserService userService) {
    this.userService = userService;
}
```

It makes the dependency explicit and allows the field to remain `final`.

---

## 7. What is the difference between `RuntimeException` and `Exception`?

`RuntimeException` is unchecked, while a normal checked `Exception` must generally be handled or declared.

For application-specific errors such as a missing resource, an unchecked custom exception is commonly used.

---

## 8. What is the problem with returning HTTP 500 for a missing user?

HTTP 500 represents a server-side failure.

A missing user is normally a client-visible resource-not-found situation and should eventually be represented with an appropriate HTTP status such as **404 Not Found**.

Problem 26 will introduce exception handling to improve this response.

---

## 9. What is the role of the controller in this problem?

The controller exposes:

```text
GET /users/{id}
```

It receives the user ID and delegates the operation to `UserService`.

It does not contain the user lookup business logic.

---

## 10. What is the overall architecture used here?

```text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Data
```

For a missing user:

```text
Data → null
       ↓
Service
       ↓
Custom Exception
       ↓
Exception Handler
```

The exception-handler portion will be implemented in the next problem.