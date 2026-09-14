# Problem 19 — User Request DTO

## 🎯 Objective

Build a Spring Boot REST API that introduces a **Request DTO (`UserRequest`)** for receiving user data from the client instead of directly accepting the `User` model.

The goal is to understand why APIs commonly use DTOs to separate external request data from internal application models.

---

## 📌 Problem Statement

Create a REST API:

```text
POST /users
```

The client sends user information as JSON.

Instead of accepting the `User` model directly in the Controller, create a separate request DTO:

```text
UserRequest
```

The request flow should be:

```text
HTTP Request
     ↓
UserRequest DTO
     ↓
Controller
     ↓
Service
     ↓
User
```

---

## 📋 Requirements

### 1. User Model

Create a `User` class containing:

- `id`
- `name`

It should have:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

---

### 2. UserRequest DTO

Create a separate class:

```text
UserRequest
```

It should contain the data that the client is allowed to provide when creating a user.

For this problem, the request should contain:

- `name`

It should **not** contain the user ID.

The ID will be generated/assigned by the application.

---

### 3. Repository

Create an in-memory `UserRepository`.

The repository should:

- Maintain a `List<User>`
- Start with:
    - `101 → Gaurav`
    - `102 → Rahul`
- Provide a method to add a new user
- Provide a way to generate the next user ID

---

### 4. Service

Create a `UserService`.

The Service should:

- Receive a `UserRequest`
- Create a `User` from the request data
- Assign a new ID
- Save the user through the Repository
- Return the created `User`

The Service should contain the application logic for converting the request DTO into the internal `User` model.

---

### 5. Controller

Create a REST Controller with:

```text
POST /users
```

The Controller should:

- Accept the request using `@RequestBody`
- Receive a `UserRequest`
- Pass the DTO to the Service
- Return the created `User`

Do **not** accept `User` directly as the request body.

---

## 🔄 Expected Request

Request:

```http
POST /users
Content-Type: application/json
```

Body:

```json
{
  "name": "Amit"
}
```

---

## ✅ Expected Result

The application should create a new user with an automatically assigned ID.

Example response:

```json
{
  "id": 103,
  "name": "Amit"
}
```

Another request:

```json
{
  "name": "Priya"
}
```

should create:

```json
{
  "id": 104,
  "name": "Priya"
}
```

---

## 🧠 Important Concept

The API request object and the internal model are intentionally different.

```text
UserRequest
--------------
name
```

versus:

```text
User
--------------
id
name
```

The client should not control the generated `id`.

This separation becomes especially important when applications grow and the internal entity contains fields that should never be accepted directly from API clients.

---

## 🏗️ Required Architecture

Use this structure:

```text
Controller
    ↓
UserService
    ↓
UserRepository
    ↓
List<User>
```

And:

```text
JSON
 ↓
UserRequest
 ↓
Service
 ↓
User
```

---

## 🚫 Constraints

Do not use:

- Database
- JPA
- Hibernate
- Lombok
- Bean Validation
- Custom exceptions
- Global exception handling
- Response DTO
- Mapper class

Use only the concepts required for this problem.

---

## 🧪 Testing Requirements

Test the API using PowerShell or another HTTP client.

### Test 1 — Create first user

```json
{
  "name": "Amit"
}
```

Expected:

```text
ID = 103
Name = Amit
```

### Test 2 — Create another user

```json
{
  "name": "Priya"
}
```

Expected:

```text
ID = 104
Name = Priya
```

### Test 3 — Verify persistence during application runtime

After creating users, verify that the new users can be retrieved if you provide an appropriate GET endpoint during implementation/testing.

---

## 🎓 Learning Goals

By completing this problem, you should understand:

1. What a DTO is.
2. What a Request DTO is.
3. Why `UserRequest` and `User` are separate classes.
4. How `@RequestBody` converts JSON into a Java object.
5. Why clients should not directly control generated IDs.
6. How the Service converts a DTO into an internal model.
7. The difference between API input objects and application/domain objects.
8. Why separating API contracts from internal models is useful in real applications.

---

## 💡 Key Question

Before starting implementation, think about this:

> If the `User` class contains an `id` field, why shouldn't the client simply send the ID in the JSON request?

You should be able to explain the answer after completing this problem.****