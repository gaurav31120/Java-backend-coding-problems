# Problem 23 — Multiple Rules: Registration Validation

## Objective

Build a Spring Boot REST API that validates a user registration request using multiple Bean Validation rules.

The goal is to learn how multiple validation annotations can be applied to different fields of the same request DTO.

---

## Scenario

A user registration API receives:

- Name
- Email
- Password

The API must reject invalid registration data before it reaches the service layer.

---

## Requirements

### 1. Registration Request DTO

Create a `RegistrationRequest` class containing:

- `name`
- `email`
- `password`

The DTO must contain:

```text
String name
String email
String password
```

Include:

- No-argument constructor
- Getters
- Setters

---

### 2. Name Validation

The `name` field must:

- Not be `null`
- Not be empty
- Not contain only whitespace

Use:

```java
@NotBlank
```

---

### 3. Email Validation

The `email` field must:

- Not be `null`
- Not be empty
- Not contain only whitespace
- Follow a valid email format

Use:

```java
@NotBlank
@Email
```

---

### 4. Password Validation

The `password` field must:

- Not be `null`
- Not be empty
- Not contain only whitespace
- Contain at least 8 characters

Use:

```java
@NotBlank
@Size(min = 8)
```

---

## 5. Registration API

Create:

```text
POST /register
```

The request body should contain:

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

A valid request should be accepted by the API.

---

## 6. Invalid Requests

The API must reject requests such as:

### Blank name

```json
{
  "name": "",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

### Invalid email

```json
{
  "name": "Gaurav",
  "email": "invalid-email",
  "password": "password123"
}
```

### Short password

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "123"
}
```

### Missing fields

```json
{}
```

Invalid requests should result in:

```text
400 Bad Request
```

---

## 7. Validation Activation

Use:

```java
@Valid
```

on the controller's `@RequestBody`.

Example concept:

```java
public RegistrationRequest register(
        @Valid @RequestBody RegistrationRequest request) {
    ...
}
```

The exact controller implementation is part of the challenge.

---

## 8. Validation Rules

| Field | Validation |
|---|---|
| name | `@NotBlank` |
| email | `@NotBlank`, `@Email` |
| password | `@NotBlank`, `@Size(min = 8)` |

---

## 9. Constraints

- Use Spring Boot.
- Use Bean Validation.
- Use `jakarta.validation` annotations.
- Do not manually validate fields using `if` statements in the controller.
- Validation must happen automatically through Spring's validation mechanism.
- Keep the request data inside a DTO.
- Do not store the password in a database for this problem.
- Focus on understanding multiple validation rules.

---

## 10. Completion Criteria

The problem is complete when:

- [ ] `RegistrationRequest` DTO is created.
- [ ] `name` uses `@NotBlank`.
- [ ] `email` uses `@NotBlank` and `@Email`.
- [ ] `password` uses `@NotBlank` and `@Size(min = 8)`.
- [ ] `POST /register` is implemented.
- [ ] `@Valid` activates validation.
- [ ] Valid registration data is accepted.
- [ ] Invalid name is rejected.
- [ ] Invalid email is rejected.
- [ ] Short password is rejected.
- [ ] Missing fields are rejected.
- [ ] Application builds successfully.
- [ ] API is tested successfully.
- [ ] Notes and interview questions are documented.
- [ ] Root `PROBLEMS.md` is updated.