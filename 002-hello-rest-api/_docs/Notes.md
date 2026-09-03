# Problem 2 — Hello REST API

## 1. What Is a REST API?

A REST API allows a client such as a browser, Postman, frontend application, or mobile app to communicate with a backend using HTTP.

Example:

```text
GET /hello
```

The backend processes the request and sends a response.

---

## 2. `@RestController`

`@RestController` tells Spring that the class contains REST API endpoints.

```java
@RestController
public class HelloController {
}
```

It is commonly used for controllers that return data directly in the HTTP response.

---

## 3. `@RequestMapping`

`@RequestMapping` maps an HTTP request to a controller method.

```java
@RequestMapping("/hello")
```

This means the method handles requests to:

```text
/hello
```

---

## 4. Controller Method

Our endpoint method is:

```java
@RequestMapping("/hello")
public String hello() {
    return "Hello, World!";
}
```

When `/hello` is requested, Spring executes the `hello()` method.

---

## 5. HTTP GET

When we enter:

```text
http://localhost:8080/hello
```

in the browser, the browser sends an HTTP GET request.

Our controller handles that request and returns:

```text
Hello, World!
```

---

## 6. Complete Controller

```java
package com.practice.hellorestapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

---

## 7. Request Flow

The basic flow is:

```text
Client
   ↓
GET /hello
   ↓
Spring Boot
   ↓
HelloController
   ↓
hello()
   ↓
"Hello, World!"
   ↓
HTTP Response
```

---

## 8. `@Controller` vs `@RestController`

`@Controller` is generally used for Spring MVC controllers that may return views.

`@RestController` is designed for REST APIs and returns the method result directly as the HTTP response body.

For REST APIs, we commonly use:

```java
@RestController
```

---

## 9. Important Concepts

- REST API
- HTTP request
- HTTP GET
- `@RestController`
- `@RequestMapping`
- Controller
- Endpoint
- HTTP response

---

## 10. Key Takeaways

- A REST API allows clients to communicate with a backend.
- `@RestController` marks a class as a REST controller.
- `@RequestMapping` maps requests to controller methods.
- A controller method processes the request and returns a response.
- `/hello` is the endpoint path.
- `Hello, World!` is the response.

---

## 11. One-Line Revision

**`@RestController` + request mapping + controller method = basic Spring REST API endpoint.**