# POS24 Backend

A Spring Boot backend application for managing customer service tickets and follow-ups.

## Technologies

- Java 17
- Spring Boot 3.2.3
- Spring Security with JWT Authentication
- Spring Data JPA
- PostgreSQL
- MapStruct
- Lombok
- Maven

## Features

- User Authentication and Authorization
- Customer Management
- Service Ticket Management
- Follow-up Management
- Exception Handling
- Data Validation
- Audit Fields (createdAt, updatedAt)

## Project Structure

```
src/main/java/com/pos24/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── exception/       # Custom exceptions and handlers
├── mapper/          # MapStruct mappers
├── repository/      # Spring Data repositories
├── security/        # Security configurations and JWT
└── service/         # Business logic
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

### Configuration

1. Clone the repository:
```bash
git clone https://github.com/josivantarcio/pos24.git
```

2. Configure the database in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Authentication
- POST `/api/auth/login` - User login

### Customers
- GET `/api/clientes` - List all customers
- GET `/api/clientes/{id}` - Get customer by ID
- POST `/api/clientes` - Create new customer
- PUT `/api/clientes/{id}` - Update customer
- DELETE `/api/clientes/{id}` - Delete customer

### Service Tickets
- GET `/api/chamados` - List all tickets
- GET `/api/chamados/{id}` - Get ticket by ID
- POST `/api/chamados` - Create new ticket
- PUT `/api/chamados/{id}` - Update ticket
- DELETE `/api/chamados/{id}` - Delete ticket

### Follow-ups
- GET `/api/followups` - List all follow-ups
- GET `/api/followups/{id}` - Get follow-up by ID
- POST `/api/followups` - Create new follow-up
- PUT `/api/followups/{id}` - Update follow-up
- DELETE `/api/followups/{id}` - Delete follow-up

## Security

The application uses JWT (JSON Web Token) for authentication. To access protected endpoints:

1. Obtain a JWT token by authenticating through the login endpoint
2. Include the token in the Authorization header of subsequent requests:
```
Authorization: Bearer your_jwt_token
```

## Error Handling

The application includes comprehensive error handling for:
- Resource not found
- Invalid arguments
- Validation errors
- Authentication/Authorization errors
- Generic exceptions

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 