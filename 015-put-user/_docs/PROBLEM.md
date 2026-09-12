# Problem 15 — PUT User

## Objective

Build a REST API that updates an existing user using the HTTP `PUT` method.

The API should identify the user using a path variable and receive the updated user information through the request body.

---

## Requirements

### 1. User Model

Create a `User` class containing:

- `id`
- `name`

Provide:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

---

### 2. Repository Layer

Create `UserRepository`.

- Annotate it with `@Repository`.
- Maintain an in-memory `List<User>`.
- Initially store:
    - `101 → Gaurav`
    - `102 → Rahul`

Provide a method to find a user by ID.

Provide a method to update an existing user.

---

### 3. Service Layer

Create `UserService`.

- Annotate it with `@Service`.
- Inject `UserRepository` using constructor injection.
- Provide a method to update a user.
- Pass the update operation to the Repository.

---

### 4. Controller Layer

Create `UserController`.

- Annotate it with `@RestController`.
- Inject `UserService` using constructor injection.
- Create:

```text
PUT /users/{id}
```

- Use `@PathVariable` to receive the user ID.
- Use `@RequestBody` to receive the updated user data.
- Return the updated user.

---

## Request

```text
PUT http://localhost:8080/users/101
```

Request body:

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

---

## Expected Response

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

---

## Important Concepts

### Path Variable

The ID comes from the URL:

```text
/users/101
```

and is captured using:

```java
@PathVariable int id
```

### Request Body

The updated user data comes from JSON:

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

and is captured using:

```java
@RequestBody User user
```

---

## Constraints

- Use Spring Boot.
- Use Spring Web.
- Use an in-memory list.
- Do not use a database.
- Do not use JPA.
- Follow:

```text
Controller → Service → Repository
```

- Use constructor injection.
- Use `@PathVariable`.
- Use `@RequestBody`.

---

## Completion Criteria

The problem is complete when:

- The application starts successfully.
- `PUT /users/{id}` accepts an update request.
- The correct user is identified using the path variable.
- The user's data is updated.
- The updated user is returned.
- Maven build succeeds.
- The API is tested successfully before committing documentation.