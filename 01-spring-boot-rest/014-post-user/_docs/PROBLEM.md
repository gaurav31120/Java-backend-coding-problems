# Problem 14 — POST User

## Objective

Build a REST API that creates a new user using the HTTP `POST` method.

The API should accept user data as JSON in the request body and return the created user.

---

## Requirements

### 1. User Model

Create a `User` class containing:

- `id`
- `name`

The class should have:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

The no-argument constructor is required so Spring/Jackson can create a `User` object from incoming JSON.

---

### 2. Repository Layer

Create `UserRepository`.

- Annotate it with `@Repository`.
- Maintain an in-memory `List<User>`.
- Initially store:
    - `101 → Gaurav`
    - `102 → Rahul`
- Provide a method to add a user.
- Provide a method to retrieve all users.

---

### 3. Service Layer

Create `UserService`.

- Annotate it with `@Service`.
- Inject `UserRepository` using constructor injection.
- Provide a method for creating a user.
- Delegate user storage to the repository.

---

### 4. Controller Layer

Create `UserController`.

- Annotate it with `@RestController`.
- Inject `UserService` using constructor injection.
- Create:

```text
POST /users
```

- Accept a `User` from the request body using `@RequestBody`.
- Pass the user to the Service.
- Return the created user.

---

## Request

```text
POST http://localhost:8080/users
```

Request body:

```json
{
  "id": 103,
  "name": "Amit"
}
```

---

## Expected Response

```json
{
  "id": 103,
  "name": "Amit"
}
```

---

## Important Difference from GET

### GET

```text
GET /users
```

Reads existing users.

### POST

```text
POST /users
```

Creates a new user.

The POST request sends data in the request body.

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
- Use `@RequestBody` to receive JSON.

---

## Completion Criteria

The problem is complete when:

- The application starts successfully.
- `POST /users` accepts JSON.
- The JSON is converted into a `User` object.
- The Service passes the user to the Repository.
- The Repository stores the new user.
- The created user is returned in the response.
- Maven build succeeds.
- The API is tested successfully before committing.