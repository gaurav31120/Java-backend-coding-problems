# Problem 23 — Multiple Rules: Registration Validation

## 1. Multiple Validation Rules

A DTO can contain multiple validation annotations.

Example:

```java
@NotBlank
@Email
String email;
```

Each annotation represents a separate validation rule.

---

## 2. `@NotBlank`

`@NotBlank` ensures that a String is:

- Not `null`
- Not empty
- Not whitespace only

Example:

```java
@NotBlank
String name;
```

---

## 3. `@Email`

`@Email` validates that a String follows a valid email format.

Example:

```java
@Email
String email;
```

For registration, it is useful to combine it with `@NotBlank`:

```java
@NotBlank
@Email
String email;
```

This handles both missing/blank email and invalid email format.

---

## 4. `@Size`

`@Size` validates the size of a value.

For the password:

```java
@NotBlank
@Size(min = 8)
String password;
```

This means the password must contain at least 8 characters.

---

## 5. Validation Rules Used

| Field | Rules |
|---|---|
| name | `@NotBlank` |
| email | `@NotBlank`, `@Email` |
| password | `@NotBlank`, `@Size(min = 8)` |

---

## 6. `@Valid`

`@Valid` activates validation on the request body.

```java
@PostMapping("/register")
public RegistrationRequest registerUser(
        @Valid @RequestBody RegistrationRequest registrationRequest) {

    return registrationRequest;
}
```

Flow:

```text
HTTP Request
     ↓
@RequestBody
     ↓
RegistrationRequest
     ↓
@Valid
     ↓
Multiple Validation Rules
     ↓
Valid → Controller method
Invalid → 400 Bad Request
```

---

## 7. API

### Endpoint

```text
POST /register
```

### Valid request

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

Result:

```text
200 OK
```

---

## 8. Invalid Examples

### Blank name

```json
{
  "name": "",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

Result:

```text
400 Bad Request
```

### Invalid email

```json
{
  "name": "Gaurav",
  "email": "invalid-email",
  "password": "password123"
}
```

Result:

```text
400 Bad Request
```

### Short password

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "123"
}
```

Result:

```text
400 Bad Request
```

### Missing fields

```json
{}
```

Result:

```text
400 Bad Request
```

---

## 9. Key Takeaways

- Multiple validation annotations can be applied to the same field.
- `@NotBlank` handles null, empty, and whitespace-only Strings.
- `@Email` validates email format.
- `@Size(min = 8)` enforces a minimum password length.
- `@Valid` activates DTO validation.
- Invalid requests are rejected before normal controller processing.