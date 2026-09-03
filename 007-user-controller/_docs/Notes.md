# Problem 7 — Notes

## 1. What Is a Controller?

A Controller is responsible for receiving HTTP requests and returning HTTP responses.

In a typical Spring Boot application:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

In this problem, we are intentionally using only the Controller layer.

The Service and Repository layers will be introduced later.

---

## 2. @RestController

`@RestController` tells Spring that a class is a REST controller.

Example:

```java
@RestController
public class UserController {
}
```

Spring detects this class and allows its methods to handle HTTP requests.

Returned Java objects are converted into response data such as JSON.

---

## 3. @GetMapping

`@GetMapping` maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping("/users")
public User getUser() {
    return new User(101, "Gaurav");
}
```

When the client sends:

```text
GET /users
```

Spring calls the `getUser()` method.

---

## 4. Controller Method

A controller method contains the code that executes when a matching HTTP request arrives.

Example:

```java
@GetMapping("/users")
public User getUser() {
    User user = new User(101, "Gaurav");
    return user;
}
```

The method:

1. Receives the request through Spring's mapping.
2. Creates a `User` object.
3. Returns the object.
4. Spring converts the object into JSON.

---

## 5. @PathVariable

`@PathVariable` extracts a value from the URL path.

Example:

```java
@GetMapping("/users/{id}")
public User searchUser(@PathVariable int id) {
    User user = new User(id, "Gaurav");
    return user;
}
```

For:

```text
GET /users/101
```

Spring extracts:

```text
id = 101
```

For:

```text
GET /users/999
```

Spring extracts:

```text
id = 999
```

---

## 6. URL Template

In:

```text
/users/{id}
```

`{id}` represents a variable part of the URL.

The actual request might be:

```text
/users/101
```

or:

```text
/users/500
```

The value is captured by:

```java
@PathVariable int id
```

---

## 7. @RequestParam

`@RequestParam` extracts a value from a query parameter.

Example:

```text
/users?name=Gaurav
```

Java:

```java
@RequestParam("name") String name
```

Spring extracts:

```text
name = "Gaurav"
```

---

## 8. @PathVariable vs @RequestParam

### Path Variable

```text
/users/101
```

Uses:

```java
@PathVariable
```

The value is part of the URL path.

### Request Parameter

```text
/users?name=Gaurav
```

Uses:

```java
@RequestParam
```

The value is part of the query string.

### Easy Memory Trick

```text
/users/101
       ↑
@PathVariable
```

```text
/users?name=Gaurav
       ↑
@RequestParam
```

---

## 9. Complete Controller Example

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public User getUser() {
        User user = new User(101, "Gaurav");
        return user;
    }

    @GetMapping("/users/{id}")
    public User searchUser(@PathVariable int id) {
        User user = new User(id, "Gaurav");
        return user;
    }
}
```

This controller exposes two GET APIs:

```text
GET /users
GET /users/{id}
```

---

## 10. Request Flow

For:

```text
GET /users/101
```

the flow is:

```text
Client
   ↓
HTTP GET /users/101
   ↓
Spring MVC
   ↓
UserController
   ↓
@GetMapping("/users/{id}")
   ↓
@PathVariable
   ↓
id = 101
   ↓
User object
   ↓
JSON response
```

---

## 11. Controller Responsibility

A Controller should primarily deal with the HTTP layer.

Typical controller responsibilities include:

- Receiving HTTP requests
- Reading path variables
- Reading query parameters
- Reading request bodies
- Calling appropriate application logic
- Returning HTTP responses

Business logic should eventually move into the Service layer.

We will learn that separation in later problems.

---

## 12. Key Takeaways

Remember:

```text
@RestController
    ↓
Marks a class as a REST controller

@GetMapping
    ↓
Handles GET requests

@PathVariable
    ↓
Reads values from the URL path

@RequestParam
    ↓
Reads values from query parameters
```

The main lesson:

> A Spring Controller is responsible for handling the HTTP/API layer of an application.