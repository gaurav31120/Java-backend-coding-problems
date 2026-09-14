# Problem 12 — Interview Q&A

## 1. What is layered architecture?

Layered architecture separates an application into different layers based on responsibility.

A common Spring Boot structure is:

```text
Controller → Service → Repository
```

---

## 2. What is the responsibility of a Controller?

The Controller handles incoming HTTP requests and returns HTTP responses.

For example:

```java
@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}
```

---

## 3. What is the responsibility of a Service?

The Service contains application or business logic.

It sits between the Controller and Repository.

```text
Controller → Service → Repository
```

---

## 4. What is the responsibility of a Repository?

The Repository handles data access.

In this problem, the repository uses an in-memory `List<User>` instead of a database.

---

## 5. Why should the Controller not directly access the Repository?

Because it creates tight coupling between the HTTP layer and data-access layer.

Instead:

```text
Controller → Service → Repository
```

This keeps responsibilities separated.

---

## 6. What does `@Repository` do?

`@Repository` tells Spring that the class is a repository component and should be managed as a Spring bean.

---

## 7. What does `@Service` do?

`@Service` tells Spring that the class is a service component and should be managed as a Spring bean.

---

## 8. What is constructor injection?

Constructor injection means providing a dependency through the class constructor.

Example:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

Spring automatically supplies the dependency.

---

## 9. Why is constructor injection preferred?

It makes dependencies explicit and allows required dependencies to be initialized when the object is created.

It also works well with `final` fields.

---

## 10. Why did we use `List<User>` instead of `List<String>`?

Because the repository stores `User` objects.

```java
private List<User> users = new ArrayList<>();
```

Therefore we add:

```java
users.add(new User(101, "Gaurav"));
```

not:

```java
users.add("Gaurav");
```

---

## 11. What is the difference between `List` and `ArrayList`?

`List` is an interface.

`ArrayList` is a concrete implementation of `List`.

Therefore:

```java
List<User> users = new ArrayList<>();
```

is preferred over trying to instantiate `List` directly.

---

## 12. Explain the request flow in this problem.

For:

```text
GET /users
```

the flow is:

```text
Client
  ↓
UserController
  ↓
UserService
  ↓
UserRepository
  ↓
List<User>
  ↓
JSON response
```

---

## 13. Why do we use `final` for injected dependencies?

A dependency such as `UserRepository` should normally not be replaced after construction.

Therefore:

```java
private final UserRepository userRepository;
```

expresses that the reference is initialized once through the constructor.