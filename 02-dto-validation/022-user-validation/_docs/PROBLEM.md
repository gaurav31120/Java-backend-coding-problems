# Problem 22 — User Validation

## Objective

Add Bean Validation to the user creation request so that invalid user names are rejected before reaching the Service layer.

## Scenario

The application accepts a user creation request.

The client should provide a valid user name.

Invalid values such as:

- null
- empty string
- whitespace-only string

should not be accepted.

## Requirements

1. Create a `UserRequest` DTO.
2. Add a `name` field to `UserRequest`.
3. Apply Bean Validation to the `name` field.
4. The `name` must not be:
    - null
    - empty
    - blank/whitespace-only
5. Create a `User` model.
6. Create an in-memory `UserRepository`.
7. Create a `UserService`.
8. Create a `UserController`.
9. Create a POST endpoint:
    - `POST /users`
10. Accept `UserRequest` using `@RequestBody`.
11. Trigger validation using `@Valid`.
12. Only valid requests should reach the Service layer.

## Expected Valid Request

```json
{
  "name": "Gaurav"
}
```

The request should be accepted.

## Expected Invalid Requests

### Empty name

```json
{
  "name": ""
}
```

Should be rejected.

### Whitespace-only name

```json
{
  "name": "   "
}
```

Should be rejected.

### Null name

```json
{}
```

Should be rejected.

## Validation

Use Jakarta Bean Validation.

The `name` field should use:

```java
@NotBlank
```

The Controller should trigger validation using:

```java
@Valid
```

## Architecture

```text
Client
   ↓
UserRequest
   ↓
Bean Validation
   ↓
Controller
   ↓
Service
   ↓
Repository
```

Invalid requests should be rejected before normal Service processing.

## Constraints

- Use Java 21.
- Use Spring Boot.
- Use Spring Web.
- Use Spring Validation.
- Use constructor injection.
- Do not use Lombok.
- Do not manually write validation logic such as:
    - `if (name == null)`
    - `if (name.isEmpty())`
    - `if (name.trim().isEmpty())`
- Use Bean Validation annotations.

## Learning Goals

By completing this problem, you should understand:

- What Bean Validation is.
- What `@NotBlank` does.
- What `@Valid` does.
- How Spring validates `@RequestBody`.
- Why validation should happen before business logic.
- How declarative validation differs from manual `if` checks.

## Completion Criteria

- Application starts successfully.
- Valid user creation request is accepted.
- Empty name is rejected.
- Blank/whitespace-only name is rejected.
- Missing name is rejected.
- `@NotBlank` is used on the DTO.
- `@Valid` is used in the Controller.
- Manual validation `if` statements are not used.