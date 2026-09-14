# Problem 5 — User Search

## Objective

Create a Spring Boot REST API that searches for a user using a name provided as a request parameter.

## Requirements

Create the following REST endpoint:

```text
GET /users?name={name}
```

The `name` value must be read from the query parameter.

## Example Request

```text
GET /users?name=Gaurav
```

## Expected Response

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

The exact ID and name values may be chosen by the developer.

## Additional Tests

Test the endpoint with at least two different names.

Example:

```text
GET /users?name=Gaurav
GET /users?name=Rahul
```

## Technical Requirements

- Create a REST controller.
- Create a User class.
- Add `id` and `name` fields.
- Create a GET `/users` endpoint.
- Use `@RequestParam` to read the name.
- Use the received name when creating or finding the user.
- Return the User object as JSON.
- Test multiple request parameter values.

## Concepts Practiced

- REST API
- HTTP GET
- Query parameter
- Request parameter
- `@RequestParam`
- Java objects
- Object-to-JSON conversion

## Success Criteria

The request:

```text
GET /users?name=Gaurav
```

must return a JSON response containing the requested name.

## Completion Checklist

- [ ] Spring Boot Maven project created.
- [ ] User class created.
- [ ] User fields created.
- [ ] REST controller created.
- [ ] `/users` endpoint created.
- [ ] `@RequestParam` used.
- [ ] Multiple names tested.
- [ ] JSON response verified.
- [ ] Notes.md completed.
- [ ] InterviewQnA.md completed.
- [ ] Changes committed.
- [ ] Changes pushed to GitHub.