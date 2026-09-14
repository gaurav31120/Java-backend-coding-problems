# Problem 21 — Entity-DTO Mapper

## Objective

Create a dedicated Mapper component that converts a `User` model into a `UserResponse` DTO instead of performing the mapping directly inside the Service.

## Scenario

The application has a `User` model containing:

- id
- name
- email

The API should return only:

- id
- name

Instead of manually creating `UserResponse` inside the Service, introduce a `UserMapper`.

## Requirements

1. Create a `User` model.
2. Create a `UserResponse` DTO.
3. Create an in-memory `UserRepository`.
4. Store at least two users:
    - 101 Gaurav — gaurav@gmail.com
    - 102 Priya — priya@gmail.com
5. Create a `UserMapper`.
6. The Mapper must convert:
    - `User → UserResponse`
7. Register the Mapper as a Spring component.
8. Inject the Mapper into the Service using constructor injection.
9. The Service should use the Mapper instead of manually constructing `UserResponse`.
10. Create a GET endpoint:
    - `GET /users/{id}`
11. Return the `UserResponse` from the Controller.

## Expected Response

For:

`GET /users/101`

the response should contain:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

The `email` must not be exposed.

## Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
User

Service
    ↓
UserMapper
    ↓
UserResponse
```

## Constraints

- Use Java 21.
- Use Spring Boot.
- Use Spring Web.
- Use constructor injection.
- Do not use Lombok.
- Do not use MapStruct or other mapping libraries.
- Implement the Mapper manually.
- Keep Controller, Service, Repository and Mapper responsibilities separate.

## Learning Goals

By completing this problem, you should understand:

- What DTO mapping means.
- Why mapping logic can be separated from the Service.
- What a Mapper is.
- How to create a Spring-managed Mapper.
- How dependency injection works with a Mapper.
- How to convert an internal model into an API response DTO.

## Completion Criteria

- Application starts successfully.
- `GET /users/101` returns the expected response.
- Email is not exposed.
- Service uses `UserMapper`.
- Mapping logic is not manually written inside the Controller.
- Project follows the Controller → Service → Repository architecture.