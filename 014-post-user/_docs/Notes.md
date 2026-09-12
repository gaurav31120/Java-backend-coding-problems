# Problem 14 — Notes

## POST in REST

`POST` is commonly used to create a new resource.

For this problem:

```text
POST /users
```

creates a new user.

---

## Request Body

The client sends user information as JSON:

```json
{
  "id": 103,
  "name": "Amit"
}
```

Spring uses `@RequestBody` to convert the JSON request body into a Java `User` object.

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.createUser(user);
}
```

---

## Why Does `User` Need a No-Argument Constructor?

The `User` class contains:

```java
public User() {
}
```

This allows Jackson to create a `User` object while converting incoming JSON into Java.

The parameterized constructor is still useful when we create `User` objects ourselves:

```java
public User(int id, String name) {
    this.id = id;
    this.name = name;
}
```

---

## POST Request Flow

```text
Client
  ↓
POST /users
  ↓
JSON Request Body
  ↓
@RequestBody
  ↓
UserController
  ↓
UserService
  ↓
UserRepository
  ↓
users.add(user)
  ↓
Created User
```

---

## Repository

The repository stores the received user:

```java
public void addUser(User user) {
    users.add(user);
}
```

The repository does not need to create another `User` object because it already receives the object that should be stored.

---

## Service

The Service delegates storage to the Repository:

```java
public User createUser(User user) {
    userRepository.addUser(user);
    return user;
}
```

---

## Controller

The Controller receives the request body and passes the User to the Service:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.createUser(user);
}
```

---

## GET vs POST

### GET

```text
GET /users
```

Used to retrieve users.

### POST

```text
POST /users
```

Used to create a new user.

CRUD mapping:

```text
POST   → Create
GET    → Read
PUT    → Update
DELETE → Delete
```

---

## Key Takeaway

The major new concept in Problem 14 is accepting JSON data from the client using:

```java
@RequestBody
```

and using that data to create and store a new resource.