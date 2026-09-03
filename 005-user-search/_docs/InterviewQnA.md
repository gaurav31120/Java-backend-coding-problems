# Problem 5 — Interview Q&A

## 1. What is @RequestParam in Spring Boot?

`@RequestParam` is used to read a value from a query parameter in the URL.

Example:

```text
/users?name=Gaurav
```

Java:

```java
public User search(@RequestParam("name") String name)
```

Here, Spring extracts `Gaurav` and stores it in the `name` variable.

---

## 2. What is the difference between @RequestParam and @PathVariable?

`@RequestParam` reads a query parameter.

Example:

```text
/users?name=Gaurav
```

`@PathVariable` reads a value from the URL path.

Example:

```text
/users/101
```

Example:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) {
    ...
}
```

Easy distinction:

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

## 3. What is @GetMapping?

`@GetMapping` maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping("/users")
public User search(...) {
    ...
}
```

When the client sends a GET request to `/users`, Spring invokes this method.

---

## 4. What is @RestController?

`@RestController` marks a class as a REST controller.

It allows the class to define REST API endpoints and return response data directly.

Example:

```java
@RestController
public class UserController {
}
```

The returned Java object can be converted into JSON automatically.

---

## 5. What happens when /users?name=Gaurav is requested?

The flow is:

```text
Client
  ↓
GET /users?name=Gaurav
  ↓
@GetMapping("/users")
  ↓
@RequestParam("name")
  ↓
name = "Gaurav"
  ↓
User object created
  ↓
Object converted to JSON
  ↓
Response sent to client
```

---

## 6. Is @RequestParam mandatory by default?

Yes, a request parameter is required by default.

For example:

```java
@RequestParam("name") String name
```

If the client calls:

```text
/users
```

without providing `name`, Spring normally returns a client error because the required parameter is missing.

---

## 7. How can a request parameter be made optional?

Use:

```java
@RequestParam(value = "name", required = false)
```

Example:

```java
@GetMapping("/users")
public User search(
        @RequestParam(value = "name", required = false) String name) {
    ...
}
```

Now the `name` parameter does not have to be present.

---

## 8. Can @RequestParam have a default value?

Yes.

Example:

```java
@RequestParam(value = "name", defaultValue = "Guest")
String name
```

If the client does not provide `name`, Spring uses:

```text
Guest
```

as the default value.

---

## 9. Why is @GetMapping preferred over @RequestMapping for GET APIs?

`@GetMapping` clearly communicates that the endpoint handles an HTTP GET request.

Example:

```java
@GetMapping("/users")
```

is more specific than:

```java
@RequestMapping("/users")
```

`@RequestMapping` can be used for different HTTP methods, while `@GetMapping` specifically represents GET.

---

## 10. What is a query parameter?

A query parameter is additional information provided after `?` in a URL.

Example:

```text
/users?name=Gaurav
```

Here:

```text
name = parameter name
Gaurav = parameter value
```

Multiple query parameters can also be sent:

```text
/users?name=Gaurav&city=Patna
```

---

## 11. What is the difference between URL path and query parameter?

Path:

```text
/users/101
```

The `101` identifies part of the resource path.

Query parameter:

```text
/users?name=Gaurav
```

The `name` parameter provides additional information for the request, such as search/filter criteria.

---

## 12. What happens if the query parameter has a different data type?

Spring performs type conversion when possible.

For example:

```java
@RequestParam("age") int age
```

For:

```text
/users?age=25
```

Spring converts `"25"` into the integer:

```text
25
```

If the value cannot be converted to the required type, Spring returns a client-side error.

---

## 13. Why did our API return JSON even though we returned a Java object?

Because the controller is a REST controller and Spring Boot uses HTTP message conversion to serialize the Java object into JSON.

For example:

```java
return new User(101, "Gaurav");
```

can become:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## 14. What is the main concept learned in Problem 5?

The main concept is:

> Use `@RequestParam` when an API needs to receive values through query parameters.

Example:

```java
@GetMapping("/users")
public User search(@RequestParam("name") String name) {
    return new User(101, name);
}
```