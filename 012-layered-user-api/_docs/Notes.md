# Problem 12 — Notes

## Layered Architecture

A common Spring Boot structure is:

```text
Controller
    ↓
Service
    ↓
Repository
```

Each layer has a specific responsibility.

---

## Controller

The Controller handles HTTP requests.

```java
@RestController
public class UserController {
}
```

Example:

```java
@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}
```

The Controller should not directly access the repository.

---

## Service

The Service contains application/business logic.

```java
@Service
public class UserService {
}
```

It receives the Repository through constructor injection:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

Then it can call:

```java
return userRepository.getAllUsers();
```

---

## Repository

The Repository is responsible for accessing data.

```java
@Repository
public class UserRepository {
}
```

For this problem, the data is stored in memory:

```java
private List<User> users = new ArrayList<>();
```

Users are initialized with:

```java
users.add(new User(101, "Gaurav"));
users.add(new User(102, "Rahul"));
```

---

## Constructor Injection

Constructor injection means dependencies are provided through the constructor.

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

Spring automatically provides the required Spring bean.

Using `final` for injected dependencies is good practice:

```java
private final UserRepository userRepository;
```

---

## Important Flow

For:

```text
GET /users
```

the request flows through:

```text
UserController
      ↓
UserService
      ↓
UserRepository
      ↓
List<User>
```

The repository returns the data, the service passes it back, and the controller returns it as JSON.

---

## Key Takeaway

Do not put everything inside the Controller.

Instead:

```text
Controller → handles HTTP
Service    → handles application logic
Repository → handles data access
```

This separation makes applications easier to maintain, test, and extend.