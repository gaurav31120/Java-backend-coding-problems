# Problem 2 — Interview Q&A

## Q1. What Is REST?

REST stands for Representational State Transfer.

It is an architectural style used for designing APIs that communicate over HTTP.

---

## Q2. What Is a REST API?

A REST API is an API that allows clients and servers to communicate using HTTP methods such as GET, POST, PUT, and DELETE.

---

## Q3. What Is `@RestController`?

`@RestController` tells Spring that a class contains REST endpoints.

It also makes the return value of controller methods the HTTP response body by default.

---

## Q4. What Is `@RequestMapping`?

`@RequestMapping` is used to map HTTP requests to controller classes or methods.

Example:

```java
@RequestMapping("/hello")
```

---

## Q5. What Happens When We Call `/hello`?

When a client sends a request to:

```text
GET /hello
```

Spring finds the controller method mapped to `/hello` and executes it.

The method returns:

```text
Hello, World!
```

which is sent back as the HTTP response.

---

## Q6. Why Do We Use `public String hello()`?

The method returns a `String`.

The returned string becomes the response body because the class is annotated with `@RestController`.

---

## Q7. What Is an Endpoint?

An endpoint is a specific URL/path through which a client can access a backend operation.

Example:

```text
GET /hello
```

---

## Q8. What Is HTTP GET Used For?

GET is generally used to retrieve/read data from a server.

In this problem, GET is used to retrieve the hello response.

---

## Q9. What Is the Difference Between `@Controller` and `@RestController`?

`@Controller` is commonly used for Spring MVC applications that return views.

`@RestController` is used for REST APIs and returns data directly in the HTTP response body.

---

## Q10. What Is the Basic Flow of a REST Request?

```text
Client
   ↓
HTTP Request
   ↓
Spring Boot
   ↓
Controller
   ↓
Controller Method
   ↓
HTTP Response
```

---

# Quick Interview Revision

```text
@RestController → REST controller

@RequestMapping → maps request to method

GET → retrieve/read

Endpoint → API URL/path

Response body → data returned to client
```

# Must Remember

**For a basic Spring REST API:**

```java
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```