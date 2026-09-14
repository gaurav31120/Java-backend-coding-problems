# Problem 22 — Bean Validation: User Validation

## 1. What is Bean Validation?

Bean Validation is a standard way to validate Java objects using annotations.

Spring Boot integrates Bean Validation with request handling.

Example:

```java
@NotBlank
String name;
```

---

## 2. `@NotBlank`

`@NotBlank` ensures that a String:

- is not `null`
- is not empty
- is not only whitespace

Example:

```java
@NotBlank
String name;
```

These requests are invalid:

```json
{}
```

```json
{
  "name": ""
}
```

```json
{
  "name": "   "
}
```

---

## 3. `@Valid`

`@Valid` tells Spring to validate the request object before executing the controller method.

Example:

```java
@PostMapping("/users")
public User addUser(@Valid @RequestBody UserRequest userRequest) {
    return userService.createUser(userRequest);
}
```

Flow:

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
Valid ───────→ Controller → Service
     │
     └────────→ Invalid → 400 Bad Request
```

---

## 4. Validation Happens Before Service Logic

When validation fails, Spring rejects the request before the service method is executed.

This prevents invalid data from reaching the business layer.

---

## 5. Request DTO

The request object contains the data coming from the client.

```java
public class UserRequest {

    @NotBlank
    String name;
}
```

The validation rule belongs to the request DTO because this is where incoming client data is validated.

---

## 6. Current API

### Endpoint

```text
POST /users
```

### Valid request

```json
{
  "name": "Gaurav"
}
```

### Result

```text
200 OK
```

Example:

```text
103 Gaurav
```

### Invalid request

```json
{
  "name": ""
}
```

Result:

```text
400 Bad Request
```

---

## 7. Important Classes

```text
UserController
      ↓
UserService
      ↓
UserRepository
      ↓
User
```

Validation happens before the controller continues to the service.

---

## 8. Key Takeaways

- Bean Validation validates Java objects using annotations.
- `@NotBlank` validates String values.
- `@Valid` activates validation for the request object.
- Invalid requests are rejected with `400 Bad Request`.
- Validation happens before service logic.
- Validation rules can be placed directly on DTO fields.