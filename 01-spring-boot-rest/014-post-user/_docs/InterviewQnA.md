# Problem 14 — Interview Q&A

## 1. What is POST used for?

POST is commonly used to create a new resource on the server.

Example:

```text
POST /users
```

creates a new user.

---

## 2. What is `@RequestBody`?

`@RequestBody` tells Spring to take the HTTP request body and convert it into a Java object.

Example:

```java
public User createUser(@RequestBody User user)
```

JSON such as:

```json
{
  "id": 103,
  "name": "Amit"
}
```

is converted into a `User` object.

---

## 3. Why does the User class have a no-argument constructor?

Jackson commonly uses a no-argument constructor when creating an object from JSON.

Therefore:

```java
public User() {
}
```

is provided for JSON deserialization.

---

## 4. What is JSON deserialization?

Deserialization is the process of converting JSON data into a Java object.

For example:

```json
{
  "id": 103,
  "name": "Amit"
}
```

becomes:

```java
User user
```

---

## 5. What is the difference between GET and POST?

```text
GET  → retrieve data
POST → create data
```

For example:

```text
GET /users
POST /users
```

The GET request retrieves users, while POST sends a new user to the server.

---

## 6. Explain the POST request flow in this application.

```text
Client
  ↓
POST /users
  ↓
@RequestBody
  ↓
UserController
  ↓
UserService
  ↓
UserRepository
  ↓
List<User>
```

---

## 7. Why does the Service call the Repository?

The Service is responsible for application logic and delegates data storage to the Repository.

```java
userRepository.addUser(user);
```

This keeps the Controller separated from the data-access layer.

---

## 8. Why does `addUser()` accept a `User` object?

The Controller receives JSON and Spring converts it into a `User`.

The same `User` object can then be passed through the Service to the Repository:

```text
JSON
 ↓
User
 ↓
Service
 ↓
Repository
```

---

## 9. Why don't we create another User object inside `addUser()`?

The Repository already receives the `User` object that should be stored.

Therefore:

```java
users.add(user);
```

is sufficient.

Creating another object would be unnecessary for this requirement.

---

## 10. What is the typical successful status code for POST?

A successful resource creation is commonly represented by:

```text
201 Created
```

However, this simple implementation currently returns the default successful response unless `ResponseEntity` or another mechanism is used to explicitly return `201 Created`.

---

## 11. Is POST idempotent?

No. POST is generally not idempotent.

Sending the same POST request multiple times can create multiple resources.

For example:

```text
POST /users
```

with the same user data could potentially create multiple users.

---

## 12. What is the role of the Controller?

The Controller handles HTTP requests.

In this problem it:

1. Receives the POST request.
2. Reads the JSON using `@RequestBody`.
3. Passes the User to the Service.
4. Returns the created User.

---

## 13. What is the role of the Repository?

The Repository is responsible for data access.

In this problem it stores users in an in-memory list:

```java
users.add(user);
```

---

## 14. How is Problem 14 different from Problem 13?

Problem 13 retrieves existing users:

```text
GET /users
```

Problem 14 creates a new user:

```text
POST /users
```

Problem 14 introduces receiving JSON through `@RequestBody` and storing the new resource.