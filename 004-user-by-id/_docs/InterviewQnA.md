# Problem 4 — Interview Q&A

## Q1. What Is a Path Variable?

A path variable is a dynamic value included in the URL path.

Example:

```text
/users/101
```

Here, `101` can be treated as the user ID.

---

## Q2. What Is `@PathVariable`?

`@PathVariable` is a Spring annotation used to extract a value from the URL path and pass it to a controller method.

Example:

```java
@GetMapping("/users/{id}")
public User user(@PathVariable("id") int id) {
    ...
}
```

---

## Q3. What Does `{id}` Mean in `/users/{id}`?

`{id}` represents a dynamic path segment.

For example:

```text
/users/101
```

means:

```text
id = 101
```

---

## Q4. Why Use a Path Variable?

Path variables are useful when the resource is identified by a value in the URL.

Examples:

```text
/users/101
/orders/500
/products/25
```

---

## Q5. Can the Same Endpoint Handle Different IDs?

Yes.

The endpoint:

```text
/users/{id}
```

can handle:

```text
/users/1
/users/101
/users/500
```

---

## Q6. What Is the Difference Between a Path Variable and a Query Parameter?

A path variable is part of the URL path:

```text
/users/101
```

A query parameter comes after `?`:

```text
/users?id=101
```

Path variables commonly identify a specific resource, while query parameters are commonly used for filtering, searching, sorting, or optional parameters.

---

## Q7. What Happens When `/users/101` Is Called?

Spring matches the request with:

```text
/users/{id}
```

`@PathVariable` extracts:

```text
101
```

and passes it to the controller method.

---

## Q8. Why Is the Method Parameter `int id`?

The ID is expected to be numeric, so the path variable is mapped to an `int`.

Spring performs the conversion from the URL text to the required Java type.

---

## Q9. Can a Path Variable Be a String?

Yes.

For example:

```java
@GetMapping("/users/{name}")
public User user(@PathVariable String name) {
    ...
}
```

The path variable type depends on the data being represented.

---

## Q10. What Happens If the Path Variable Cannot Be Converted to `int`?

If the endpoint expects:

```java
@PathVariable int id
```

but the client sends a non-numeric value such as:

```text
/users/abc
```

Spring cannot convert `"abc"` to an integer and will return an error response.

---

## Q11. What Is the Basic Request Flow?

```text
GET /users/101
      ↓
Spring Controller
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

# Quick Interview Revision

```text
Path Variable → dynamic value in URL

@PathVariable → extracts value from URL

/users/{id} → dynamic endpoint

/users/101 → id = 101

Path variable → commonly identifies a resource
```

# Must Remember

**`@PathVariable` extracts dynamic values directly from the URL path.**