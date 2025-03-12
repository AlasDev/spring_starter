# SpringBoot Project

Simple [springboot](https://spring.io/projects/spring-boot) project i'm exercising with during my first internship.

## Project makes use of:

- **Lombok**: To reduce [boilerplate](https://en.wikipedia.org/wiki/Boilerplate_code) code (getter/setter, constructors, ...)
- **MapStruct**: For automatic mapping of objects (example: Entity <-> DTO)
- **Exception Handling**: Common exceptions thrown on runtime are intercepted by an exception handler
- **Authentication**: A [JWT token](https://en.wikipedia.org/wiki/JSON_Web_Token) is issued to Users whenever they pass the Login process
- **Role**: Extracted from the token, it restricts who can do what
