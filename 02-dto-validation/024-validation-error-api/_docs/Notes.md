# Problem 24 — Validation Error Response API

## 1. Problem

Bean Validation automatically rejects invalid requests with `400 Bad Request`, but an API should provide useful information about which fields failed validation.

This problem extracts validation errors and returns them as a structured JSON response.

---

## 2. `MethodArgumentNotValidException`

When `@Valid` validation fails for a request body, Spring throws:

```java
MethodArgumentNotValidException
```

The exception contains the validation information.

---

## 3. Getting Validation Errors

We can access the validation errors using:

```java
exception.getBindingResult().getFieldErrors();
```

`getFieldErrors()` returns all field-level validation errors.

---

## 4. `FieldError`

Each `FieldError` contains information about a failed field.

Get the field name:

```java
fieldError.getField();
```

Get the validation message:

```java
fieldError.getDefaultMessage();
```

Example:

```text
fieldName = "email"
message = "must be a well-formed email address"
```

---

## 5. Building the Error Map

A `Map<String, String>` can store the field and its validation message:

```java
Map<String, String> map = new HashMap<>();

for (FieldError fieldError : fieldErrors) {

    String fieldName = fieldError.getField();
    String message = fieldError.getDefaultMessage();

    map.put(fieldName, message);
}
```

The resulting structure can look like:

```json
{
  "name": "must not be blank",
  "email": "must be a well-formed email address",
  "password": "size must be between 8 and 2147483647"
}
```

---

## 6. `@ExceptionHandler`

`@ExceptionHandler` tells Spring which method should handle a specific exception.

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidationException(
        MethodArgumentNotValidException exception) {
    ...
}
```

---

## 7. `@RestControllerAdvice`

`@RestControllerAdvice` allows the class to handle exceptions from REST controllers and return the result as an HTTP response body.

```java
@RestControllerAdvice
public class ValidationExceptionHandler {
    ...
}
```

---

## 8. Complete Validation Flow

```text
HTTP Request
     ↓
@RequestBody
     ↓
RegistrationRequest
     ↓
@Valid
     ↓
Bean Validation
     ↓
Validation fails
     ↓
MethodArgumentNotValidException
     ↓
@RestControllerAdvice
     ↓
@ExceptionHandler
     ↓
getFieldErrors()
     ↓
Map<String, String>
     ↓
JSON Response
```

---

## 9. Important Difference

### `getFieldError()`

Returns one field error.

### `getFieldErrors()`

Returns all field errors.

For an API that should report multiple invalid fields, use:

```java
getFieldErrors()
```

---

## 10. Key Takeaways

- Validation failures can be handled with `MethodArgumentNotValidException`.
- `getBindingResult()` provides validation information.
- `getFieldErrors()` returns all field errors.
- `FieldError.getField()` provides the field name.
- `FieldError.getDefaultMessage()` provides the validation message.
- `@ExceptionHandler` connects an exception to its handling method.
- `@RestControllerAdvice` allows REST exception handling.
- A `Map<String, String>` can provide a simple structured validation-error response.