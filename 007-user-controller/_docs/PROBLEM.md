# Problem 7 — User Controller

## Topic

Spring MVC — Controller

## Difficulty

🟢 Beginner

## Objective

Create a Spring MVC REST controller that exposes user-related API endpoints.

The goal is to understand the responsibility of the Controller layer and how Spring maps HTTP requests to controller methods.

## Requirements

### 1. Create a User Model

Create a `User` class containing:

- `id`
- `name`

Include:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

### 2. Create a UserController

Create a class named:

```text
UserController
```

Annotate it with:

```java
@RestController
```

### 3. Create GET User API

Create:

```text
GET /users/{id}
```

The API should accept the user ID using `@PathVariable`.

Example:

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

### 4. Create User Search API

Create:

```text
GET /users?name=Gaurav
```

Use `@RequestParam` to receive the name.

Expected response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

### 5. Controller Responsibility

The controller should:

- Receive HTTP requests
- Extract request data
- Create/return the appropriate response

Do not introduce a Service or Repository layer in this problem.

Those concepts will be introduced in later problems.

## Concepts Learned

- Spring MVC Controller
- `@RestController`
- `@GetMapping`
- `@PathVariable`
- `@RequestParam`
- HTTP request mapping
- Controller responsibility

## Success Criteria

The following requests work successfully:

```text
GET /users/101
```

and:

```text
GET /users?name=Gaurav
```

Both should return valid JSON responses.