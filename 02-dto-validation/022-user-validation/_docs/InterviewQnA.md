# Problem 22 — Interview Q&A

## Q1. What is Bean Validation?

Bean Validation is a standard mechanism for defining validation rules on Java objects using annotations such as `@NotBlank`.

---

## Q2. What does `@NotBlank` do?

`@NotBlank` ensures that a String is not:

- `null`
- empty
- whitespace only

Example:

```java
@NotBlank
String name;
```

---

## Q3. What is the purpose of `@Valid`?

`@Valid` tells Spring to validate the object before continuing with the controller method execution.

Example:

```java
public User addUser(@Valid @RequestBody UserRequest userRequest)
```

---

## Q4. What happens when validation fails?

Spring rejects the request and returns:

```text
400 Bad Request
```

The controller's normal processing does not continue.

---

## Q5. Why put validation on a DTO instead of the Entity?

A request DTO represents data received from the client.

Keeping validation rules on the DTO allows the API to control and validate incoming request data independently from the internal model.

---

## Q6. What is the difference between `@NotBlank`, `@NotEmpty`, and `@NotNull`?

### `@NotNull`

The value cannot be `null`.

### `@NotEmpty`

The value cannot be `null` or empty.

### `@NotBlank`

For Strings, the value cannot be `null`, empty, or whitespace only.

---

## Q7. Where does validation happen in our application flow?

```text
HTTP Request
     ↓
@RequestBody
     ↓
UserRequest
     ↓
@Valid
     ↓
Bean Validation
     ↓
Controller
     ↓
Service
```

If validation fails, the request is rejected before the service layer.

---

## Q8. Why is validation important?

Validation prevents invalid client input from entering the application's business logic.

This improves data quality and reduces unnecessary errors in the service and repository layers.

---

## Q9. What dependency provides Bean Validation in this project?

The project uses:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## Q10. What happens if `@Valid` is removed?

The validation annotations such as `@NotBlank` will not be automatically triggered for the request body through this controller parameter.

Therefore, invalid input could reach the service layer.

---

## Q11. Why did `{}` also return 400 Bad Request?

Because the `name` field is missing, so its value is `null`.

`@NotBlank` does not allow `null`, therefore validation fails.

---

## Q12. Why did `"   "` return 400 Bad Request?

Because `@NotBlank` rejects Strings containing only whitespace.

---

## Q13. What is the main lesson from this problem?

The main lesson is how Spring Boot validates incoming REST request data before allowing it to reach business logic.

The combination is:

```java
@NotBlank
```

on the DTO and:

```java
@Valid
```

on the controller request parameter.