# Problem 1 — First Spring Boot App

## 1. What Is Spring?

Spring is a Java framework used to build backend applications.

### Main Features

- IoC
- Dependency Injection
- AOP
- Database support
- Transactions
- Security
- Web development

### Remember

> Spring = Java framework

---

## 2. What Is Spring Boot?

Spring Boot is built on top of Spring and makes Spring development easier.

### Main Benefits

- Auto-configuration
- Starter dependencies
- Embedded server
- Easy configuration

### Remember

> Spring = Framework  
> Spring Boot = Simplifies Spring

---

## 3. What Is Maven?

Maven is a build and dependency management tool for Java projects.

### Maven Helps With

- Downloading dependencies
- Compiling code
- Running tests
- Packaging applications

### Main File

```text
pom.xml
```

### Remember

> `pom.xml` = Maven project configuration

---

## 4. What Is Spring Web?

Spring Web is used to build:

- Web applications
- REST APIs
- HTTP endpoints

Because Spring Web is present, Spring Boot can automatically configure an embedded web server.

---

## 5. Main Application Class

```java
package com.practice.firstspringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

## 6. `@SpringBootApplication`

`@SpringBootApplication` marks the main Spring Boot application class.

It combines:

```text
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
```

### Remember

> `@SpringBootApplication` = Main Spring Boot application class

---

## 7. `SpringApplication.run()`

```java
SpringApplication.run(Application.class, args);
```

It starts the Spring Boot application.

### High-Level Flow

```text
main()
   ↓
SpringApplication.run()
   ↓
Spring Boot starts
   ↓
ApplicationContext created
   ↓
Configuration + Beans loaded
   ↓
Embedded server starts
```

### Important

`SpringApplication.run()` starts the application.

It does **not** directly set the server port.

---

## 8. ApplicationContext

`ApplicationContext` is Spring's container.

It manages Spring objects called **Beans**.

### Remember

> ApplicationContext = Spring's container

We will study this in detail in the Spring Core problems.

---

## 9. Embedded Tomcat

Because we added Spring Web, Spring Boot automatically started an embedded Tomcat server.

Our application showed:

```text
Tomcat started on port 8080
```

### High-Level Flow

```text
Spring Web
    ↓
Web Application
    ↓
Embedded Tomcat
```

---

## 10. Default Port

The default Spring Boot HTTP port is:

```text
8080
```

### Change the Port

In `application.properties`:

```properties
server.port=8081
```

---

## 11. `application.properties`

### Location

```text
src/main/resources/application.properties
```

It is used for application configuration.

### Our Configuration

```properties
spring.application.name=my-first-backend
```

After restarting the application, the logs showed:

```text
[my-first-backend]
```

This proved that Spring Boot successfully read the configuration.

---

## 12. Project Structure

```text
001-first-spring-boot-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.practice.firstspringbootapp/
│   │   │       └── Application.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── pom.xml
├── .gitignore
├── mvnw
└── mvnw.cmd
```

---

## 13. What Happens When the Application Starts?

### High-Level Understanding

```text
Java main()
      ↓
SpringApplication.run()
      ↓
Spring Boot starts
      ↓
ApplicationContext created
      ↓
Auto-configuration
      ↓
Component scanning
      ↓
Beans created
      ↓
Embedded Tomcat starts
      ↓
Application running
```

---

## 14. Important Configuration

### Application Name

```properties
spring.application.name=my-first-backend
```

### Server Port

```properties
server.port=8081
```

### Default Port

```text
8080
```

---

## 15. Key Terms

| Term | Meaning |
|---|---|
| Spring | Java framework |
| Spring Boot | Simplifies Spring development |
| Maven | Build + dependency management |
| `pom.xml` | Maven configuration |
| Spring Web | Web + REST support |
| `@SpringBootApplication` | Main Spring Boot application class |
| `SpringApplication.run()` | Starts Spring Boot |
| ApplicationContext | Spring container |
| Bean | Object managed by Spring |
| Tomcat | Embedded web server |
| `application.properties` | Application configuration |
| 8080 | Default HTTP port |

---

## 16. Key Takeaways

```text
Spring
→ Java framework

Spring Boot
→ Simplifies Spring

Maven
→ Build + dependency management

pom.xml
→ Maven configuration

Spring Web
→ Web + REST support

@SpringBootApplication
→ Main Spring Boot application class

SpringApplication.run()
→ Starts Spring Boot

ApplicationContext
→ Spring container

Tomcat
→ Embedded web server

application.properties
→ Application configuration

8080
→ Default HTTP port
```

---

## 17. What I Practiced

- [x] Created Spring Boot project
- [x] Used Java 21
- [x] Used Maven
- [x] Added Spring Web
- [x] Ran Spring Boot application
- [x] Verified embedded Tomcat
- [x] Verified port 8080
- [x] Changed application name
- [x] Verified configuration in logs
- [x] Understood `@SpringBootApplication`
- [x] Understood `SpringApplication.run()`

---

## 18. One-Line Revision

> `@SpringBootApplication` marks the main Spring Boot application class, and `SpringApplication.run()` starts the Spring Boot application and its required infrastructure.