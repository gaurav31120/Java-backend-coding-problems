# Problem 24 — Interview Q&A

## Q1. What exception is thrown when `@Valid` fails for a request body?

Spring throws:

```java
MethodArgumentNotValidException
```

---

## Q2. How do you get validation errors from `MethodArgumentNotValidException`?

Use:

```java
exception.getBindingResult().getFieldErrors();
```

---

## Q3. What is the difference between `getFieldError()` and `getFieldErrors()`?

`getFieldError()` returns one field error.

`getFieldErrors()` returns a list containing all field errors.

---

## Q4. How do you get the field name from a `FieldError`?

Use:

```java
fieldError.getField();
```

---

## Q5. How do you get the validation message?

Use:

```java
fieldError.getDefaultMessage();
```

---

## Q6. What does `@ExceptionHandler` do?

It tells Spring that a particular method should handle a specified exception.

Example:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
```

---

## Q7. What does `@RestControllerAdvice` do?

It allows a class to provide exception handling for REST controllers and return response bodies directly.

---

## Q8. Why use a `Map<String, String>` for validation errors?

It provides a simple structure where:

```text
key   → field name
value → validation message
```

Example:

```json
{
  "email": "must be a well-formed email address"
}
```

---

## Q9. Why do we loop through `getFieldErrors()`?

Because a single request can contain multiple invalid fields.

For example:

```text
name     → invalid
email    → invalid
password → invalid
```

The loop allows all validation errors to be collected.

---

## Q10. Why must `return map` be outside the loop?

If `return map` is inside the loop, the method stops after the first error.

Putting it outside allows every validation error to be processed first.

---

## Q11. What is the validation error flow?

```text
@Valid
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
JSON response
```

---

## Q12. Why shouldn't we return the raw exception?

The raw framework exception contains implementation details that are not appropriate as a clean API response.

A structured response gives the client only the useful validation information.

---

## Q13. What did this problem add compared with Problem 23?

Problem 23 demonstrated multiple validation rules.

Problem 24 added custom handling of the validation exception and returned field-specific validation messages.

---

## Q14. What is the main lesson?

Spring can intercept validation failures through `MethodArgumentNotValidException`, allowing the application to transform framework validation errors into a cleaner API response.