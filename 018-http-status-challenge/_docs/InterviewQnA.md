# Problem 18 — Interview Q&A

## 1. What is `ResponseEntity` in Spring Boot?

`ResponseEntity` represents the complete HTTP response.

It can control:

- Status code
- Response body
- HTTP headers

Example:

```java
return ResponseEntity.ok(user);
```

---

## 2. What is the difference between 200, 400, and 404?

### 200 OK

The request was valid and the resource was found.

### 400 BAD REQUEST

The client sent an invalid request.

Example:

```text
/users/0
```

when IDs must be positive.

### 404 NOT FOUND

The request was valid, but the requested resource does not exist.

Example:

```text
/users/999
```

when user `999` doesn't exist.

---

## 3. Why should HTTP status handling be in the Controller?

The Controller is responsible for translating application results into HTTP responses.

For example:

```text
Repository → User / null
Service → User / null
Controller → 200 / 404
```

This keeps the Service and Repository independent of HTTP.

---

## 4. Why shouldn't the Service return `ResponseEntity`?

`ResponseEntity` is an HTTP-specific abstraction.

If the Service returns `ResponseEntity`, business logic becomes tightly coupled to the web layer.

A cleaner design is:

```java
public User findById(int id)
```

and let the Controller decide:

```java
ResponseEntity.ok(user)
```

or:

```java
ResponseEntity.notFound().build()
```

---

## 5. What does `ResponseEntity.notFound().build()` do?

It creates a response with:

```text
404 NOT FOUND
```

and no response body.

---

## 6. What does `ResponseEntity.badRequest().build()` do?

It creates a response with:

```text
400 BAD REQUEST
```

and no response body.

---

## 7. Why validate `id <= 0` before calling the Service?

Because the request is already invalid.

There is no need to perform a Service or Repository lookup for an invalid ID.

This is an example of early validation.

---

## 8. What does `@PathVariable` do?

`@PathVariable` extracts a value from the URL path.

Example:

```java
@GetMapping("/users/{id}")
public ResponseEntity<User> findById(@PathVariable int id)
```

For:

```text
GET /users/101
```

Spring assigns:

```text
id = 101
```

---

## 9. What happens when the Repository returns `null`?

The Service returns the `null` result to the Controller.

The Controller interprets it as "user not found" and returns:

```text
404 NOT FOUND
```

---

## 10. What is the responsibility of each layer?

### Controller

HTTP request and response handling.

### Service

Business/application logic.

### Repository

Data access.

This separation improves maintainability and testability.

---

## 11. Is returning `null` from a Repository always the best approach?

Not necessarily.

For a simple learning exercise, returning `null` is acceptable.

In production applications, alternatives such as `Optional<User>` can make the absence of a value more explicit.

Example:

```java
Optional<User> findById(int id)
```

---

## 12. Can `ResponseEntity` also set HTTP headers?

Yes.

`ResponseEntity` can contain:

- HTTP status
- Headers
- Body

This makes it useful when the Controller needs precise control over the HTTP response.

---

## 13. What is the main lesson from this problem?

The main lesson is understanding how application results are converted into meaningful HTTP responses.

```text
Valid request + resource exists
→ 200

Valid request + resource missing
→ 404

Invalid request
→ 400
```