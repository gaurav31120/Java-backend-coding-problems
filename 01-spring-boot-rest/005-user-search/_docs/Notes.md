# Problem 5 — User Search

## 1. What We Learned

This problem introduced `@RequestParam`.

It is used when data is sent to a REST API through the URL query string.

Example:

```text
/users?name=Gaurav
```

Here:

- `/users` → API endpoint
- `name` → parameter name
- `Gaurav` → parameter value

---

## 2. @RequestParam

`@RequestParam` reads a value from the URL query parameter.

Example:

```java
@GetMapping("/users")
public User search(@RequestParam("name") String name) {
    User user = new User(101, name);
    return user;
}
```

If the request is:

```text
/users?name=Gaurav
```

Spring extracts:

```text
name = "Gaurav"
```

and passes it to the `search()` method.

---

## 3. @GetMapping

`@GetMapping` maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping("/users")
public User search(...) {
    ...
}
```

So when the client sends:

```text
GET /users
```

Spring calls the `search()` method.

---

## 4. @RestController

`@RestController` tells Spring that this class contains REST API endpoints.

Example:

```java
@RestController
public class UserController {
}
```

The returned Java object is automatically converted into JSON.

---

## 5. Complete Flow

Request:

```text
GET /users?name=Gaurav
```

Spring sees:

```java
@GetMapping("/users")
```

Then:

```java
@RequestParam("name")
```

extracts:

```text
Gaurav
```

Then the method creates:

```java
User user = new User(101, name);
```

Finally Spring converts the `User` object into JSON.

Response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## 6. @PathVariable vs @RequestParam

### @PathVariable

Used when the value is part of the URL path.

Example:

```text
/users/101
```

Java:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) {
    ...
}
```

### @RequestParam

Used when the value is a query parameter.

Example:

```text
/users?name=Gaurav
```

Java:

```java
@GetMapping("/users")
public User search(@RequestParam("name") String name) {
    ...
}
```

### Easy Memory Trick

```text
/users/101
       ↑
PathVariable
```

```text
/users?name=Gaurav
       ↑
RequestParam
```

---

## 7. Important Annotation Difference

### @RequestMapping

General-purpose mapping annotation.

Example:

```java
@RequestMapping("/users")
```

### @GetMapping

Specifically used for HTTP GET requests.

Example:

```java
@GetMapping("/users")
```

For a GET API, `@GetMapping` is usually clearer.

---

## 8. Current User Class

```java
public class User {

    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Spring/Jackson uses the getter methods to produce JSON.

---

## 9. Key Takeaways

Remember these four things:

```text
@RestController
    ↓
Creates REST API controller

@GetMapping
    ↓
Handles GET request

@RequestParam
    ↓
Reads query parameter

User object
    ↓
Converted to JSON response
```

The most important concept from this problem is:

> `@RequestParam` reads values from query parameters in the URL.