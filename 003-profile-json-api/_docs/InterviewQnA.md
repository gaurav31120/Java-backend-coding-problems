# Problem 3 — Interview Q&A

## Q1. What Is JSON?

JSON stands for JavaScript Object Notation.

It is a lightweight data format commonly used for communication between clients and REST APIs.

## Q2. Why Do REST APIs Use JSON?

JSON is simple, lightweight, human-readable, and supported by almost every modern programming language.

## Q3. Can a Spring REST Controller Return a Java Object?

Yes.

A REST controller can return a Java object, and Spring Boot can convert that object into JSON.

## Q4. What Converts a Java Object Into JSON in Spring Boot?

Spring Boot commonly uses the Jackson library for JSON serialization.

## Q5. What Is Serialization?

Serialization is the process of converting an object into a format that can be transmitted or stored.

In this problem:

```text
Java Object → JSON
```

## Q6. What Is Deserialization?

Deserialization is the reverse process:

```text
JSON → Java Object
```

## Q7. What Does `@RestController` Do?

`@RestController` marks a class as a REST controller and makes controller return values available directly in the HTTP response body.

## Q8. What Does `@GetMapping` Do?

`@GetMapping` maps an HTTP GET request to a controller method.

Example:

```java
@GetMapping("/profile")
```

## Q9. Why Does the Controller Return `Profile` Instead of `String`?

Because the endpoint needs to return structured profile data containing multiple fields:

```text
name
age
city
```

The `Profile` class represents that data.

## Q10. Does the JSON Property Order Matter?

No.

For example, these responses represent the same data:

```json
{
  "name": "Gaurav",
  "age": 25,
  "city": "Patna"
}
```

and:

```json
{
  "age": 25,
  "city": "Patna",
  "name": "Gaurav"
}
```

## Q11. What Is Serialization in This Problem?

The `Profile` Java object returned by the controller is serialized into JSON before being sent to the client.

## Q12. Explain the Request Flow

```text
Client
   ↓
GET /profile
   ↓
ProfileController
   ↓
Profile Java Object
   ↓
Jackson Serialization
   ↓
JSON
   ↓
HTTP Response
```

# Quick Interview Revision

```text
REST API → communicates over HTTP

JSON → common API data format

@RestController → REST controller

@GetMapping → handles GET request

Serialization → Java Object → JSON

Deserialization → JSON → Java Object

Jackson → commonly handles JSON conversion
```

# Must Remember

**Spring REST APIs can return Java objects directly. Spring/Jackson handles the conversion from Java object to JSON.**