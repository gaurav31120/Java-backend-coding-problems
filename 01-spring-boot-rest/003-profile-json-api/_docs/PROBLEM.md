# Problem 3 — Profile JSON API

## Objective

Create a Spring Boot REST API that returns a user profile as JSON.

## Requirements

Create the following REST endpoint:

```text
GET /profile
```

The endpoint must return a profile containing:

- Name
- Age
- City

## Expected URL

```text
http://localhost:8080/profile
```

## Expected Response

The API should return JSON similar to:

```json
{
  "name": "Gaurav",
  "age": 25,
  "city": "Patna"
}
```

The exact values may be chosen by the developer.

## Technical Requirements

- Create a REST controller.
- Create a Java class to represent the profile.
- Add fields for name, age, and city.
- Create a GET `/profile` endpoint.
- Return the profile object from the controller.
- Allow Spring Boot to convert the Java object into JSON.
- Run the application successfully.
- Test the endpoint using a browser or API client.

## Concepts Practiced

- REST API
- HTTP GET
- JSON
- Java objects
- `@RestController`
- Request mapping
- Object-to-JSON conversion
- HTTP response body

## Success Criteria

The application starts successfully and:

```text
GET /profile
```

returns a JSON response containing:

```text
name
age
city
```

## Completion Checklist

- [ ] Spring Boot project created.
- [ ] Profile class created.
- [ ] Profile fields created.
- [ ] REST controller created.
- [ ] `/profile` endpoint created.
- [ ] Profile object returned from the endpoint.
- [ ] JSON response verified.
- [ ] Notes.md completed.
- [ ] InterviewQnA.md completed.
- [ ] Changes committed.
- [ ] Changes pushed to GitHub.