# Problem 21 — Entity-DTO Mapper

## 1. What is DTO Mapping?

DTO mapping is the process of converting one object type into another object type.

In this problem:

```text
User → UserResponse
```

The `User` represents our internal model, while `UserResponse` represents the data exposed by the API.

---

## 2. Why Do We Need a Mapper?

In Problem 20, the Service directly created the `UserResponse`:

```java
return new UserResponse(user.getId(), user.getName());
```

This works, but the Service is now responsible for both:

1. Business/application flow
2. Object conversion

As an application grows, many DTO conversions can become repetitive.

A Mapper separates this responsibility.

---

## 3. Mapper Responsibility

The `UserMapper` is responsible only for converting:

```text
User
 ↓
UserMapper
 ↓
UserResponse
```

Example:

```java
public UserResponse toResponse(User user) {

    UserResponse userResponse =
            new UserResponse(user.getId(), user.getName());

    return userResponse;
}
```

The Mapper does not access the database and does not handle HTTP requests.

---

## 4. @Component

The Mapper is annotated with:

```java
@Component
```

This tells Spring to create and manage the `UserMapper` object.

```java
@Component
public class UserMapper {
}
```

Because Spring manages it, it can be injected into the Service.

---

## 5. User Model

The `User` model contains:

```text
id
name
email
```

Example:

```java
new User(101, "Gaurav", "gaurav@gmail.com");
```

---

## 6. UserResponse DTO

The API response contains only:

```text
id
name
```

Example:

```java
new UserResponse(101, "Gaurav");
```

The email is intentionally not exposed.

---

## 7. Mapping Logic

The Mapper performs:

```java
UserResponse userResponse =
        new UserResponse(user.getId(), user.getName());
```

So:

```text
User.id   → UserResponse.id
User.name → UserResponse.name
User.email → not mapped
```

---

## 8. Service with Mapper

The Service now receives both dependencies:

```java
private final UserRepository userRepository;
private final UserMapper userMapper;
```

Constructor injection:

```java
public UserService(
        UserRepository userRepository,
        UserMapper userMapper) {

    this.userRepository = userRepository;
    this.userMapper = userMapper;
}
```

---

## 9. Service Flow

The Service performs the application flow:

```java
public UserResponse getUser(int id) {

    User user = userRepository.findById(id);

    if (user == null) {
        return null;
    }

    return userMapper.toResponse(user);
}
```

The Service does not manually create `UserResponse`.

Instead:

```java
userMapper.toResponse(user)
```

is responsible for conversion.

---

## 10. Controller

The Controller handles the HTTP request:

```java
@GetMapping("/users/{id}")
public UserResponse getUser(@PathVariable int id) {

    return userService.getUser(id);
}
```

The Controller does not perform mapping.

---

## 11. Complete Architecture

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
User
  ↓
Service
  ↓
UserMapper
  ↓
UserResponse
  ↓
Client
```

Responsibilities:

```text
Controller → HTTP handling
Service    → Application/business flow
Repository → Data access
Mapper     → Object conversion
DTO        → API data structure
```

---

## 12. Entity/Model vs DTO

The internal model and API DTO serve different purposes.

```text
User
├── id
├── name
└── email

UserResponse
├── id
└── name
```

This prevents the API from automatically exposing every field in the internal model.

---

## 13. Request DTO vs Response DTO

### Request DTO

Used for data coming into the application:

```text
Client
 ↓
UserRequest
 ↓
Controller
```

### Response DTO

Used for data going out:

```text
Service
 ↓
UserResponse
 ↓
Client
```

---

## 14. Benefits of a Mapper

A dedicated Mapper provides:

- Separation of responsibilities
- Reusable conversion logic
- Cleaner Services
- Easier maintenance
- Better organization
- More controlled API responses

---

## 15. Common Mistakes

### Mistake 1 — Mapping inside Controller

Avoid:

```java
new UserResponse(user.getId(), user.getName());
```

inside the Controller.

The Controller should handle HTTP concerns.

---

### Mistake 2 — Mapping inside Repository

The Repository should return the data model:

```java
User
```

It should not create API DTOs.

---

### Mistake 3 — Exposing email

The Mapper should map only the fields required by `UserResponse`.

```java
new UserResponse(user.getId(), user.getName());
```

Do not add email when the DTO does not require it.

---

### Mistake 4 — Forgetting @Component

Without:

```java
@Component
```

Spring will not automatically manage the Mapper as a Spring bean.

---

## 16. Key Takeaways

1. DTO mapping converts one object representation into another.
2. A Mapper centralizes conversion logic.
3. `UserMapper` converts `User → UserResponse`.
4. `@Component` makes the Mapper a Spring-managed bean.
5. The Service uses the Mapper.
6. The Repository works with the `User` model.
7. The Controller works with HTTP requests and responses.
8. `UserResponse` controls which fields are exposed.
9. Mapper separation becomes more useful as an application grows.
10. The key architecture is:

```text
Controller → Service → Repository
                 ↓
               Mapper
                 ↓
                DTO
```