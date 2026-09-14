# Problem 17 — ResponseEntity — Interview Q&A

## 1. What is ResponseEntity in Spring Boot?

**Answer:**

`ResponseEntity` represents the complete HTTP response returned by a controller.

It allows us to control the response body, HTTP status, and headers.

---

## 2. Why would you use ResponseEntity instead of returning a User directly?

**Answer:**

Returning `User` focuses mainly on the response body.

`ResponseEntity<User>` allows the Controller to explicitly control the HTTP status as well.

For example:

```java
return ResponseEntity.ok(user);
```

returns `200 OK`.

```java
return ResponseEntity.notFound().build();
```

returns `404 NOT FOUND`.

---

## 3. What does `ResponseEntity.ok(user)` do?

**Answer:**

It creates a response with:

```text
HTTP 200 OK
```

and the supplied `user` as the response body.

---

## 4. What does `ResponseEntity.notFound().build()` do?

**Answer:**

It creates:

```text
HTTP 404 NOT FOUND
```

with no response body.

---

## 5. Why is `ResponseEntity` used in the Controller instead of the Repository?

**Answer:**

The Repository handles data access.

The Controller handles HTTP requests and responses.

HTTP status codes are therefore the responsibility of the Controller/API layer.

---

## 6. What does `@PathVariable` do?

**Answer:**

It extracts a value from the URL path.

For:

```text
GET /users/101
```

```java
@PathVariable int id
```

receives:

```text
101
```

---

## 7. Explain the complete request flow.

**Answer:**

```text
Client
  ↓
GET /users/101
  ↓
UserController
  ↓
@PathVariable → 101
  ↓
UserService
  ↓
UserRepository
  ↓
Find User
  ↓
Controller receives User / null
  ↓
ResponseEntity
  ↓
200 OK / 404 NOT FOUND
```

---

## 8. What happens when the user exists?

**Answer:**

The Repository returns the user.

The Controller then executes:

```java
return ResponseEntity.ok(user);
```

The client receives:

```text
200 OK
```

with the user in the response body.

---

## 9. What happens when the user does not exist?

**Answer:**

The Repository returns `null`.

The Controller detects this and returns:

```java
return ResponseEntity.notFound().build();
```

The client receives:

```text
404 NOT FOUND
```

---

## 10. What does `build()` mean in `notFound().build()`?

**Answer:**

`notFound()` creates a response builder configured for `404 NOT FOUND`.

`build()` creates the final `ResponseEntity` without a response body.

---

## 11. Can ResponseEntity contain headers?

**Answer:**

Yes.

`ResponseEntity` can represent:

```text
Body
Status
Headers
```

This makes it useful when an API needs more control over the HTTP response.

---

## 12. What is the difference between 200 and 404?

**Answer:**

`200 OK` means the request was successfully processed and the requested resource was found.

`404 NOT FOUND` means the requested resource could not be found.

---

## 13. Why should the Service return User instead of ResponseEntity?

**Answer:**

The Service should generally remain independent of HTTP concerns.

It deals with application/business operations.

`ResponseEntity` is an HTTP-specific concept, so the Controller should translate the Service result into an HTTP response.

---

## 14. Is ResponseEntity mandatory for every REST endpoint?

**Answer:**

No.

Spring can automatically create HTTP responses when a controller directly returns objects.

`ResponseEntity` is useful when we need explicit control over status codes, headers, or response bodies.

---

## 15. What is the advantage of layered architecture here?

**Answer:**

Each layer has a clear responsibility:

```text
Controller   → HTTP/API handling
Service      → Application/business logic
Repository   → Data access
```

This separation makes the application easier to maintain, test, and extend.

---

## 16. What status should be returned when a requested user is missing?

**Answer:**

A REST API would normally return:

```text
404 NOT FOUND
```

because the requested resource does not exist.