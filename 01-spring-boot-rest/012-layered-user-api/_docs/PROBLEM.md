# Problem 12 — Layered User API

## Topic

Architecture — Repository Layer

## Difficulty

🟠 Intermediate

## Objective

Learn how to introduce a Repository layer and build a basic layered User API.

## Scenario

Our application currently has:

```text
Controller
   ↓
Service
```

We now want to introduce a Repository layer:

```text
Controller
   ↓
Service
   ↓
Repository
```

Each layer should have a clear responsibility.

## Requirements

### 1. Create UserRepository

Create a repository class named:

```text
UserRepository
```

It should store users in memory.

Do not use a database or JPA yet.

### 2. Create UserService

Create:

```text
UserService
```

as a Spring Service.

The Service must depend on `UserRepository`.

Use constructor injection.

### 3. Create UserController

Create:

```text
UserController
```

as a REST Controller.

The Controller must depend on `UserService`.

Use constructor injection.

### 4. Create User API

Create:

```text
GET /users
```

The request should flow through all three layers:

```text
GET /users
   ↓
UserController
   ↓
UserService
   ↓
UserRepository
```

### 5. User Data

The Repository should provide two users:

```text
101 - Gaurav
102 - Rahul
```

The API should return both users as JSON.

## Important Rule

Each layer should have a separate responsibility.

```text
Controller  → handles HTTP request

Service     → handles application/business logic

Repository  → handles user data access
```

The Controller must not directly access the Repository.

## Concepts Learned

- Layered architecture
- Repository layer
- Separation of responsibilities
- Constructor Injection
- Dependency Injection
- In-memory data
- Controller → Service → Repository flow

## Success Criteria

Calling:

```text
GET /users
```

should return both users.

The request must flow through:

```text
Controller
    ↓
Service
    ↓
Repository
```

No database or JPA is required for this problem.