# Problem 24 — Validation Error Response API

## Objective

Improve the validation API from Problem 23 by returning a clean, structured error response when request validation fails.

The goal is to learn how Spring represents validation failures and how to extract those errors into an API-friendly response.

---

## Scenario

Our registration API currently validates:

- Name
- Email
- Password

However, when validation fails, the client only receives a generic:

```text
400 Bad Request
```

The API should instead return useful validation messages.

---

## Requirements

### 1. Registration Request

Create a `RegistrationRequest` DTO containing:

- `name`
- `email`
- `password`

Apply these validation rules:

| Field | Validation |
|---|---|
| name | `@NotBlank` |
| email | `@NotBlank`, `@Email` |
| password | `@NotBlank`, `@Size(min = 8)` |

---

## 2. Registration Endpoint

Create:

```text
POST /register
```

Example valid request:

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

A valid request should be accepted.

---

## 3. Validation Error Response

When validation fails, return a structured JSON response.

For example:

```json
{
  "name": "Name is required",
  "email": "Invalid email",
  "password": "Password must be at least 8 characters"
}
```

The exact messages may be chosen by you, but the response must clearly identify:

- Which field failed
- Why the field failed

---

## 4. Validation Error Extraction

Learn how Spring provides validation failures through:

```text
MethodArgumentNotValidException
```

and understand how validation errors can be extracted from the exception.

You should be able to identify:

```text
Field name
Validation message
```

from each validation error.

---

## 5. Structured Error Response

Create a suitable response representation for validation errors.

The response should contain field-specific validation messages rather than exposing the raw framework exception.

---

## 6. Invalid Request Examples

### Blank name

```json
{
  "name": "",
  "email": "gaurav@gmail.com",
  "password": "password123"
}
```

Expected:

```text
400 Bad Request
```

with a meaningful validation message for `name`.

---

### Invalid email

```json
{
  "name": "Gaurav",
  "email": "invalid-email",
  "password": "password123"
}
```

Expected:

```text
400 Bad Request
```

with a meaningful validation message for `email`.

---

### Short password

```json
{
  "name": "Gaurav",
  "email": "gaurav@gmail.com",
  "password": "123"
}
```

Expected:

```text
400 Bad Request
```

with a meaningful validation message for `password`.

---

### Multiple validation errors

```json
{
  "name": "",
  "email": "invalid-email",
  "password": "123"
}
```

The response should contain errors for all invalid fields.

---

## 7. Constraints

- Use Spring Boot.
- Use Bean Validation.
- Use `jakarta.validation`.
- Use `@Valid`.
- Handle `MethodArgumentNotValidException`.
- Return a structured JSON error response.
- Do not return the raw exception object.
- Do not manually validate request fields using `if` statements.
- Do not use `@ControllerAdvice` yet. Global exception handling will be covered in Problem 27.

---

## 8. Completion Criteria

- [ ] `RegistrationRequest` DTO is created.
- [ ] Validation rules are applied.
- [ ] `POST /register` is implemented.
- [ ] `@Valid` activates validation.
- [ ] `MethodArgumentNotValidException` is handled.
- [ ] Validation errors are extracted.
- [ ] Field names are included in the response.
- [ ] Validation messages are included in the response.
- [ ] Multiple validation errors can be returned.
- [ ] Valid requests are accepted.
- [ ] Invalid requests return `400 Bad Request`.
- [ ] Application builds successfully.
- [ ] API is tested successfully.
- [ ] Notes and interview questions are documented.
- [ ] Root `PROBLEMS.md` is updated.