# Problem 1 — Interview Q&A

## Q1. What Is Spring?

Spring is a Java framework used to build backend and enterprise applications.

It provides features such as:

- IoC
- Dependency Injection
- AOP
- Transactions
- Web development
- Database integration

---

## Q2. What Is Spring Boot?

Spring Boot is built on top of Spring and simplifies Spring application development.

It provides:

- Auto-configuration
- Starter dependencies
- Embedded servers
- Easy configuration

---

## Q3. What Is the Difference Between Spring and Spring Boot?

| Spring | Spring Boot |
|---|---|
| Core Java framework | Built on top of Spring |
| Requires more configuration | Reduces configuration |
| More manual setup | Provides sensible defaults |
| Server setup can require configuration | Provides embedded servers |

### Short Answer

> Spring provides the framework; Spring Boot makes Spring easier to build and run.

---

## Q4. What Does `@SpringBootApplication` Do?

It marks the main Spring Boot application class.

It combines:

```text
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
```

---

## Q5. What Does `SpringApplication.run()` Do?

It starts the Spring Boot application.

At a high level, it:

1. Creates the Spring ApplicationContext.
2. Performs auto-configuration.
3. Performs component scanning.
4. Creates required beans.
5. Starts the embedded web server for a web application.

---

## Q6. Does `SpringApplication.run()` Set the Server Port?

No.

It starts the Spring Boot application.

The server port is configured separately.

Example:

```properties
server.port=8081
```

---

## Q7. What Is Maven?

Maven is a build automation and dependency management tool for Java projects.

It helps with:

- Dependency management
- Compilation
- Testing
- Packaging

---

## Q8. What Is `pom.xml`?

`pom.xml` stands for:

> Project Object Model

It is Maven's main project configuration file.

It contains:

- Project information
- Dependencies
- Plugins
- Build configuration

---

## Q9. What Is a Dependency?

A dependency is an external library required by an application.

For example:

```text
Spring Web
```

provides libraries required for web and REST development.

---

## Q10. What Is Spring Web?

Spring Web provides support for building:

- Web applications
- REST APIs
- HTTP endpoints

---

## Q11. Why Did Tomcat Start Automatically?

Because Spring Web was added to the project.

Spring Boot detected that the application is a web application and automatically configured an embedded web server.

---

## Q12. What Is an Embedded Server?

An embedded server is a web server that runs as part of the application.

Spring Boot commonly uses embedded Tomcat for Spring MVC applications.

---

## Q13. What Is the Default Spring Boot Port?

The default HTTP port is:

```text
8080
```

---

## Q14. How Do You Change the Spring Boot Port?

Add this to `application.properties`:

```properties
server.port=8081
```

---

## Q15. What Is `application.properties`?

It is a Spring Boot configuration file used to configure application properties.

Example:

```properties
spring.application.name=my-first-backend
server.port=8081
```

---

## Q16. Where Is `application.properties` Located?

```text
src/main/resources/application.properties
```

---

## Q17. What Is ApplicationContext?

ApplicationContext is Spring's container.

It manages Spring Beans and application configuration.

### Short Answer

> ApplicationContext = Spring container

---

## Q18. What Is a Spring Bean?

A Spring Bean is an object that is created and managed by the Spring container.

---

## Q19. What Is Auto-Configuration?

Auto-configuration is a Spring Boot feature that automatically configures many components based on the application's dependencies and environment.

For example, when Spring Web is present, Spring Boot can automatically configure web application infrastructure and an embedded server.

---

## Q20. What Is Component Scanning?

Component scanning is the process through which Spring searches for Spring-managed components such as:

```text
@Component
@Service
@Repository
@Controller
@RestController
```

and registers them as Beans.

---

## Q21. Why Is Spring Boot Called Opinionated?

Spring Boot provides sensible default configurations so developers don't need to configure everything manually.

---

## Q22. What Happens When a Spring Boot Application Starts?

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

# Quick Interview Revision

## Spring

> Java backend framework

## Spring Boot

> Simplifies Spring development

## Maven

> Build + dependency management

## `pom.xml`

> Maven project configuration

## Spring Web

> Web + REST support

## `@SpringBootApplication`

> Main Spring Boot application class

## `SpringApplication.run()`

> Starts the Spring Boot application

## ApplicationContext

> Spring container

## Bean

> Object managed by Spring

## Tomcat

> Embedded web server

## `application.properties`

> Application configuration

## Default Port

> 8080

---

# Must Remember

## 1. Spring Boot

> Spring Boot simplifies Spring development using auto-configuration, starter dependencies, embedded servers, and sensible defaults.

## 2. `@SpringBootApplication`

> `@SpringBootApplication` combines `@SpringBootConfiguration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

## 3. `SpringApplication.run()`

> `SpringApplication.run()` starts the Spring Boot application.

## 4. Spring Web

> Spring Web provides support for building web applications and REST APIs.

## 5. Embedded Tomcat

> Spring Boot can automatically start an embedded Tomcat server for a web application.

## 6. Default Port

> The default Spring Boot HTTP port is 8080.

## 7. Configuration

> `application.properties` is used for Spring Boot application configuration.

## 8. ApplicationContext

> ApplicationContext is Spring's container for managing Beans and application configuration.