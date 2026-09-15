# Problem 23 — Interview Q&A

## Q1. Can multiple validation annotations be used on one field?

Yes.

Example:

```java
@NotBlank
@Email
String email;
```

Each annotation applies a different validation rule.

---

## Q2. Why use both `@NotBlank` and `@Email` for email?

`@NotBlank` ensures that an email is provided.

`@Email` ensures that the provided value follows a valid email format.

They solve different problems.

---

## Q3. What does `@Size(min = 8)` do?

It requires the value to have a minimum size of 8.

For a String password:

```java
@Size(min = 8)
String password;
```

the password must contain at least 8 characters.

---

## Q4. Does `@Email` mean the field cannot be blank?

No.

`@Email` and `@NotBlank` serve different purposes.

For a required email field, use:

```java
@NotBlank
@Email
```

---

## Q5. What happens when multiple validation rules fail?

Spring's validation mechanism detects the validation violations and rejects the request.

In this project, the API returns:

```text
400 Bad Request
```

---

## Q6. Why use validation annotations instead of `if` statements?

Validation annotations keep validation rules close to the DTO fields and allow Spring's validation mechanism to handle them automatically.

This keeps controller code cleaner.

---

## Q7. Where are the validation rules defined?

They are defined directly on the fields of `RegistrationRequest`.

Example:

```java
@NotBlank
String name;

@NotBlank
@Email
String email;

@NotBlank
@Size(min = 8)
String password;
```

---

## Q8. What is the purpose of `@Valid`?

`@Valid` tells Spring to validate the request object before continuing with normal controller processing.

---

## Q9. What HTTP status is returned for invalid input in this project?

The invalid registration requests return:

```text
400 Bad Request
```

---

## Q10. What is the validation flow?

```text
POST /register
      ↓
@RequestBody
      ↓
RegistrationRequest
      ↓
@Valid
      ↓
Bean Validation
      ↓
Valid → Controller
Invalid → 400 Bad Request
```

---

## Q11. What validation rules are used for the password?

The password uses:

```java
@NotBlank
@Size(min = 8)
```

Therefore it must be present and contain at least 8 characters.

---

## Q12. What is the main lesson from this problem?

A realistic request DTO often needs several validation rules.

Spring Boot allows these rules to be expressed declaratively using Bean Validation annotations instead of manually checking every field in controller code.