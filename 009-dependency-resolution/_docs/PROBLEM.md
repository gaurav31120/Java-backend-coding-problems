# Problem 9 — Dependency Resolution

## Topic

Spring Core — IoC and Dependency Resolution

## Difficulty

🟠 Intermediate

## Objective

Learn how Spring resolves dependencies when multiple beans of the same type exist in the application context.

## Scenario

Our application has two different notification services:

```text
NotificationService
       ↑
       ├── EmailNotificationService
       └── SmsNotificationService
```

Both services should be managed by Spring.

The Controller will depend on `NotificationService`.

Spring must determine which implementation should be injected.

## Requirements

### 1. Create NotificationService

Create an interface:

```java
NotificationService
```

with a method:

```text
sendNotification()
```

### 2. Create EmailNotificationService

Create an implementation of `NotificationService`.

It should return:

```text
Email notification sent
```

Make it a Spring-managed Bean.

### 3. Create SmsNotificationService

Create another implementation of `NotificationService`.

It should return:

```text
SMS notification sent
```

Make it a Spring-managed Bean.

### 4. Create NotificationController

Create a REST Controller that depends on:

```java
NotificationService
```

Use Constructor Injection.

### 5. Create API

Create:

```text
GET /notification
```

The API should call:

```text
sendNotification()
```

and return the result.

## Important Challenge

There are now two Spring Beans implementing:

```text
NotificationService
```

Spring may not know which implementation should be injected.

Observe the startup behavior and understand the dependency-resolution problem.

## Concepts Learned

- IoC
- Spring Bean
- Interface
- Multiple implementations
- Dependency resolution
- Constructor Injection
- Bean ambiguity

## Success Criteria

Understand why Spring cannot automatically choose between multiple beans of the same interface type.

Then resolve the ambiguity using an appropriate Spring mechanism and make:

```text
GET /notification
```

return the intended notification message.****