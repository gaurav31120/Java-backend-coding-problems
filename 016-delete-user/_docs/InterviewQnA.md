# Problem 16 — DELETE User — Interview Q&A

## 1. What is the DELETE HTTP method?

**Answer:**

DELETE is an HTTP method used to remove an existing resource.

Example:

```text
DELETE /users/101
```

---

## 2. What is `@DeleteMapping`?

**Answer:**

`@DeleteMapping` maps an HTTP DELETE request to a Spring controller method.

```java
@DeleteMapping("/users/{id}")
```

---

## 3. What is `@PathVariable`?

**Answer:**

`@PathVariable` extracts a value from the URL path.

For:

```text
/users/101
```

the value of:

```java
@PathVariable int id
```

is `101`.

---

## 4. Why do we use an Iterator when deleting from a List?

**Answer:**

An Iterator allows us to safely remove the current element while iterating through a collection.

Using `users.remove()` directly inside an enhanced `for` loop can cause `ConcurrentModificationException`.

---

## 5. What does `iterator.hasNext()` do?

**Answer:**

It checks whether another element is available in the collection.

```java
while (iterator.hasNext())
```

continues while there is another user to process.

---

## 6. What does `iterator.next()` do?

**Answer:**

It returns the next element from the collection and advances the iterator.

```java
User user = iterator.next();
```

---

## 7. What does `iterator.remove()` do?

**Answer:**

It safely removes the element most recently returned by `next()`.

```java
iterator.remove();
```

---

## 8. What happens when the requested user does not exist?

**Answer:**

In this beginner implementation, the Repository returns `null`.

A production API should normally return an appropriate HTTP status such as `404 Not Found`.

---

## 9. Explain the DELETE request flow.

**Answer:**

```text
Client
  ↓
DELETE /users/101
  ↓
UserController
  ↓
@PathVariable → 101
  ↓
UserService
  ↓
UserRepository
  ↓
Find matching user
  ↓
Iterator.remove()
  ↓
Return deleted User
```

---

## 10. Why shouldn't the Controller directly remove the user?

**Answer:**

The Controller should focus on handling HTTP requests.

The Service layer coordinates the operation, while the Repository handles data access.

This separation follows layered architecture.

---

## 11. Is DELETE idempotent?

**Answer:**

DELETE is generally designed to be idempotent.

Sending the same DELETE request multiple times should result in the resource remaining deleted.

The exact HTTP response can differ between requests, but the intended resource state remains the same.

---

## 12. What is constructor injection?

**Answer:**

Constructor injection provides required dependencies through the class constructor.

Example:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

Spring creates the dependency and passes it to the constructor.

---

## 13. What is the difference between DELETE and POST?

**Answer:**

- `POST` is commonly used to create a resource or trigger an operation.
- `DELETE` is used to remove a resource.

Example:

```text
POST   /users
DELETE /users/101
```

---

## 14. What HTTP status would normally be returned after a successful DELETE?

**Answer:**

A successful DELETE commonly returns:

```text
204 No Content
```

when there is no response body.

It can also return:

```text
200 OK
```

when a response body, such as the deleted resource, is returned.

---

## 15. What is the role of the Repository in this problem?

**Answer:**

The Repository manages the in-memory user data.

For DELETE, it:

1. Iterates through the users.
2. Finds the matching ID.
3. Removes the user.
4. Returns the deleted user.