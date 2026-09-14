# Problem 17 — ResponseEntity — Status Handling

## 1. What is ResponseEntity?

`ResponseEntity` represents the complete HTTP response returned by a Spring REST controller.

It can control:

- Response body
- HTTP status
- HTTP headers

Example:

```java
ResponseEntity<User>
```

means the response contains a `User` body when one is available.

---

## 2. Why use ResponseEntity?

A controller that returns only:

```java
public User getUser(...)
```

does not explicitly control the HTTP status.

With `ResponseEntity`, we can return different responses depending on the result.

```text
User exists
    ↓
200 OK + User

User doesn't exist
    ↓
404 NOT FOUND
```

---

## 3. ResponseEntity.ok()

```java
return ResponseEntity.ok(user);
```

This returns:

```text
HTTP 200 OK
```

with the user as the response body.

---

## 4. ResponseEntity.notFound()

```java
return ResponseEntity.notFound().build();
```

This returns:

```text
HTTP 404 NOT FOUND
```

with no response body.

---

## 5. Complete Controller Logic

```java
@GetMapping("/users/{id}")
public ResponseEntity<User> getUser(@PathVariable int id) {

    User user = userService.findById(id);

    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

The Controller checks the Service result and chooses the appropriate HTTP response.

---

## 6. Layer Responsibility

```text
Repository
    ↓
Find User
    ↓
User / null

Service
    ↓
Delegate lookup
    ↓
User / null

Controller
    ↓
Interpret result
    ↓
ResponseEntity<User>
    ↓
200 OK / 404 NOT FOUND
```

The HTTP status decision belongs in the Controller because it is part of the HTTP/API layer.

---

## 7. @PathVariable

For:

```text
GET /users/101
```

Spring extracts:

```java
@PathVariable int id
```

as:

```text
id = 101
```

---

## 8. HTTP Status Codes Used

| Status | Meaning |
|---|---|
| `200 OK` | Request succeeded and user was found |
| `404 NOT FOUND` | Requested user does not exist |

---

## 9. Why `build()`?

`notFound()` creates a response builder for a `404 NOT FOUND` response.

```java
ResponseEntity.notFound().build();
```

`build()` completes the response without a body.

---

## 10. Important Learning

`ResponseEntity` allows a controller to return both:

```text
Status + Body
```

instead of returning only a Java object.

For example:

```java
return ResponseEntity.ok(user);
```

means:

```text
Status → 200 OK
Body   → user
```

---

## 11. Current Implementation

Initial users:

```text
101 → gaurav
102 → Rahul
```

Request:

```text
GET /users/101
```

returns:

```text
200 OK
```

Request:

```text
GET /users/999
```

returns:

```text
404 NOT FOUND
```

---

## 12. Quick Revision

```text
ResponseEntity<T>
       ↓
Complete HTTP response

ok(body)
       ↓
200 OK + body

notFound().build()
       ↓
404 NOT FOUND + no body
```