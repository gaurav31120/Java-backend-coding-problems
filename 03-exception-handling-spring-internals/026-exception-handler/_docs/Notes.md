# Notes — Problem 26: Exception Handler

## 1. What is `@ExceptionHandler`?

`@ExceptionHandler` is used in Spring MVC to handle a specific exception thrown while processing a request.

Example:

```java
@ExceptionHandler(UserNotFoundException.class)
public String handleUserNotFoundException(UserNotFoundException exception) {
    return exception.getMessage();
}
```

This tells Spring:

> When `UserNotFoundException` occurs, execute this method.

---

## 2. `@RestControllerAdvice`

`@RestControllerAdvice` is used to create a centralized exception-handling component for REST controllers.

```java
@RestControllerAdvice
public class UserExceptionHandler {
}
```

Because it is a REST-specific advice, the returned value is written directly to the HTTP response body.

---

## 3. Exception Handling Flow

Without an exception handler:

```text
Request
   ↓
Controller
   ↓
Service
   ↓
UserNotFoundException
   ↓
Spring
   ↓
500 Internal Server Error
   ↓
Whitelabel Error Page
```

With `@ExceptionHandler`:

```text
Request
   ↓
Controller
   ↓
Service
   ↓
UserNotFoundException
   ↓
@RestControllerAdvice
   ↓
@ExceptionHandler
   ↓
Custom response
```

---

## 4. Getting the Exception Message

The service throws:

```java
throw new UserNotFoundException(
    "User with id " + id + " not found"
);
```

The exception stores the message using:

```java
super(message);
```

The handler can retrieve it using:

```java
exception.getMessage();
```

---

## 5. Why Use an Exception Handler?

Without exception handling, application exceptions can result in generic error responses.

An exception handler allows the application to:

- Handle known exceptions explicitly.
- Return meaningful error information.
- Keep exception-handling logic separate from controller logic.
- Avoid duplicating `try-catch` blocks in controllers.

---

## 6. `@ExceptionHandler` vs `@RestControllerAdvice`

### `@ExceptionHandler`

Identifies the method that handles a particular exception.

```java
@ExceptionHandler(UserNotFoundException.class)
```

### `@RestControllerAdvice`

Makes the exception-handling class applicable to REST controllers.

```java
@RestControllerAdvice
public class UserExceptionHandler {
}
```

They work together in this problem.

---

## 7. Current Error Response

For:

```text
GET /users/999
```

the application now returns:

```text
User with id 999 not found
```

instead of the default Whitelabel Error Page.

At this stage, the response is a plain `String`.

---

## 8. Important Takeaways

- `@ExceptionHandler` handles specific exceptions.
- `@RestControllerAdvice` provides centralized exception handling for REST APIs.
- `exception.getMessage()` retrieves the exception message.
- Exception handling keeps error-handling logic separate from normal controller logic.
- A custom exception can be converted into a meaningful API response.
- Later problems will improve the error response and HTTP status handling.