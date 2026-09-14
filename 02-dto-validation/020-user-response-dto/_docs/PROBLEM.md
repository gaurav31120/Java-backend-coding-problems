# Problem 20 — Response DTO: UserResponse

## Objective

Learn how to use a **Response DTO** to control the data returned by a REST API.

In Problem 19, we introduced a `UserRequest` DTO to control the data coming **into** the application.

In this problem, we will introduce a `UserResponse` DTO to control the data going **out** of the application.

The goal is to prevent the API from directly exposing the complete `User` object.

---

# Scenario

Our application has a `User` model containing:

- `id`
- `name`
- `email`

However, the API should return only:

- `id`
- `name`

The `email` field should remain internal to the application.

Therefore, instead of returning:

```text
User
```

directly from the Controller, the Service should create and return:

```text
UserResponse
```

---

# Project Setup

### Project

```text
020-user-response-dto
```

### Location

```text
D:\Coding\Java-backend-coding-problems\02-dto-validation\020-user-response-dto
```

### Java

```text
JDK 21
```

### Build Tool

```text
Maven
```

### Dependency

```text
Spring Web
```

### Package

```text
com.practice.userresponsedto
```

---

# Required Structure

```text
020-user-response-dto/
├── _docs/
│   └── PROBLEM.md
│
└── src/
    └── main/
        └── java/
            └── com/
                └── practice/
                    └── userresponsedto/
                        ├── User.java
                        ├── UserResponse.java
                        ├── UserRepository.java
                        ├── UserService.java
                        └── UserController.java
```

---

# Requirements

## 1. Create the User Model

Create:

```text
User.java
```

The `User` model must contain:

```text
int id
String name
String email
```

Provide:

- no-argument constructor
- parameterized constructor
- getters
- setters

---

# 2. Create UserResponse DTO

Create:

```text
UserResponse.java
```

`UserResponse` must contain only:

```text
int id
String name
```

It must **not** contain:

```text
email
```

Provide:

- no-argument constructor
- parameterized constructor
- getters
- setters

---

# 3. Create the Repository

Create:

```text
UserRepository.java
```

Use an in-memory:

```text
List<User>
```

Initialize it with:

```text
101 → Gaurav → gaurav@gmail.com
102 → Rahul  → rahul@gmail.com
```

The Repository must provide a method to find a User by ID.

For example:

```text
findById(int id)
```

The method should return the matching User if it exists.

If the User does not exist, return:

```text
null
```

Do not introduce custom exceptions in this problem.

---

# 4. Create the Service

Create:

```text
UserService.java
```

The Service should:

1. Receive a User ID.
2. Ask the Repository for the User.
3. If the User exists, convert the User into a `UserResponse`.
4. Return the `UserResponse`.
5. If the User does not exist, return `null`.

The Service is responsible for controlling what data is exposed to the API.

The conversion should look conceptually like:

```text
User
├── id
├── name
└── email

       ↓

UserResponse
├── id
└── name
```

The `email` must not be copied into `UserResponse`.

---

# 5. Create the Controller

Create:

```text
UserController.java
```

Create an endpoint:

```text
GET /users/{id}
```

The Controller should:

1. Receive the ID using `@PathVariable`.
2. Pass the ID to the Service.
3. Return the `UserResponse`.

Example request:

```text
GET /users/101
```

Expected response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

The response must **not** contain:

```text
email
```

---

# Expected Behavior

## Existing User

Request:

```text
GET /users/101
```

Response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## Another Existing User

Request:

```text
GET /users/102
```

Response:

```json
{
  "id": 102,
  "name": "Rahul"
}
```

---

## Non-existing User

Request:

```text
GET /users/999
```

For this problem, the focus is Response DTO creation and exposure control.

The Service should return `null` when the User does not exist.

HTTP status handling and global exception handling will be covered in later problems.

---

# Important Rule

The Controller should **not** directly return the `User` object.

Avoid:

```java
public User getUser(int id)
```

The endpoint should return:

```text
UserResponse
```

This ensures the API exposes only the fields defined by the Response DTO.

---

# Architecture

The expected flow is:

```text
Client
   ↓
GET /users/{id}
   ↓
Controller
   ↓
UserService
   ↓
UserRepository
   ↓
User
   ↓
UserResponse
   ↓
Controller
   ↓
Client
```

---

# Example

### Internal User

```text
User
-------------------------
id       = 101
name     = Gaurav
email    = gaurav@gmail.com
```

### API Response

```text
UserResponse
-------------------------
id       = 101
name     = Gaurav
```

The email exists internally but is not exposed through the API.

---

# Constraints

Do **not** use:

- Database
- JPA
- Hibernate
- Lombok
- Bean Validation
- Custom Exceptions
- `@ControllerAdvice`
- Mapper class
- MapStruct
- ModelMapper
- Spring Security
- ResponseEntity

Keep the implementation focused on learning the **Response DTO concept**.

Use:

```text
Controller → Service → Repository
```

---

# Testing

Start the Spring Boot application and test using PowerShell or another HTTP client.

Test at least:

### Test 1 — Existing User

```text
GET /users/101
```

Expected:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Verify that `email` is **not present**.

### Test 2 — Another Existing User

```text
GET /users/102
```

Expected:

```json
{
  "id": 102,
  "name": "Rahul"
}
```

Verify that `email` is **not present**.

### Test 3 — Non-existing User

```text
GET /users/999
```

Verify that the Service returns `null`.

---

# Learning Goals

By completing this problem, you should understand:

- What a Response DTO is.
- Why APIs should not always expose domain/model objects directly.
- Difference between Request DTO and Response DTO.
- How `UserResponse` controls API output.
- How to convert a `User` into a `UserResponse`.
- Why the Service performs the conversion in this problem.
- How Controller → Service → Repository flow works.
- How DTOs help define a clean API contract.

---

# Completion Criteria

The problem is complete when:

- [ ] `User.java` contains `id`, `name`, and `email`.
- [ ] `UserResponse.java` contains only `id` and `name`.
- [ ] Repository contains initial users.
- [ ] Repository can find a User by ID.
- [ ] Service converts `User` → `UserResponse`.
- [ ] Controller exposes `GET /users/{id}`.
- [ ] API returns `UserResponse`, not `User`.
- [ ] Email is not exposed in the JSON response.
- [ ] Existing-user tests pass.
- [ ] Non-existing-user test passes.
- [ ] Project builds successfully.
- [ ] Project runs successfully.
- [ ] Final documentation is completed.
- [ ] `PROBLEMS.md` is updated.