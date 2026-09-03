# Problem 3 — Profile JSON API

## 1. REST API Response

A REST API can return structured data such as JSON instead of plain text.

Example:

```json
{
  "name": "Gaurav",
  "age": 25,
  "city": "Patna"
}
```

## 2. Java Object

A Java class can represent the data returned by an API.

```java
public class Profile {
    String name;
    int age;
    String city;
}
```

The `Profile` object contains the profile data.

## 3. Returning a Java Object

A controller can return a Java object directly.

```java
@GetMapping("/profile")
public Profile profile() {
    Profile profile = new Profile("Gaurav", 25, "Patna");
    return profile;
}
```

## 4. Object to JSON Conversion

Spring Boot uses Jackson to convert Java objects into JSON when returning them from REST controllers.

The flow is:

```text
Java Profile Object
        ↓
Spring Boot
        ↓
Jackson
        ↓
JSON
        ↓
HTTP Response
```

## 5. `@RestController`

`@RestController` marks a class as a REST controller.

Returned objects are written to the HTTP response body.

## 6. `@GetMapping`

`@GetMapping` maps an HTTP GET request to a controller method.

```java
@GetMapping("/profile")
```

This handles:

```text
GET /profile
```

## 7. Constructor

The constructor is used to create a `Profile` object with its initial values.

```java
Profile(String name, int age, String city) {
    this.name = name;
    this.age = age;
    this.city = city;
}
```

## 8. Getters

Getters allow the values of the object's fields to be accessed.

```java
getName()
getAge()
getCity()
```

Jackson uses the object's properties/getters when creating the JSON response.

## 9. Complete Flow

```text
GET /profile
      ↓
ProfileController
      ↓
new Profile("Gaurav", 25, "Patna")
      ↓
return Profile
      ↓
Jackson converts object to JSON
      ↓
HTTP Response
```

## 10. Important Concepts

- REST API
- JSON
- Java object
- `@RestController`
- `@GetMapping`
- Constructor
- Getter
- Jackson
- Object-to-JSON conversion

## 11. Key Takeaways

- REST APIs commonly return JSON.
- A Java class can represent API response data.
- A controller can return a Java object.
- Spring Boot automatically converts the returned object into JSON.
- Jackson performs the object-to-JSON conversion.
- JSON property order does not matter.

## 12. One-Line Revision

**Return a Java object from a REST controller and Spring/Jackson converts it into JSON.**