# Problem 26 — Exception Handler

## Objective

Handle a custom `UserNotFoundException` using Spring's `@ExceptionHandler` instead of allowing the exception to produce the default Whitelabel Error Page.

## Requirements

Create a REST API that:

1. Provides `GET /users/{id}`.
2. Searches for a user by ID.
3. Returns the user when found.
4. Throws `UserNotFoundException` when the user does not exist.
5. Uses `@ExceptionHandler` to handle `UserNotFoundException`.
6. Returns a clean error response instead of the default Whitelabel Error Page.

## Expected Behavior

### Existing User

```text
GET /users/101
```

Returns the user.

### Missing User

```text
GET /users/999
```

Should no longer show the default Whitelabel Error Page.

Instead, the custom exception handler should return a meaningful error response.

## Learning Goal

Understand how Spring MVC handles exceptions using `@ExceptionHandler`.