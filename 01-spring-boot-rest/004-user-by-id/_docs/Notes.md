# Problem 4 — User by ID

## 1. Path Variable

A path variable is a value included directly inside a URL path.

Example:

```text
/users/101
```

Here, `101` is the path variable.

A dynamic path is written as:

```text
/users/{id}
```

---

## 2. `@PathVariable`

`@PathVariable` extracts a value from the URL and provides it to the controller method.

Example:

```java
@GetMapping("/users/{id}")
public User user(@PathVariable("id") int id) {
    ...
}
```

For:

```text
/users/101
```

Spring provides:

```text
id = 101
```

---

## 3. Dynamic URL

The `{id}` part makes the endpoint dynamic.

The same endpoint can handle:

```text
/users/1
/users/101
/users/500
```

without creating separate controller methods.

---

## 4. Creating the User Object

The captured ID can be used to create a Java object.

```java
User user = new User(id, "Gaurav");
```

The value of `id` comes from the URL.

---

## 5. Object-to-JSON Conversion

The controller returns a `User` object.

Spring/Jackson converts the object into JSON.

```text
URL
 ↓
@PathVariable
 ↓
Java variable
 ↓
User object
 ↓
Jackson
 ↓
JSON response
```

---

## 6. Complete Controller

```java
@RestController
public class UserController {

    @RequestMapping("/users/{id}")
    public User user(@PathVariable("id") int id) {
        User user = new User(id, "Gaurav");
        return user;
    }
}
```

---

## 7. Request Flow

For:

```text
GET /users/101
```

the flow is:

```text
Client
   ↓
GET /users/101
   ↓
Spring matches /users/{id}
   ↓
@PathVariable extracts 101
   ↓
id = 101
   ↓
new User(101, "Gaurav")
   ↓
Jackson converts User → JSON
   ↓
HTTP Response
```

---

## 8. Important Concepts

- Path variable
- `@PathVariable`
- Dynamic URL
- REST controller
- HTTP GET
- Java object
- JSON serialization

---

## 9. Key Takeaways

- `{id}` represents a dynamic value in the URL.
- `@PathVariable` extracts that value.
- The extracted value can be used inside the controller.
- One endpoint can handle many different IDs.
- Spring/Jackson converts the returned Java object into JSON.

---

## 10. One-Line Revision

**`/users/{id}` + `@PathVariable` allows a controller to read a dynamic value directly from the URL.**