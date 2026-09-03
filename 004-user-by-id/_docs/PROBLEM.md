# Problem 4 — User by ID

## Objective

Create a Spring Boot REST API that retrieves a user using an ID provided in the URL.

## Requirements

Create the following REST endpoint:

```text
GET /users/{id}
```

The `{id}` must be captured from the URL and used by the controller.

## Example Request

```text
GET /users/101
```

## Expected Response

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

The exact user name may be chosen by the developer.

## Additional Examples

```text
GET /users/1
GET /users/25
GET /users/500
```

The returned `id` should match the ID provided in the URL.

## Technical Requirements

- Create a REST controller.
- Create a User class.
- Add `id` and `name` fields to User.
- Create a GET `/users/{id}` endpoint.
- Capture the ID using `@PathVariable`.
- Create and return a User object.
- Verify that the ID in the response matches the ID in the URL.
- Test multiple different IDs.

## Concepts Practiced

- REST API
- HTTP GET
- Path variables
- `@PathVariable`
- Dynamic URL values
- Java objects
- Object-to-JSON conversion

## Success Criteria

The following request:

```text
GET /users/101
```

returns JSON containing:

```json
{
  "id": 101
}
```

If the request changes to:

```text
GET /users/500
```

the response should contain:

```json
{
  "id": 500
}
```

## Completion Checklist

- [ ] Spring Boot Maven project created.
- [ ] User class created.
- [ ] User fields created.
- [ ] REST controller created.
- [ ] `/users/{id}` endpoint created.
- [ ] `@PathVariable` used.
- [ ] User object returned.
- [ ] Multiple IDs tested.
- [ ] Notes.md completed.
- [ ] InterviewQnA.md completed.
- [ ] Changes committed.
- [ ] Changes pushed to GitHub.