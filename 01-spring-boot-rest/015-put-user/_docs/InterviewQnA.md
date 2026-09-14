# Problem 15 — PUT User — Interview Q&A

## 1. What is PUT in REST?

**Answer:**

PUT is an HTTP method commonly used to update an existing resource.

Example:

```text
PUT /users/101
```

The request identifies user `101` and sends the updated representation in the request body.

---

## 2. What is the difference between PUT and POST?

**Answer:**

- `POST` is commonly used to create a new resource.
- `PUT` is commonly used to update or replace an existing resource.

Example:

```text
POST /users
PUT /users/101
```

---

## 3. What does `@PutMapping` do?

**Answer:**

`@PutMapping` maps an HTTP PUT request to a controller method.

```java
@PutMapping("/users/{id}")
```

---

## 4. What does `@PathVariable` do?

**Answer:**

It extracts a value from the URL path.

```text
/users/101
```

```java
@PathVariable int id
```

The value of `id` becomes `101`.

---

## 5. What does `@RequestBody` do?

**Answer:**

`@RequestBody` tells Spring to convert the incoming request body, usually JSON, into a Java object.

```java
@RequestBody User updatedUser
```

---

## 6. Why do we use both `@PathVariable` and `@RequestBody`?

**Answer:**

They provide two different pieces of information.

```text
Path variable → Which resource?
Request body  → What data should be updated?
```

Example:

```text
PUT /users/101
```

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

---

## 7. Why does the repository call `findById()` before updating?

**Answer:**

Because we need to locate the existing object before changing its data.

```java
User existingUser = findById(id);
```

Then:

```java
existingUser.setName(updatedUser.getName());
```

---

## 8. Why are we not changing the user's ID?

**Answer:**

The ID identifies the resource being updated.

For:

```text
PUT /users/101
```

the resource is user `101`.

Therefore, the path ID is treated as the identity of the resource, while the body provides updated data.

---

## 9. What happens if the user does not exist?

**Answer:**

In this beginner implementation, `findById()` returns `null`, so `updateUser()` also returns `null`.

A production API should normally return an appropriate HTTP status such as `404 Not Found`.

---

## 10. Why is constructor injection used?

**Answer:**

Constructor injection makes dependencies explicit and allows Spring to provide the required dependency when creating the object.

Example:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

---

## 11. Explain the complete PUT request flow.

**Answer:**

```text
Client
  ↓
PUT /users/101
  ↓
UserController
  ↓
@PathVariable → 101
@RequestBody → User object
  ↓
UserService
  ↓
UserRepository
  ↓
findById(101)
  ↓
Update existing user
  ↓
Return updated User
```

---

## 12. Is PUT idempotent?

**Answer:**

HTTP PUT is designed to be idempotent.

If the same PUT request is sent multiple times, the intended result should remain the same.

For example, repeatedly sending:

```text
PUT /users/101
```

with:

```json
{
  "id": 101,
  "name": "Gaurav Kumar"
}
```

should leave user `101` as `Gaurav Kumar`.

---

## 13. What is the role of the Service layer in this problem?

**Answer:**

The Service layer separates business/application logic from the HTTP layer and repository layer.

Here it delegates the update operation to the repository:

```java
return userRepository.updateUser(id, updatedUser);
```

---

## 14. Why should the Controller not directly manipulate the list?

**Answer:**

The Controller should focus on handling HTTP requests and responses.

Data access belongs in the Repository layer, while application/business logic belongs in the Service layer.

This separation makes the application easier to maintain and test.

---

## 15. What HTTP status should a successful PUT normally return?

**Answer:**

A successful update commonly returns `200 OK` when the updated representation is returned.

`204 No Content` can also be used when there is no response body.

Proper status handling will be explored later in the curriculum.