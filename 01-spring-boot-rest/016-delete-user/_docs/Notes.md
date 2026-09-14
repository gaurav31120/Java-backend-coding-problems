# Problem 16 — DELETE User

## 1. What is DELETE?

`DELETE` is an HTTP method used to remove an existing resource.

Example:

```text
DELETE /users/101
```

Here, `101` identifies the user that should be deleted.

---

## 2. `@DeleteMapping`

Spring uses `@DeleteMapping` to map an HTTP DELETE request to a controller method.

```java
@DeleteMapping("/users/{id}")
public User deleteUser(@PathVariable int id) {
    return userService.deleteUser(id);
}
```

---

## 3. `@PathVariable`

`@PathVariable` reads a value from the URL.

For:

```text
/users/101
```

```java
@PathVariable int id
```

gives:

```text
id = 101
```

---

## 4. Removing an Element from a List

Our users are stored in an in-memory `List<User>`.

To safely remove a user while iterating, we use an `Iterator`.

```java
Iterator<User> iterator = users.iterator();

while (iterator.hasNext()) {
    User user = iterator.next();

    if (user.getId() == id) {
        iterator.remove();
        return user;
    }
}

return null;
```

---

## 5. Why Use `Iterator`?

Removing an element directly from a list while using an enhanced `for` loop can cause:

```text
ConcurrentModificationException
```

`Iterator.remove()` safely removes the element currently being visited.

---

## 6. Important Iterator Methods

| Method | Purpose |
|---|---|
| `hasNext()` | Checks whether another element exists |
| `next()` | Returns the next element |
| `remove()` | Safely removes the current element |

Think:

```text
hasNext() → Is there another item?
next()    → Give me the next item.
remove()  → Remove this item.
```

---

## 7. Layered DELETE Flow

```text
HTTP DELETE Request
       ↓
UserController
       ↓
UserService
       ↓
UserRepository
       ↓
Iterator
       ↓
Remove User
       ↓
Return deleted User
```

### Controller

Handles the HTTP DELETE request.

### Service

Delegates the delete operation.

### Repository

Finds and removes the user from the list.

---

## 8. Current Implementation

Initial users:

```text
101 → Gaurav
102 → Rahul
```

Request:

```text
DELETE /users/101
```

After deletion:

```text
102 → Rahul
```

The deleted user is returned in the response.

---

## 9. Non-Existing User

If the requested ID does not exist:

```text
DELETE /users/999
```

the Repository returns:

```java
null
```

Proper HTTP status handling will be introduced later using `ResponseEntity` and exception handling.

---

## 10. Key Annotations

| Annotation | Purpose |
|---|---|
| `@RestController` | Defines a REST controller |
| `@DeleteMapping` | Maps HTTP DELETE requests |
| `@PathVariable` | Reads a value from the URL |
| `@Service` | Defines the service layer |
| `@Repository` | Defines the repository layer |

---

## 11. Quick Revision

```text
DELETE
   ↓
Remove an existing resource

@PathVariable
   ↓
Read the ID from the URL

Iterator
   ↓
Safely traverse and remove from List

Repository
   ↓
Delete the matching user
```