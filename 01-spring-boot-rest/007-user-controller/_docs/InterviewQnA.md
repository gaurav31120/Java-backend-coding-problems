# Problem 7 — Interview Q&A

## 1. What is a Controller in Spring Boot?

A Controller is a component that handles HTTP requests and returns responses.

It acts as an entry point between the client and the application's backend logic.

Typical flow:

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

---

## 2. What does @RestController do?

`@RestController` tells Spring that a class is a REST controller.

Its methods can handle HTTP requests and return response data directly.

Example:

```java
@RestController
public class UserController {
}
```

It is effectively a combination of `@Controller` and `@ResponseBody`.

---

## 3. What is @GetMapping?

`@GetMapping` maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping("/users")
public User getUser() {
    return new User(101, "Gaurav");
}
```

A request to:

```text
GET /users
```

will invoke the method.

---

## 4. What is @PathVariable?

`@PathVariable` extracts a value from the URL path.

Example:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) {
    return new User(id, "Gaurav");
}
```

For:

```text
/users/101
```

the value of `id` becomes:

```text
101
```

---

## 5. What is @RequestParam?

`@RequestParam` extracts values from query parameters.

Example:

```java
@GetMapping("/users")
public User search(@RequestParam("name") String name) {
    return new User(101, name);
}
```

For:

```text
/users?name=Gaurav
```

the value of `name` becomes:

```text
Gaurav
```

---

## 6. What is the difference between @PathVariable and @RequestParam?

`@PathVariable` reads values from the URL path.

Example:

```text
/users/101
```

`@RequestParam` reads values from the query string.

Example:

```text
/users?name=Gaurav
```

Simple distinction:

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

## 7. What is the difference between @Controller and @RestController?

`@Controller` is commonly used for Spring MVC controllers, especially when returning views.

`@RestController` is designed for REST APIs and automatically treats controller return values as response bodies.

Example:

```java
@RestController
public class UserController {
}
```

---

## 8. What is the responsibility of a Controller?

A Controller should primarily handle the HTTP layer.

It commonly:

- Receives requests
- Reads request data
- Calls business/application logic
- Returns responses

It should generally avoid containing large amounts of business logic.

---

## 9. Should business logic be written inside a Controller?

Generally, no.

Controllers should remain focused on HTTP/API concerns.

Business logic should usually be placed in a Service layer.

A common architecture is:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

We will introduce this architecture in later problems.

---

## 10. How does Spring know which method should handle a request?

Spring examines the request and compares it with the mappings defined by controller methods.

For example:

```java
@GetMapping("/users/{id}")
```

matches a GET request such as:

```text
/users/101
```

Spring then invokes the corresponding controller method.

---

## 11. What happens when /users/101 is requested?

The flow is approximately:

```text
HTTP Request
     ↓
Spring MVC
     ↓
Find matching controller mapping
     ↓
@GetMapping("/users/{id}")
     ↓
Extract id using @PathVariable
     ↓
Execute controller method
     ↓
Return User object
     ↓
Convert response to JSON
```

---

## 12. Can one Controller contain multiple endpoints?

Yes.

A controller can contain multiple request-handling methods.

For example:

```java
@GetMapping("/users")
public User getUsers() {
    ...
}

@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) {
    ...
}
```

Each method handles a different request mapping.

---

## 13. Why should Controllers be kept thin?

A thin Controller is easier to:

- Understand
- Test
- Maintain
- Modify

It should mainly translate HTTP requests into calls to application logic and then return the appropriate response.

Business logic belongs in appropriate application layers.

---

## 14. What does `{id}` mean in @GetMapping("/users/{id}")?

`{id}` is a path variable placeholder.

For example:

```text
/users/101
```

matches:

```java
@GetMapping("/users/{id}")
```

and:

```java
@PathVariable int id
```

receives:

```text
101
```

---

## 15. What is the main lesson from Problem 7?

The main lesson is:

> A Spring REST Controller handles HTTP requests and maps them to Java methods.

Important annotations learned:

```text
@RestController
@GetMapping
@PathVariable
@RequestParam
```

These form the basic building blocks of Spring REST APIs.