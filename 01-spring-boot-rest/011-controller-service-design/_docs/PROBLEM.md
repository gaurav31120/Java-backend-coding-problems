# Problem 11 — Controller-Service Design

## Topic

Architecture — Controller-Service Layers

## Difficulty

🟡 Intermediate

## Objective

Learn how to separate HTTP handling from business logic using Controller and Service layers.

## Scenario

We want to build a simple User API.

The Controller should handle the HTTP request, while the Service should handle the user-related business logic.

The architecture should be:

```text
Client
   ↓
UserController
   ↓
UserService
   ↓
Business Logic
```

## Requirements

### 1. Create UserService

Create a Spring Service named:

```text
UserService
```

It should contain the user-related logic.

### 2. Create UserController

Create a REST Controller named:

```text
UserController
```

The Controller must receive `UserService` through constructor injection.

### 3. Create User API

Create:

```text
GET /users/profile
```

The Controller should call the Service and return the Service result.

### 4. Service Response

The Service should return:

```text
User profile from service
```

## Important Rule

The Controller must NOT contain the message:

```text
User profile from service
```

The message must come from `UserService`.

## Concepts Learned

- Layered architecture
- Controller layer
- Service layer
- Separation of responsibilities
- Constructor Injection
- Dependency Injection
- Controller → Service communication

## Success Criteria

Calling:

```text
GET /users/profile
```

must return:

```text
User profile from service
```

The request must flow through:

```text
GET /users/profile
        ↓
UserController
        ↓
UserService
        ↓
"User profile from service"
```