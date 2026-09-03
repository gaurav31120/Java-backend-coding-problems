# Problem 6 — Create User

## Topic

REST API — `@RequestBody`

## Difficulty

🟡 Intermediate

## Objective

Create a REST API that accepts user information as JSON in the HTTP request body and returns the created user as a JSON response.

## Requirements

### 1. Create User Model

Create a `User` class containing:

- `id`
- `name`

The class should have:

- No-argument constructor
- Parameterized constructor
- Getters
- Setters

### 2. Create POST API

Create the following endpoint:

```text
POST /users
```

The API must accept JSON in the request body.

Example request:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

### 3. Use @RequestBody

Use Spring's `@RequestBody` annotation to convert the incoming JSON into a Java `User` object.

Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

### 4. Return the User

Return the received `User` object as the API response.

For the request:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Expected response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

## Testing

Test the API using a POST request with:

```text
POST http://localhost:8080/users
```

Request body:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Also test with different values, for example:

```json
{
  "id": 102,
  "name": "Rahul"
}
```

## Concepts Learned

- HTTP POST
- Request body
- `@RequestBody`
- `@PostMapping`
- JSON to Java object conversion
- Java object to JSON response
- REST API request/response flow

## Success Criteria

The problem is complete when:

- The Spring Boot application starts successfully.
- `POST /users` accepts JSON.
- JSON is converted into a `User` object.
- The received user is returned as JSON.
- Multiple different user requests work successfully.