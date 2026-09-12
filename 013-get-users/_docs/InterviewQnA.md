# Problem 13 — Interview Q&A

## 1. What is the purpose of HTTP GET?

GET is used to retrieve data from a server.

Example:

```text
GET /users
```

retrieves users.

---

## 2. What does `/users` represent?

`/users` represents a collection resource containing users.

Therefore:

```text
GET /users
```

is used to retrieve the collection.

---

## 3. Why does `GET /users` return `List<User>`?

Because the endpoint represents a collection of users.

```java
public List<User> getAllUsers()
```

returns multiple `User` objects.

---

## 4. What is `@GetMapping`?

`@GetMapping` is a Spring MVC annotation used to map an HTTP GET request to a controller method.

Example:

```java
@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}
```

---

## 5. What is the request flow for `GET /users`?

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Users
```

The Controller receives the request, the Service handles the application logic, and the Repository provides the data.

---

## 6. Should a GET request modify server data?

Normally, no.

GET is intended for retrieving resources and should not be used to create, update, or delete data.

---

## 7. What is the difference between `GET /users` and `GET /users/101`?

```text
GET /users
```

retrieves the collection of users.

```text
GET /users/101
```

retrieves a specific user with ID `101`.

---

## 8. What is CRUD?

CRUD represents four basic data operations:

```text
C → Create
R → Read
U → Update
D → Delete
```

Common HTTP mappings are:

```text
POST   → Create
GET    → Read
PUT    → Update
DELETE → Delete
```

---

## 9. What HTTP status is normally returned for a successful GET?

A successful GET normally returns:

```text
200 OK
```

---

## 10. Why should the Controller call the Service instead of the Repository directly?

It keeps responsibilities separated.

The preferred structure is:

```text
Controller → Service → Repository
```

This makes the application easier to maintain and test.

---

## 11. How is Problem 13 different from Problem 12?

Problem 12 primarily introduced the layered architecture:

```text
Controller → Service → Repository
```

Problem 13 focuses on using that architecture for a CRUD **Read** operation:

```text
GET /users
```

The implementation is similar because the GET operation naturally uses the same layers.