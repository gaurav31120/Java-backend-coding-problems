# Problem 18 — HTTP Status Challenge

## Objective

Build a Spring Boot REST API that returns different HTTP status codes based on the result of a user lookup.

The API should correctly distinguish between:

- A successful request for an existing user
- A request for a user that does not exist
- A request containing an invalid user ID

The application should follow:

```text
Controller
    ↓
Service
    ↓
Repository
```

---

## Requirements

### 1. User Model

Create a `User` class with:

- `id`
- `name`

Include:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

---

### 2. User Repository

Create a `UserRepository` class.

Requirements:

- Annotate it with `@Repository`
- Maintain users using an in-memory `List<User>`
- Add these initial users:

```text
101 → Gaurav
102 → Rahul
```

Create:

```java
public User findById(int id)
```

The method should:

1. Search the list for the requested ID.
2. Return the matching user if found.
3. Return `null` if the user does not exist.

The Repository should not decide HTTP status codes.

---

### 3. User Service

Create a `UserService` class.

Requirements:

- Annotate it with `@Service`
- Use constructor injection for `UserRepository`
- Provide a method to find a user by ID.
- Delegate the lookup operation to the Repository.

The Service should not return `ResponseEntity`.

---

### 4. User Controller

Create a `UserController` class.

Requirements:

- Annotate it with `@RestController`
- Use constructor injection for `UserService`
- Create:

```text
GET /users/{id}
```

- Use `@PathVariable` to receive the ID.
- Return the response using:

```java
ResponseEntity<User>
```

---

## HTTP Status Rules

The Controller must apply these rules.

### Case 1 — User Exists

Request:

```text
GET http://localhost:8080/users/101
```

Response:

```text
HTTP 200 OK
```

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

### Case 2 — User Does Not Exist

Request:

```text
GET http://localhost:8080/users/999
```

Response:

```text
HTTP 404 NOT FOUND
```

There should be no response body.

---

### Case 3 — Invalid ID

An ID less than or equal to zero is considered invalid.

Examples:

```text
GET /users/0
GET /users/-1
```

Response:

```text
HTTP 400 BAD REQUEST
```

There should be no response body.

---

## Decision Flow

The Controller should effectively follow this logic:

```text
Receive ID
    ↓
Is ID <= 0?
    ↓ YES
400 BAD REQUEST

    ↓ NO
Find user
    ↓
Does user exist?
   ↙       ↘
 YES       NO
  ↓         ↓
200 OK    404 NOT FOUND
+ User
```

---

## ResponseEntity Examples

### 200 OK

```java
return ResponseEntity.ok(user);
```

### 404 Not Found

```java
return ResponseEntity.notFound().build();
```

### 400 Bad Request

```java
return ResponseEntity.badRequest().build();
```

---

## Constraints

- Use Spring Boot.
- Use Spring Web.
- Use Java 21.
- Use an in-memory `List<User>`.
- Do not use a database.
- Do not use JPA.
- Use `ResponseEntity`.
- Use Controller → Service → Repository layering.
- Use constructor injection.
- Do not introduce custom exceptions yet.
- Do not introduce Bean Validation yet.
- HTTP status decisions should be handled by the Controller.

---

## Testing Requirements

The following cases must be tested:

### Existing User

```text
GET /users/101
```

Expected:

```text
200 OK
```

### Missing User

```text
GET /users/999
```

Expected:

```text
404 NOT FOUND
```

### Invalid ID

```text
GET /users/0
```

Expected:

```text
400 BAD REQUEST
```

### Negative ID

```text
GET /users/-1
```

Expected:

```text
400 BAD REQUEST
```

---

## Learning Goals

By completing this problem, you should understand:

- Common HTTP status codes.
- Difference between `200 OK`, `400 BAD REQUEST`, and `404 NOT FOUND`.
- How `ResponseEntity` controls HTTP responses.
- How to validate simple request conditions in a Controller.
- Why HTTP status decisions belong to the API/Controller layer.
- How Controller, Service, and Repository responsibilities remain separated.