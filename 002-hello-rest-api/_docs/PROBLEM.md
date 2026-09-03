# Problem 2 — Hello REST API

## Objective

Create your first REST API endpoint using Spring Boot.

The endpoint should respond to an HTTP GET request and return a simple text response.

## Requirements

Create a REST controller with the following endpoint:

```text
GET /hello
```

The endpoint must return:

```text
Hello, World!
```

## Expected URL

```text
http://localhost:8080/hello
```

## Expected Response

```text
Hello, World!
```

## Technical Requirements

- Create a controller class.
- Use `@RestController`.
- Create a method to handle the request.
- Map the `/hello` endpoint.
- Handle the endpoint using an HTTP GET request.
- Return `Hello, World!` as the response.
- Run the Spring Boot application successfully.
- Test the endpoint using a browser or API client.

## Concepts Practiced

- REST API
- HTTP GET
- REST Controller
- `@RestController`
- `@RequestMapping`
- Endpoint
- HTTP request
- HTTP response

## Success Criteria

The application starts successfully and the following request:

```text
GET /hello
```

returns:

```text
Hello, World!
```

## Folder Structure

```text
002-hello-rest-api/
├── PROBLEM.md
├── Notes.md
├── InterviewQnA.md
├── pom.xml
└── src/
```

## Completion Checklist

- [x] Spring Boot project created.
- [x] REST controller created.
- [x] `/hello` endpoint created.
- [x] GET request handled.
- [x] `Hello, World!` returned.
- [x] Endpoint tested successfully.
- [ ] Notes.md completed.
- [ ] InterviewQnA.md completed.
- [ ] Changes committed.
- [ ] Changes pushed to GitHub.