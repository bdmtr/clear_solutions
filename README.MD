## Java practical test assignment for Clear Solutions

RESTful API based on the web Spring Boot application: controller, responsible for the resource named Users.


### Endpoints description

| Endpoint           | Http method | Action                                   | Input                                                   |
|--------------------|-------------|------------------------------------------|---------------------------------------------------------|
| /api/v1/users/add  | POST	       | Add new User                             | Request body: JSON                                      |
| /api/v1/users/{id} | PATCH	      | Update one and more user properties by id | Path variable: id; Request body: JSON              |
| /api/v1/users/{id}        | PUT         | Update all user properties by id         | Path variable: id; Request body: JSON              |
| /api/v1/users/{id}        | DELETE	     | Delete user by id                        | Path variable: id                                  |
| /api/v1/users/dates       | GET	        | Search for users by birthdate range      | Request parameter: fromDate; Request parameter: fromDate |


## Requirements
### Fields
1. **Email (required)**: Add validation against email pattern.
2. **First name (required)**.
3. **Last name (required)**.
4. **Birth date (required)**: Value must be earlier than the current date.
5. **Address (optional)**.
6. **Phone number (optional)**.

### Functionality
1. **Create user**: It allows registering users who are more than 18 years old. The value "18" should be taken from a properties file.
2. **Update one/some user fields**.
3. **Update all user fields**.
4. **Delete user**.
5. **Search for users by birth date range**: Add validation that checks that "From" is less than "To". Should return a list of objects.

### Additional Requirements
1. Code is covered by unit tests using Spring.
2. Code has error handling for REST.
3. API responses are in JSON format.
