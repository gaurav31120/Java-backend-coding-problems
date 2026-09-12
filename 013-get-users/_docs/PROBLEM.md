# Problem 13 — GET Users

## Objective

Build a REST API that retrieves a collection of users using the HTTP `GET` method.

The API should follow the layered architecture:

```text
Controller → Service → Repository
```

The users will be stored in memory. No database or JPA is required.

---

## Requirements

### 1. User Model

Create a `User` class containing:

- `id`
- `name`

The class should support creating users with an `id` and `name`.

---

### 2. Repository Layer

Create `UserRepository`.

- Annotate it with `@Repository`.
- Maintain an in-memory `List<User>`.
- Add the following users:
    - `101 → Gaurav`
    - `102 → Rahul`
- Provide a method to retrieve all users.

---

### 3. Service Layer

Create `UserService`.

- Annotate it with `@Service`.
- Inject `UserRepository` using constructor injection.
- Provide a method that retrieves all users from the repository.

---

### 4. Controller Layer

Create `UserController`.

- Annotate it with `@RestController`.
- Inject `UserService` using constructor injection.
- Create the endpoint:

```text
GET /users
```

- Return the list of users received from the service.

---

## Expected API

### Request

```text
GET http://localhost:8080/users
```

### Expected Response

```json
[
  {
    "id": 101,
    "name": "Gaurav"
  },
  {
    "id": 102,
    "name": "Rahul"
  }
]
```

---

## Constraints

- Use Spring Boot.
- Use Spring Web.
- Use an in-memory list.
- Do not use a database.
- Do not use JPA.
- Use the layered architecture:

```text
Controller → Service → Repository
```

- The Controller must not directly access the Repository.
- Use constructor injection for dependencies.

---

## Concepts Practiced

- REST API
- HTTP `GET`
- Collection endpoint
- `List<User>`
- `@RestController`
- `@GetMapping`
- `@Service`
- `@Repository`
- Constructor injection
- Layered architecture
- Returning Java objects as JSON

---

## Completion Criteria

The problem is complete when:

- The application starts successfully.
- `GET /users` returns HTTP 200.
- Both users are returned correctly.
- Controller → Service → Repository flow is working.
- The project builds successfully with Maven.
- The API is tested successfully before committing.****