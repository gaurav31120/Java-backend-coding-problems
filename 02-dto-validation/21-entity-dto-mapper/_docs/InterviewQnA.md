# Problem 21 — Entity-DTO Mapper — Interview Q&A

## 1. What is DTO mapping?

DTO mapping is the process of converting one object type into another.

In this problem:

```text
User → UserResponse
```

---

## 2. What is a Mapper?

A Mapper is responsible for converting one object representation into another.

For example:

```java
public UserResponse toResponse(User user)
```

converts a `User` into a `UserResponse`.

---

## 3. Why do we need a Mapper?

Without a Mapper, Services may repeatedly contain conversion code.

A Mapper separates object conversion from the main Service logic.

---

## 4. What is the responsibility of UserMapper?

`UserMapper` is responsible for:

```text
User → UserResponse
```

It should not:

- Access the database
- Handle HTTP requests
- Perform controller logic

---

## 5. What does @Component do?

`@Component` tells Spring to create and manage the class as a Spring bean.

Example:

```java
@Component
public class UserMapper {
}
```

This allows the Mapper to be injected into other Spring-managed classes.

---

## 6. Why can UserMapper be injected into UserService?

Because both are managed by Spring.

`UserMapper` is registered using:

```java
@Component
```

and `UserService` is registered using:

```java
@Service
```

Spring creates the required objects and injects the Mapper through the constructor.

---

## 7. Where should DTO mapping happen?

There is no single mandatory location for every architecture, but in this problem the mapping responsibility is explicitly placed in a dedicated Mapper component.

The flow is:

```text
Service → UserMapper → UserResponse
```

---

## 8. What was different in Problem 20?

In Problem 20, the Service manually created the response:

```java
new UserResponse(user.getId(), user.getName());
```

In Problem 21, the Service delegates that work:

```java
userMapper.toResponse(user);
```

---

## 9. What is the benefit of this separation?

The Service focuses on application flow while the Mapper focuses on object conversion.

This makes responsibilities clearer and conversion logic easier to reuse.

---

## 10. What fields are mapped from User to UserResponse?

The Mapper maps:

```text
User.id   → UserResponse.id
User.name → UserResponse.name
```

Email is not mapped.

---

## 11. Why is email not included in UserResponse?

Because `UserResponse` is designed to expose only:

```text
id
name
```

The DTO controls the API response structure.

---

## 12. What is constructor injection?

Constructor injection means dependencies are passed through the constructor.

Example:

```java
public UserService(
        UserRepository userRepository,
        UserMapper userMapper) {

    this.userRepository = userRepository;
    this.userMapper = userMapper;
}
```

---

## 13. Why does UserService need UserMapper?

The Service retrieves a `User` from the Repository and needs to convert it into the API's `UserResponse`.

The Mapper performs that conversion.

---

## 14. What is the responsibility of the Repository?

The Repository handles data access.

In this problem it provides:

```java
User findById(int id)
```

It returns a `User`.

---

## 15. What is the responsibility of the Service?

The Service coordinates the application operation.

For this problem:

```text
1. Get User from Repository
2. Check whether User exists
3. Ask Mapper to convert User
4. Return UserResponse
```

---

## 16. What is the responsibility of the Controller?

The Controller handles HTTP requests and delegates to the Service.

Example:

```java
@GetMapping("/users/{id}")
public UserResponse getUser(@PathVariable int id) {
    return userService.getUser(id);
}
```

---

## 17. What is the difference between Model and DTO?

A Model represents internal application data.

A DTO represents data transferred through an API or between layers.

For this problem:

```text
User         → internal model
UserResponse → API response DTO
```

---

## 18. Why shouldn't the Repository return UserResponse?

The Repository is responsible for data access and should work with the application's data model.

The API response structure belongs outside the Repository.

In this problem:

```text
Repository → User
Mapper     → UserResponse
```

---

## 19. Why shouldn't the Controller perform the mapping?

The Controller should focus on HTTP handling.

Putting mapping logic inside the Controller mixes responsibilities and makes the Controller harder to maintain.

---

## 20. Can one User have multiple Response DTOs?

Yes.

Different API endpoints may require different representations.

For example:

```text
UserSummaryResponse
UserDetailsResponse
AdminUserResponse
```

Each DTO can expose different fields.

---

## 21. What is manual mapping?

Manual mapping means explicitly creating the target DTO and copying the required fields.

Example:

```java
UserResponse response =
        new UserResponse(user.getId(), user.getName());
```

---

## 22. What is the advantage of manual mapping in this problem?

Manual mapping makes the conversion process easy to understand.

We can clearly see:

```text
id → id
name → name
```

It also avoids introducing a mapping library before understanding the underlying concept.

---

## 23. What happens when GET /users/101 is called?

The request follows:

```text
GET /users/101
       ↓
UserController
       ↓
UserService
       ↓
UserRepository
       ↓
User
       ↓
UserMapper
       ↓
UserResponse
       ↓
JSON response
```

---

## 24. What does the Mapper return?

The Mapper returns a `UserResponse`:

```java
return userResponse;
```

---

## 25. What happens if the User does not exist?

The Repository returns:

```java
null
```

The Service checks:

```java
if (user == null) {
    return null;
}
```

The detailed HTTP error-handling strategy is handled in later problems.

---

## 26. What is the most important concept from Problem 21?

The key idea is:

```text
Internal Model ≠ API DTO
```

and:

```text
User
 ↓
Mapper
 ↓
UserResponse
```

A dedicated Mapper keeps object conversion separate from the Service and Controller responsibilities.