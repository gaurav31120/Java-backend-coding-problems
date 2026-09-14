# Problem 19 — UserRequest DTO
# Interview Q&A

## 1. What is a DTO?

**Answer:**

DTO stands for **Data Transfer Object**.

It is an object used to transfer data between different parts of an application or across an API boundary.

In this problem, `UserRequest` is a DTO used to carry user creation data from the client to the backend.

---

## 2. Why did we create `UserRequest` instead of directly accepting `User`?

**Answer:**

Because the client should only provide the data required to create the user.

The `User` object contains:

```text
id
name
```

But the client should provide only:

```text
name
```

The backend is responsible for generating the ID.

Therefore:

```text
UserRequest → name
User        → id + name
```

This keeps the API request model separate from the application's User model.

---

## 3. What is the difference between `UserRequest` and `User` in this problem?

**Answer:**

`UserRequest` represents incoming request data:

```text
name
```

`User` represents the actual user:

```text
id
name
```

The Service converts:

```text
UserRequest → User
```

---

## 4. Why shouldn't the client send the User ID?

**Answer:**

The ID is controlled by the backend in this application.

If the client could send the ID, it could attempt to create:

```json
{
  "id": 1,
  "name": "Amit"
}
```

Instead, the client sends:

```json
{
  "name": "Amit"
}
```

and the backend generates the next ID.

---

## 5. What does `@RequestBody` do?

**Answer:**

`@RequestBody` tells Spring to read the HTTP request body and convert the incoming data into the specified Java object.

Example:

```java
@PostMapping("/users")
public User addUser(@RequestBody UserRequest userRequest) {
    return userService.addUser(userRequest);
}
```

For this request:

```json
{
  "name": "Amit"
}
```

Spring creates/populates a `UserRequest` object.

---

## 6. What does `@PostMapping` do?

**Answer:**

`@PostMapping` maps an HTTP POST request to a controller method.

Example:

```java
@PostMapping("/users")
```

means that a POST request to:

```text
/users
```

will invoke that controller method.

---

## 7. Why did we use POST for creating a user?

**Answer:**

POST is commonly used when the client asks the server to create a new resource.

In this problem:

```text
POST /users
```

is used to create a new User.

---

## 8. What is the responsibility of the Controller?

**Answer:**

The Controller handles the HTTP/API layer.

In this problem it:

1. Receives the POST request.
2. Accepts the `UserRequest`.
3. Passes it to the Service.
4. Returns the created User.

It should not contain the main business logic.

---

## 9. What is the responsibility of the Service?

**Answer:**

The Service contains the application's business logic.

In this problem it:

1. Receives `UserRequest`.
2. Gets the next ID from the Repository.
3. Creates a `User`.
4. Saves the User through the Repository.
5. Returns the created User.

---

## 10. What is the responsibility of the Repository?

**Answer:**

The Repository is responsible for working with application data.

For this problem, there is no database, so the Repository uses an in-memory `ArrayList`.

It provides:

```java
addUser(User user)
```

and:

```java
getNextId()
```

---

## 11. Why is the Service not directly modifying the `users` list?

**Answer:**

Because the Repository owns the data-access responsibility.

The Service should communicate with the Repository instead of directly manipulating the underlying collection.

This keeps responsibilities separated:

```text
Controller → HTTP
Service    → Business logic
Repository → Data
```

---

## 12. Why did we use constructor injection?

**Answer:**

Constructor injection allows Spring to provide required dependencies when creating the object.

Example:

