# SpringBoot Project

Simple [springboot](https://spring.io/projects/spring-boot) project i'm exercising with during my first internship.

## Project makes use of:

- **Lombok**: To reduce [boilerplate code](https://en.wikipedia.org/wiki/Boilerplate_code) (getter/setter, constructors, ...)
- **MapStruct**: For automatic mapping of objects (example: Entity <-> DTO)
- **Exception Handling**: Common exceptions thrown on runtime are intercepted by an exception handler
- **Authentication**: A [JWT token](https://en.wikipedia.org/wiki/JSON_Web_Token) is issued to Users whenever they pass the Login process
- **Roles**: Extracted from the token, they restrict who can do what based on the request and table you want to access
- **Other**: ...
