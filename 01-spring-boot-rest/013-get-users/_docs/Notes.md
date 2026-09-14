# Problem 13 — Notes

## GET in REST

`GET` is an HTTP method used to retrieve data from a server.

For this problem:

```text
GET /users
```

means:

> Retrieve all users.

---

## Collection Endpoint

`/users` represents a collection of users.

The response is therefore a `List<User>` rather than a single `User`.

```java
@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}
```

---

## GET Request Flow

The request flows through the application layers:

```text
Client
  ↓
GET /users
  ↓
UserController
  ↓
UserService
  ↓
UserRepository
  ↓
List<User>
  ↓
JSON Response
```

---

## Controller

The Controller handles the HTTP request:

```java
@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}
```

`@GetMapping` maps an HTTP GET request to a Java method.

---

## Service

The Service requests the users from the Repository:

```java
public List<User> getAllUsers() {
    return userRepository.getAllUsers();
}
```

---

## Repository

The Repository stores the users in memory:

```java
private List<User> users = new ArrayList<>();
```

The repository returns the collection:

```java
public List<User> getAllUsers() {
    return users;
}
```

---

## GET Should Not Modify Data

A GET request is intended for retrieving resources.

For example:

```text
GET /users
```

should not create, update, or delete users.

CRUD operations will be introduced progressively:

```text
GET    → Read
POST   → Create
PUT    → Update
DELETE → Delete
```

---

## Collection vs Single Resource

These two endpoints represent different things:

```text
GET /users
```

→ retrieve the collection of users.

```text
GET /users/101
```

→ retrieve one specific user.

---

## Key Takeaway

Problem 12 focused primarily on **layered architecture**.

Problem 13 focuses on the **GET operation in CRUD and retrieving a collection of resources**.

The implementation is intentionally similar because both concepts use the same basic flow:

```text
Controller → Service → Repository
```