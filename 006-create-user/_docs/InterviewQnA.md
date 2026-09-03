# Problem 6 — Interview Q&A

## 1. What is @RequestBody?

`@RequestBody` is a Spring MVC annotation used to bind the HTTP request body to a Java object.

Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

If the request contains JSON:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Spring converts it into a `User` object.

---

## 2. What is the difference between @RequestParam and @RequestBody?

`@RequestParam` reads data from query parameters.

Example:

```text
/users?name=Gaurav
```

```java
@RequestParam("name") String name
```

`@RequestBody` reads data from the HTTP request body.

Example:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

```java
@RequestBody User user
```

---

## 3. What is @PostMapping?

`@PostMapping` maps an HTTP POST request to a controller method.

Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

It is commonly used when creating or submitting data.

---

## 4. Why is POST commonly used for creating resources?

POST is commonly used when the client sends data to the server to create or process a resource.

For example:

```text
POST /users
```

with:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

The server receives the user information and can create a new user.

---

## 5. How does Spring convert JSON into a Java object?

Spring uses HTTP message converters.

For JSON, Spring Boot commonly uses Jackson.

The converter reads the JSON request body and deserializes it into the Java class specified by `@RequestBody`.

Example:

```java
@RequestBody User user
```

The JSON is converted into a `User` object.

---

## 6. How does Spring convert a Java object into JSON?

When a controller returns a Java object, Spring uses an HTTP message converter to serialize the object into JSON.

Example:

```java
return user;
```

can produce:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

---

## 7. What is serialization?

Serialization is the process of converting an object into a format suitable for transmission or storage.

In a REST API, a Java object can be serialized into JSON.

Example:

```text
Java Object
     ↓
Serialization
     ↓
JSON
```

---

## 8. What is deserialization?

Deserialization is the reverse process.

JSON is converted into a Java object.

Example:

```text
JSON
 ↓
Deserialization
 ↓
Java Object
```

`@RequestBody` commonly involves this process.

---

## 9. Why does the User class have a no-argument constructor?

For this exercise, the no-argument constructor allows Jackson to instantiate the `User` object before populating its fields from the JSON request.

Example:

```java
public User() {
}
```

---

## 10. Can @RequestBody be used with GET?

Technically, HTTP does not universally forbid a body on GET, but relying on a request body for GET APIs is generally discouraged.

For normal REST API design:

```text
GET
 ↓
Retrieve data
```

and:

```text
POST
 ↓
Send data / create resource
```

For our learning path, we will normally use `@RequestBody` with POST, PUT, or PATCH requests when structured request data is required.

---

## 11. What happens if the JSON field name does not match the Java field?

By default, Jackson maps JSON properties to Java properties using their names.

For example:

```json
{
  "name": "Gaurav"
}
```

maps naturally to:

```java
private String name;
```

If the names differ, Jackson mapping can be customized using annotations such as `@JsonProperty`.

---

## 12. What happens if the request contains invalid JSON?

If the request body cannot be parsed as valid JSON, Spring cannot deserialize it into the requested Java object.

The request will fail with a client-side HTTP error.

Later, we will learn how to handle such errors properly using exception handling.

---

## 13. Does @RequestBody automatically validate the request?

No.

`@RequestBody` is responsible for binding/deserializing the request body.

Validation is a separate concern.

Later we will learn:

```java
@Valid
@RequestBody UserRequest request
```

along with Bean Validation annotations such as:

```java
@NotBlank
@Email
@Size
```

---

## 14. Explain the complete flow of our API.

The client sends:

```text
POST /users
```

with:

```json
{
  "id": 101,
  "name": "Gaurav"
}
```

Spring receives the request and:

```text
HTTP Request
     ↓
@RequestBody
     ↓
JSON deserialization
     ↓
User object
     ↓
Controller method
     ↓
return user
     ↓
JSON serialization
     ↓
HTTP Response
```

---

## 15. What is the main concept of Problem 6?

The main concept is:

> `@RequestBody` allows Spring to convert request-body JSON into a Java object.

Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

This is one of the fundamental building blocks of REST APIs.