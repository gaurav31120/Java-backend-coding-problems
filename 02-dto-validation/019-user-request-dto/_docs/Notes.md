# Problem 19 — UserRequest DTO

## 1. What is a DTO?

DTO stands for **Data Transfer Object**.

A DTO is an object used to carry data between different parts of an application, especially between the **client and the backend API**.

A DTO is not necessarily the same thing as the application's domain/model object.

In this problem, we use:

```text
UserRequest
```

to represent data coming **into** the API.

---

## 2. Why use a Request DTO?

Suppose we directly accept `User` in the controller:

```java
@PostMapping("/users")
public User addUser(@RequestBody User user) {
    ...
}
```

Then the client could send:

```json
{
  "id": 999,
  "name": "Amit"
}
```

The client is now trying to provide the user's ID.

But the ID should be controlled by the backend.

Instead, we create a separate request DTO:

```java
public class UserRequest {
    String name;
}
```

Now the client sends only:

```json
{
  "name": "Amit"
}
```

The backend creates the `User` and assigns the ID.

---

## 3. User vs UserRequest

### User

Represents the actual user object used by the application.

```java
public class User {

    int id;
    String name;

    // constructors
    // getters
    // setters
}
```

It contains:

```text
id
name
```

### UserRequest

Represents the data expected from the client when creating a user.

```java
public class UserRequest {

    String name;

    // constructors
    // getter
    // setter
}
```

It contains:

```text
name
```

It does **not** contain `id`.

---

## 4. Request DTO Flow

The complete flow in this problem is:

```text
Client
  |
  | POST /users
  | { "name": "Amit" }
  ↓
Controller
  |
  | @RequestBody UserRequest
  ↓
UserService
  |
  | getNextId()
  | create User
  ↓
UserRepository
  |
  | save User
  ↓
Created User
  |
  ↓
HTTP Response
```

The important transformation is:

```text
UserRequest
(name)
   ↓
Service
   ↓
User
(id + name)
```

---

# 5. UserRequest

`UserRequest` is our Request DTO.

```java
public class UserRequest {

    String name;

    public UserRequest() {
    }

    public UserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

The no-argument constructor allows the framework to create the object and populate its fields.

---

# 6. User Model

The `User` class represents the actual user.

```java
public class User {

    int id;
    String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
```

---

# 7. Repository Responsibility

The Repository is responsible for working with the application's data.

For this problem there is no database, so we use:

```java
private final List<User> users = new ArrayList<>();
```

Initial data:

```text
101 → Gaurav
102 → Rahul
```

The Repository provides:

```java
addUser(User user)
```

and:

```java
getNextId()
```

---

## 8. addUser()

The Repository stores the User object:

```java
public void addUser(User user) {
    users.add(user);
}
```

We don't need to create another `User` object.

The object received as the parameter can directly be stored.

---

# 9. Generating the Next ID

The Repository finds the largest existing ID and adds `1`.

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

Example:

```text
101 → Gaurav
102 → Rahul

maxId = 102

next ID = 102 + 1

next ID = 103
```

After adding Amit:

```text
101 → Gaurav
102 → Rahul
103 → Amit
```

The next ID becomes:

```text
104
```

---

# 10. Service Responsibility

The Service contains the application/business logic.

In this problem it performs the conversion:

```text
UserRequest → User
```

The Service receives:

```java
public User addUser(UserRequest userRequest)
```

Then gets the next ID:

```java
userRepository.getNextId()
```

Gets the name from the DTO:

```java
userRequest.getName()
```

Creates the actual User:

```java
User user = new User(
        userRepository.getNextId(),
        userRequest.getName()
);
```

Then saves it:

```java
userRepository.addUser(user);
```

Finally returns it:

```java
return user;
```

Complete method:

```java
public User addUser(UserRequest userRequest) {

    User user = new User(
            userRepository.getNextId(),
            userRequest.getName()
    );

    userRepository.addUser(user);

    return user;
}
```

---

# 11. Controller Responsibility

The Controller handles the HTTP request.

```java
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }
}
```

The Controller does not create the User itself.

It delegates the work to the Service.

---

# 12. @RequestBody

`@RequestBody` tells Spring to read the HTTP request body and convert it into the specified Java object.

Example request:

```json
{
  "name": "Amit"
}
```

Controller:

```java
public User addUser(@RequestBody UserRequest userRequest)
```

Spring converts the JSON into a `UserRequest` object.

Conceptually:

```text
JSON
 ↓
UserRequest
```

---

# 13. @PostMapping

`@PostMapping("/users")` maps HTTP POST requests to the controller method.

```java
@PostMapping("/users")
public User addUser(@RequestBody UserRequest userRequest) {
    return userService.addUser(userRequest);
}
```

So:

```text
POST /users
```

calls:

```text
addUser()
```

---

# 14. Constructor Injection

We use constructor injection to provide dependencies.

Controller:

```java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

Service:

```java
private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

Spring creates the required objects and provides them through the constructors.

---

# 15. @Service and @Repository

### @Service

```java
@Service
public class UserService
```

Tells Spring that this class is a Service component.

### @Repository

```java
@Repository
public class UserRepository
```

Tells Spring that this class is a Repository component.

Spring can discover and manage these classes as beans.

---

# 16. Complete Example

### Request

```http
POST /users
```

```json
{
  "name": "Amit"
}
```

### Controller receives

```text
UserRequest
name = "Amit"
```

### Service asks Repository

```text
getNextId()
```

Result:

```text
103
```

### Service creates User

```text
User
id = 103
name = "Amit"
```

### Repository stores User

```text
101 → Gaurav
102 → Rahul
103 → Amit
```

### Response

```json
{
  "id": 103,
  "name": "Amit"
}
```

---

# 17. Why the ID is Generated by the Backend

The client sends:

```json
{
  "name": "Amit"
}
```

The client does not send:

```json
{
  "id": 103,
  "name": "Amit"
}
```

The backend decides the ID.

This prevents the request DTO from allowing the client to control data that should be generated by the application.

---

# 18. Common Mistakes

### Mistake 1 — Accepting User instead of UserRequest

Avoid:

```java
public User addUser(@RequestBody User user)
```

For this problem, use:

```java
public User addUser(@RequestBody UserRequest userRequest)
```

---

### Mistake 2 — Putting the ID inside UserRequest

Avoid:

```java
class UserRequest {
    int id;
    String name;
}
```

The request only needs:

```text
name
```

---

### Mistake 3 — Creating another User unnecessarily

Avoid:

```java
users.add(new User(user.id, user.name));
```

When the User object is already available:

```java
users.add(user);
```

---

### Mistake 4 — Returning maxId instead of next ID

Wrong:

```java
return maxId;
```

Correct:

```java
return maxId + 1;
```

---

### Mistake 5 — Putting business logic in Controller

The Controller should not generate IDs and construct Users itself.

Instead:

```text
Controller
    ↓
Service
    ↓
Repository
```

---

# 19. Key Takeaways

```text
DTO
→ carries data between layers/boundaries

UserRequest
→ represents incoming user creation data

User
→ represents the actual user

Controller
→ handles HTTP request

Service
→ performs application logic and converts UserRequest → User

Repository
→ stores/retrieves data

@RequestBody
→ maps request JSON to Java object

@PostMapping
→ handles HTTP POST request

Constructor Injection
→ provides dependencies through constructors
```

### Most Important Flow

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
Generate ID
     ↓
Create User
     ↓
Repository
     ↓
Save User
     ↓
Return User
```