# Interview Q&A — Problem 26: Exception Handler

## 1. What is `@ExceptionHandler`?

`@ExceptionHandler` is a Spring MVC annotation used to define a method that handles a specific exception.

Example:

```java
@ExceptionHandler(UserNotFoundException.class)
public String handleUserNotFoundException(UserNotFoundException exception) {
    return exception.getMessage();
}
```

---

## 2. What is `@RestControllerAdvice`?

`@RestControllerAdvice` is used for centralized exception handling across REST controllers.

It combines controller advice functionality with REST response handling.

---

## 3. Why did we use `@RestControllerAdvice` instead of putting `@ExceptionHandler` inside `UserController`?

It separates exception-handling logic from normal controller logic.

This becomes especially useful when multiple controllers need the same exception-handling behavior.

---

## 4. What happens when `UserNotFoundException` is thrown?

Spring looks for an exception handler that can handle the thrown exception.

Because our application contains:

```java
@ExceptionHandler(UserNotFoundException.class)
```

Spring invokes the corresponding handler method.

---

## 5. What does `UserNotFoundException.class` mean?

It identifies the exception type that the handler method is responsible for handling.

```java
@ExceptionHandler(UserNotFoundException.class)
```

means the method handles `UserNotFoundException`.

---

## 6. What does `exception.getMessage()` return?

It returns the message stored inside the exception.

For example:

```java
throw new UserNotFoundException(
    "User with id " + id + " not found"
);
```

The handler receives that exception and:

```java
exception.getMessage();
```

returns:

```text
User with id 999 not found
```

---

## 7. What happened before we added the exception handler?

The `UserNotFoundException` was not handled by our application.

Spring therefore returned a generic HTTP 500 response with the Whitelabel Error Page.

---

## 8. What changed after adding `@ExceptionHandler`?

The exception is intercepted by the handler method and its message is returned as the response body.

```text
UserNotFoundException
        ↓
@ExceptionHandler
        ↓
exception.getMessage()
        ↓
HTTP response body
```

---

## 9. Is `@ExceptionHandler` only used with custom exceptions?

No.

It can be used to handle many different exception types, including application-specific exceptions and framework exceptions.

---

## 10. What is the benefit of centralized exception handling?

It avoids repeating exception-handling logic in individual controllers.

Instead of:

```text
Controller 1 → try/catch
Controller 2 → try/catch
Controller 3 → try/catch
```

we can centralize the handling:

```text
Controller 1 ─┐
Controller 2 ─┼→ @RestControllerAdvice
Controller 3 ─┘
```

This makes the application easier to maintain.

---

## 11. Is our current error response production-ready?

Not completely.

Currently the handler returns only a plain `String`.

A production REST API commonly uses a structured error response and an appropriate HTTP status.

Those concerns will be covered in subsequent exception-handling problems.