# Problem 8 — Constructor Injection

## Topic

Spring Core — Dependency Injection

## Difficulty

🟡 Intermediate

## Objective

Learn how Spring manages dependencies and how to use constructor injection to provide a dependency to a Controller.

The goal is to understand why a class should receive its dependencies instead of creating them manually.

## Scenario

We have two classes:

```text
UserController
      ↓
UserService
```

`UserController` needs `UserService` to perform user-related operations.

Instead of creating `UserService` manually inside the Controller, use constructor injection.

## Requirements

### 1. Create UserService

Create:

```text
UserService.java
```

The class should initially contain a simple method that returns:

```text
User service is working
```

### 2. Create UserController

Create:

```text
UserController.java
```

The Controller should be a Spring REST controller.

### 3. Add UserService as a Dependency

`UserController` must contain a `UserService` field.

The dependency must be provided through the constructor.

Do not create the dependency using:

```java
new UserService()
```

inside the Controller.

### 4. Use Spring Dependency Injection

Make `UserService` a Spring-managed bean.

Spring should create the `UserService` object and inject it into `UserController`.

### 5. Create Test API

Create:

```text
GET /users/service
```

The endpoint should use the injected `UserService` and return:

```text
User service is working
```

## Expected Architecture

```text
Client
   ↓
UserController
   ↓
UserService
```

Spring manages the objects:

```text
Spring Container
      │
      ├── UserService
      │
      └── UserController
              │
              └── UserService dependency
```

## Restrictions

Do not use:

```java
UserService userService = new UserService();
```

inside `UserController`.

The purpose of this problem is to practice Dependency Injection.

## Concepts Learned

- Dependency
- Dependency Injection
- Constructor Injection
- Spring-managed Bean
- Spring IoC Container
- Controller-Service relationship

## Success Criteria

The problem is complete when:

- `UserService` is managed by Spring.
- `UserController` receives `UserService` through its constructor.
- The Controller does not manually create `UserService`.
- `GET /users/service` works successfully.
- The response is:

```text
User service is working
```