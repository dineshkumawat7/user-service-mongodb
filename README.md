User Management Microservice

Overview

This is a User Management Microservice developed using Spring Boot. It provides APIs for managing user-related operations such as creating, retrieving, updating, and deleting user information.

Features

User Registration: Create new user accounts.

User Authentication: Authenticate users securely using JWT.

CRUD Operations: Perform Create, Read, Update, and Delete operations on user data.

Role-Based Access Control: Manage permissions based on user roles.

Integration Ready: Easily integrate with other microservices.

Prerequisites

Java 17 or higher

Maven 3.8+

Spring Boot 3.1+

Database: MySQL/PostgreSQL (or any compatible relational database)

IDE: IntelliJ IDEA, Eclipse, or any preferred IDE

Installation

Clone the Repository:

git clone https://github.com/your-repo/user-management-microservice.git
cd user-management-microservice

Set Up Database:

Create a database named user_management.

Update the application.properties or application.yml file with your database connection details.

spring.datasource.url=jdbc:mysql://localhost:3306/user_management
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

Build the Project:

mvn clean install

Run the Application:

mvn spring-boot:run

API Endpoints

Authentication

POST /api/auth/login: Login and receive a JWT token.

POST /api/auth/register: Register a new user.

User Management

GET /api/users: Retrieve a list of all users.

GET /api/users/{id}: Retrieve details of a specific user by ID.

POST /api/users: Create a new user.

PUT /api/users/{id}: Update user details.

DELETE /api/users/{id}: Delete a user.

Configuration

Environment Variables

You can override default configurations by setting environment variables:

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

JWT_SECRET

Properties File

Default configurations are defined in src/main/resources/application.properties.

Architecture

This microservice follows a layered architecture:

Controller Layer: Handles API requests and responses.

Service Layer: Contains business logic.

Repository Layer: Interacts with the database.

Model Layer: Defines entities and data transfer objects (DTOs).

Technologies Used

Spring Boot: Framework for building the microservice.

Spring Security: Authentication and authorization.

JWT: Token-based authentication.

Hibernate: ORM framework for database interactions.

MySQL: Relational database.

Swagger: API documentation.

Testing

Unit tests are written using JUnit 5.

Mocking is done using Mockito.

To run tests:

mvn test

API Documentation

Swagger UI is available at: http://localhost:8080/swagger-ui.html

OpenAPI specification is available at: http://localhost:8080/v3/api-docs

Deployment

Docker:

Build the Docker image:

docker build -t user-management-service .

Run the container:

docker run -p 8080:8080 user-management-service

Kubernetes:

Create a deployment and service YAML file.

Apply the configurations:

kubectl apply -f deployment.yaml

Contribution

Contributions are welcome! Please follow these steps:

Fork the repository.

Create a new branch for your feature or bug fix.

Submit a pull request with detailed description.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Contact

For any questions or support, please contact:

Email: support@yourdomain.com

GitHub Issues: https://github.com/your-repo/issues

