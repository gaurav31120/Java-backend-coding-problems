# Problem 10 — User Service

## Topic

Spring — Service Layer

## Difficulty

🟡 Intermediate

## Objective

Learn how to move business logic from a Controller into a dedicated Service class.

## Scenario

Our application currently handles user-related logic directly inside the Controller.

We want to introduce a Service layer.

The architecture should become:

```text
Client
  ↓
Controller
  ↓
UserService
  ↓
Business Logic
```

## Requirements

### 1. Create UserService

Create a Spring Service:

```text
UserService
```

Use the appropriate Spring annotation so Spring manages it as a Bean.

### 2. Add User Logic

Create a method in `UserService` that returns:

```text
User service is working
```

### 3. Create UserController

Create a REST Controller.

The Controller must receive `UserService` through constructor injection.

### 4. Create API

Create:

```text
GET /users/service
```

The Controller should call the Service method and return its result.

## Important Rule

The Controller should NOT contain the business message directly.

The message:

```text
User service is working
```

must come from `UserService`.

## Concepts Learned

- Service layer
- `@Service`
- Constructor Injection
- Dependency Injection
- Separation of responsibilities
- Controller → Service flow

## Success Criteria

The following request should return:

```text
User service is working
```

The request should flow through:

```text
GET /users/service
        ↓
UserController
        ↓
UserService
        ↓
"User service is working"
```