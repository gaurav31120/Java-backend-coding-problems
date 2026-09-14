# Problem 17 — ResponseEntity — Status Handling

## Objective

Build a Spring Boot REST API that uses `ResponseEntity` to return appropriate HTTP status codes based on whether a user exists.

The application should follow a layered architecture:

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

Create a method:

```java
public User findById(int id)
```

The method should:

1. Search the list for the requested ID.
2. Return the matching user if found.
3. Return `null` if the user does not exist.

---

### 3. User Service

Create a `UserService` class.

Requirements:

- Annotate it with `@Service`
- Use constructor injection for `UserRepository`
- Create a method to find a user by ID.
- Delegate the lookup operation to the Repository.

---

### 4. User Controller

Create a `UserController` class.

Requirements:

- Annotate it with `@RestController`
- Use constructor injection for `UserService`
- Create the endpoint:

```text
GET /users/{id}
```

- Use `@PathVariable` to receive the user ID.
- Return the response using `ResponseEntity<User>`.

---

## Response Requirements

### User Exists

Request:

```text
GET http://localhost:8080/users/101
```

Expected response:

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

### User Does Not Exist

Request:

```text
GET http://localhost:8080/users/999
```

Expected response:

```text
HTTP 404 NOT FOUND
```

There should be no user body in the response.

---

## ResponseEntity

`ResponseEntity` allows the Controller to explicitly control:

- HTTP status
- Response body
- HTTP headers

For example:

```java
return ResponseEntity.ok(user);
```

returns:

```text
200 OK
```

with the user as the response body.

And:

```java
return ResponseEntity.notFound().build();
```

returns:

```text
404 NOT FOUND
```

without a response body.

---

## Constraints

- Use Spring Boot.
- Use Spring Web.
- Use Java 21.
- Use an in-memory `List<User>`.
- Do not use a database.
- Do not use JPA.
- Use `ResponseEntity`.
- Follow Controller → Service → Repository layering.
- Use constructor injection.
- Do not introduce custom exceptions yet.

---

## Learning Goals

By completing this problem, you should understand:

- What `ResponseEntity` is.
- Why HTTP status codes matter.
- How to return `200 OK`.
- How to return `404 NOT FOUND`.
- How to return a response body conditionally.
- How the Controller can explicitly control an HTTP response.
- The difference between returning a Java object and returning `ResponseEntity<T>`.