```java
public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

The Service cannot work without its Repository, so the dependency is explicitly required through the constructor.

---

## 13. What does `@Service` do?

**Answer:**

`@Service` tells Spring that the class is a Service component managed by Spring.

Example:

```java
@Service
public class UserService {
}
```

Spring discovers it and can inject it into other Spring-managed components.

---

## 14. What does `@Repository` do?

**Answer:**

`@Repository` tells Spring that the class is a Repository component.

Example:

```java
@Repository
public class UserRepository {
}
```

Spring manages the Repository as a bean so it can be injected into the Service.

---

## 15. How is the next User ID generated in this problem?

**Answer:**

The Repository finds the largest existing ID and returns that value plus one.

```java
public int getNextId() {
    int maxId = 0;

    for (User user : users) {
        if (user.getId() > maxId) {
            maxId = user.getId();
        }
    }

    return maxId + 1;
}
```

For:

```text
101
102
```

the next ID is:

```text
103
```

---

## 16. Why does `getNextId()` return `maxId + 1`?

**Answer:**

`maxId` represents the highest ID currently stored.

If:

```text
maxId = 102
```

then the next available sequential ID is:

```text
102 + 1 = 103
```

Returning only `maxId` would return the ID that is already in use.

---

## 17. What happens when the client sends a POST request?

**Answer:**

The flow is:

```text
POST /users
      ↓
Controller
      ↓
@RequestBody converts JSON → UserRequest
      ↓
UserService
      ↓
Repository.getNextId()
      ↓
Create User
      ↓
Repository.addUser()
      ↓
Return User
      ↓
HTTP Response
```

---

## 18. What happens if the client sends this request?

```json
{
  "name": "Amit"
}
```

**Answer:**

Spring maps the JSON to:

```text
UserRequest
name = "Amit"
```

The Service gets the next ID:

```text
103
```

Then creates:

```text
User(103, "Amit")
```

The Repository stores it and the User is returned in the response.

---

## 19. Why did we not put the ID in `UserRequest`?

**Answer:**

Because the ID is generated by the backend.

The Request DTO represents what the client is allowed/expected to provide.

The backend adds information that it controls when creating the actual `User`.

---

## 20. What is the benefit of separating request data from the User model?

**Answer:**

It creates a clear separation between:

```text
API input
```

and:

```text
Application model
```

The API can define exactly what data it accepts without exposing every field of the application's User model.

---

## 21. Why does `UserService.addUser()` return `User`?

**Answer:**

Because after creating and storing the user, the application needs to return the created resource to the Controller.

The Controller then returns that User as the HTTP response.

---

## 22. Why does `UserRepository.addUser()` accept a `User` instead of a `UserRequest`?

**Answer:**

Because the Repository stores the actual `User`.

The conversion from:

```text
UserRequest
```

to:

```text
User
```

is performed by the Service.

Therefore:

```text
UserRequest
     ↓
Service
     ↓
User
     ↓
Repository
```

---

## 23. Where should the conversion from `UserRequest` to `User` happen?

**Answer:**

In this problem, it happens in the Service layer.

```java
User user = new User(
        userRepository.getNextId(),
        userRequest.getName()
);
```

The Controller receives the request, while the Service performs the application logic.

---

## 24. Why don't we create a separate Mapper class in this problem?

**Answer:**

The problem explicitly keeps the implementation simple and does not require a Mapper class.

Therefore, the Service performs the small conversion directly.

For larger applications, mapping may be moved into a dedicated Mapper component.

---

## 25. What is the difference between `@RequestMapping` and `@PostMapping`?

**Answer:**

`@RequestMapping` is a more general request-mapping annotation.

`@PostMapping` specifically maps HTTP POST requests.

For a create-user endpoint, this is clearer:

```java
@PostMapping("/users")
```

instead of using a generic mapping.

---

## 26. Why is `UserRequest` a better API contract than accepting `User` directly?

**Answer:**

Because it explicitly defines what the endpoint expects.

The request contract is:

```text
name
```

rather than exposing the complete User structure:

```text
id
name
```

This prevents clients from unnecessarily providing fields controlled by the server.

---

# Quick Interview Revision

### DTO

```text
Data Transfer Object
```

### Request DTO

```text
Client → Backend
```

### UserRequest

```text
name
```

### User

```text
id + name
```

### Controller

```text
Handles HTTP request
```

### Service

```text
Business/application logic
UserRequest → User
```

### Repository

```text
Data storage/retrieval
```

### @RequestBody

```text
JSON → Java object
```

### @PostMapping

```text
HTTP POST → Controller method
```

### Complete flow

```text
POST /users
    ↓
JSON
    ↓
UserRequest
    ↓
Controller
    ↓
Service
    ↓
User
    ↓
Repository
    ↓
Response
```