# Problem 9 — Interview Questions & Answers

## 1. What happens when multiple Spring Beans implement the same interface?

Spring cannot automatically decide which Bean should be injected.

This creates a dependency ambiguity error.

## 2. How can you resolve multiple Bean ambiguity in Spring?

Common approaches are:

- `@Qualifier`
- `@Primary`
- Injecting multiple Beans as a collection when all implementations are required.

## 3. What is `@Qualifier`?

`@Qualifier` tells Spring which specific Bean should be injected when multiple Beans of the same type are available.

Example:

```java
@Qualifier("emailNotificationService")
```

## 4. What is `@Primary`?

`@Primary` tells Spring which Bean should be preferred when multiple Beans of the same type are available.

Example:

```java
@Primary
@Service
public class EmailNotificationService {
}
```

## 5. What is the difference between `@Primary` and `@Qualifier`?

`@Primary` defines a preferred/default Bean.

`@Qualifier` explicitly selects a particular Bean at an injection point.

A useful mental model is:

```text
@Primary   → default choice

@Qualifier → explicit choice
```

## 6. Why was `NotificationService` defined as an interface?

The interface allows multiple implementations without making the Controller depend directly on a specific implementation.

The Controller depends on:

```java
NotificationService
```

rather than:

```java
EmailNotificationService
```

This reduces coupling.

## 7. What does `implements` mean in Java?

`implements` means that a class agrees to follow the contract defined by an interface.

Example:

```java
public class EmailNotificationService
        implements NotificationService {
}
```

The class must provide the methods required by the interface.

## 8. What does `@Service` mean?

`@Service` tells Spring that the class is a component managed by Spring.

Spring creates and manages its Bean inside the application context.

## 9. What is a Spring Bean?

A Spring Bean is an object that is created and managed by the Spring IoC container.

Spring can then inject that Bean into other components when required.

## 10. Why is constructor injection useful here?

Constructor injection makes the Controller's dependency explicit.

```java
public NotificationController(
        NotificationService notificationService) {
    this.notificationService = notificationService;
}
```

It also works well with immutable dependencies and makes the class easier to test.

## 11. What is dependency ambiguity?

Dependency ambiguity occurs when Spring finds multiple Beans that satisfy the same required dependency and cannot determine which one should be selected.

Example:

```text
NotificationService
       ↑
       ├── EmailNotificationService
       └── SmsNotificationService
```

## 12. What was the dependency resolution problem in this exercise?

The Controller requested:

```java
NotificationService
```

But Spring found:

```text
emailNotificationService
smsNotificationService
```

Therefore Spring could not choose one automatically.

We resolved it using:

```java
@Qualifier("emailNotificationService")
```

## 13. Can Spring inject all implementations instead of choosing one?

Yes.

If all implementations are required, Spring can inject them as a collection such as:

```java
List<NotificationService>
```

or:

```java
Map<String, NotificationService>
```

This is useful when the application needs to work with multiple implementations.

## 14. What is the default Bean name for `EmailNotificationService`?

By default, Spring generally uses:

```text
emailNotificationService
```

Therefore this can be used with:

```java
@Qualifier("emailNotificationService")
```

## 15. What is the main lesson from this problem?

When Spring sees one dependency and multiple possible Beans, it needs additional information to resolve the dependency.

Use:

```text
@Qualifier → choose a specific Bean

@Primary   → choose a preferred/default Bean
```