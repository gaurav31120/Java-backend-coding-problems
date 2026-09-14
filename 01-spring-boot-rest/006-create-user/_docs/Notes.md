# Problem 6 — Notes

## 1. Main Concept

Problem 6 introduces `@RequestBody`.

`@RequestBody` is used when the client sends data inside the HTTP request body, usually as JSON.

Example request:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Spring converts this JSON into a Java object.

---

## 2. @PostMapping

`@PostMapping` is used to handle HTTP POST requests.

Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

This method handles:

```text
POST /users
```

---

## 3. @RequestBody

`@RequestBody` tells Spring:

> Read the HTTP request body and convert it into the specified Java object.

Example:

```java
@RequestBody User user
```

If the client sends:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Spring creates a `User` object containing:

```text
id = 101
name = Gaurav
```

---

## 4. JSON to Java Object

The request flow is:

```text
Client
   ↓
JSON request body
   ↓
Spring HTTP message converter
   ↓
User Java object
   ↓
Controller method
```

Example:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

becomes approximately:

```java
User user = new User();
user.setId(101);
user.setName("Gaurav");
```

Spring/Jackson handles this conversion automatically.

---

## 5. Java Object to JSON

Our controller returns:

```java
return user;
```

Spring converts the Java object back into JSON for the HTTP response.

Flow:

```text
User Java object
      ↓
Spring/Jackson
      ↓
JSON response
```

Response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## 6. Why Do We Need a No-Argument Constructor?

Our `User` class contains:

```java
public User() {
}
```

For this exercise, this allows Jackson to create an empty `User` object and populate its fields while deserializing the incoming JSON.

We also have:

```java
public User(int id, String name) {
    this.id = id;
    this.name = name;
}
```

which is useful when creating a `User` manually in Java.

---

## 7. RequestParam vs RequestBody

### @RequestParam

Used for query parameters.

Example:

```text
GET /users?name=Gaurav
```

Java:

```java
@RequestParam("name") String name
```

### @RequestBody

Used for data inside the request body.

Example:

```text
POST /users
```

Body:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Java:

```java
@RequestBody User user
```

### Easy Memory Trick

```text
URL data
   ↓
@RequestParam
```

```text
Body data
   ↓
@RequestBody
```

---

## 8. Complete Controller

```java
@RestController
public class UserController {

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return user;
    }
}
```

This is the complete REST flow for this problem.

---

## 9. POST Request Example

Request:

```http
POST /users
Content-Type: application/json
```

Body:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Response:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## 10. Key Takeaways

Remember these concepts:

```text
@PostMapping
    ↓
Handles POST requests

@RequestBody
    ↓
Reads JSON from request body

JSON
    ↓
Converted to Java object

Java object
    ↓
Returned by controller

Java object
    ↓
Converted to JSON response
```

The most important point:

> Use `@RequestBody` when the client sends structured data inside the HTTP request body.