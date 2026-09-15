# Problem 25 — Custom Exception: User Not Found

## Objective

Create a custom application exception for handling the situation where a requested user does not exist.

The goal is to understand how and why applications create custom exceptions instead of returning `null` or using generic exceptions.

---

## Scenario

Our user API provides:

```text
GET /users/{id}
```

When a user exists, the API should return that user.

When the requested user does not exist, the service layer should throw a custom exception:

```text
UserNotFoundException
```

---

## Requirements

### 1. User Model

Create a simple `User` class containing:

- `id`
- `name`
- `email`

Include:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

---

### 2. User Repository

Create an in-memory `UserRepository`.

Store at least these users:

```text
101 → Gaurav → gaurav@gmail.com
102 → Priya  → priya@gmail.com
```

Provide a method to find a user by ID.

If the user does not exist, the repository may return:

```java
null
```

The repository should not throw `UserNotFoundException`.

---

## 3. Custom Exception

Create:

```text
UserNotFoundException
```

This should be a custom runtime exception.

The exception should contain a useful message indicating that the requested user was not found.

Example:

```text
User with id 999 not found
```

---

## 4. Service Layer

Create a `UserService`.

The service should:

1. Ask the repository for the user.
2. Check whether the returned user is `null`.
3. If the user exists, return it.
4. If the user does not exist, throw `UserNotFoundException`.

Conceptually:

```text
Repository
    ↓
User found?
  ↙       ↘
YES       NO
 ↓         ↓
User       throw
           UserNotFoundException
```

---

## 5. Controller

Create:

```text
GET /users/{id}
```

The controller should call the service and return the user.

For this problem, do not create an exception handler yet.

Exception handling will be covered in the next problems.

---

## 6. Expected Behavior

### Existing user

Request:

```text
GET /users/101
```

Expected response:

```json
{
  "id": 101,
  "name": "Gaurav",
  "email": "gaurav@gmail.com"
}
```

---

### Missing user

Request:

```text
GET /users/999
```

The service should throw:

```text
UserNotFoundException
```

The application does not need to provide a custom JSON error response yet.

That will be handled in Problem 26.

---

## 7. Layer Responsibility

The problem should follow this structure:

```text
Controller
    ↓
Service
    ↓
Repository
```

Responsibilities:

### Controller

Handles HTTP requests.

### Service

Contains the business decision that a missing user is an exceptional situation.

### Repository

Searches for the user and returns the result.

---

## 8. Constraints

- Use Spring Boot.
- Use Spring Web MVC.
- Use an in-memory `List<User>`.
- Create a custom `UserNotFoundException`.
- The exception should extend `RuntimeException`.
- Throw the custom exception from the service layer.
- Do not throw the exception from the repository.
- Do not use `@ExceptionHandler` yet.
- Do not use `@ControllerAdvice` yet.
- Do not use a database.
- Keep the architecture as Controller → Service → Repository.

---

## 9. Completion Criteria

- [ ] Spring Boot project is created.
- [ ] `User` model is created.
- [ ] `UserRepository` is created.
- [ ] Initial users 101 and 102 are available.
- [ ] `UserNotFoundException` is created.
- [ ] Exception extends `RuntimeException`.
- [ ] `UserService` is created.
- [ ] Service throws `UserNotFoundException` for a missing user.
- [ ] `UserController` is created.
- [ ] `GET /users/{id}` works for an existing user.
- [ ] Missing user causes `UserNotFoundException`.
- [ ] Application builds successfully.
- [ ] API is tested successfully.
- [ ] Notes and interview questions are documented.
- [ ] Root `PROBLEMS.md` is updated.