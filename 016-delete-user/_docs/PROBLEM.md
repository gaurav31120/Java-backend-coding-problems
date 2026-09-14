# Problem 16 — DELETE User

## Objective

Build a Spring Boot REST API that deletes an existing user from an in-memory list using the HTTP `DELETE` method.

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

Create a method to delete a user by ID.

The method should:

1. Find the user with the given ID.
2. Remove the user if it exists.
3. Return the deleted user.
4. Return `null` if the user does not exist.

---

### 3. User Service

Create a `UserService` class.

Requirements:

- Annotate it with `@Service`
- Use constructor injection for `UserRepository`
- Create a method that delegates the delete operation to the repository.

---

### 4. User Controller

Create a `UserController` class.

Requirements:

- Annotate it with `@RestController`
- Use constructor injection for `UserService`
- Create a DELETE endpoint:

```text
DELETE /users/{id}
```

- Use `@PathVariable` to receive the user ID.
- Call the Service layer.
- Return the deleted user.

---

## API Example

### Request

```text
DELETE http://localhost:8080/users/101
```

### Expected Response

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

After deletion, user `101` should no longer exist in the in-memory list.

---

## Non-Existing User

If the requested ID does not exist:

```text
DELETE /users/999
```

the current beginner implementation should return:

```text
null
```

Proper HTTP status handling will be introduced later in the curriculum.

---

## Constraints

- Use Spring Boot.
- Use Spring Web.
- Use Java 21.
- Use an in-memory `List<User>`.
- Do not use a database.
- Do not use JPA.
- Do not use `ResponseEntity` yet.
- Follow Controller → Service → Repository layering.
- Use constructor injection.

---

## Learning Goals

By completing this problem, you should understand:

- HTTP `DELETE`
- `@DeleteMapping`
- `@PathVariable`
- Removing objects from a Java `List`
- Layered architecture
- Constructor injection
- Delegating operations from Controller → Service → Repository