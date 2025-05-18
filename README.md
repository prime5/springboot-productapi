# Spring Boot Product API

A RESTful API built with Spring Boot and MongoDB for managing products. This application demonstrates CRUD operations using Spring Data MongoDB and follows clean layered architecture.

## Features

- Spring Boot 3.4.x
- MongoDB integration with Spring Data
- RESTful CRUD operations
- JSON-based APIs
- Local development via IntelliJ or `mvn spring-boot:run`

## Getting Started

### Prerequisites

- Java 17+
- MongoDB running locally on `localhost:27017`
- Maven

### Running the Application

```bash
mvn spring-boot:run
Or run ProductApiApplication.java directly in your IDE.

API Endpoints
Method	Endpoint	Description
GET	/api/products	Get all products
GET	/api/products/{id}	Get a product by ID
POST	/api/products	Create a new product
PUT	/api/products/{id}	Update an existing one
DELETE	/api/products/{id}	Delete a product

Example Payload
json
Copy
Edit
{
  "name": "iPhone",
  "description": "iPhone 15 Pro",
  "price": 1299.99
}

Authentication
Basic Auth Users
Username	Password	Role
admin	secret123	ADMIN
guest	guest123	USER

GET endpoints are public

POST, PUT, and DELETE require ADMIN role

Example Authenticated Request
bash
Copy
Edit
curl -X POST http://localhost:8080/api/products \
  -u admin:secret123 \
  -H "Content-Type: application/json" \
  -d '{"name": "Secured Product", "description": "Protected", "price": 199.99}'

Testing
Unit tests are written for all CRUD endpoints using:

JUnit 5

Mockito (@MockBean)

Spring Boot's @WebMvcTest + MockMvc

Run tests:
bash
Copy
Edit
mvn test
MongoDB Setup
Host: localhost:27017

Database: productdb

Collection: products

You can inspect data using MongoDB Compass or shell.

License
MIT

yaml
Copy
Edit

---

### ✅ Now:

1. Add it to `README.md`
2. Commit + push:

```bash
git add README.md
git commit -m "Update README with test coverage and API details"
git push

Docker Support
This project includes a complete Dockerfile and docker-compose.yml to run the Spring Boot app with MongoDB locally in containers.

🔧 Build and Run
bash
Copy
Edit
./mvnw clean package -DskipTests
docker compose up --build
This will:

Start MongoDB on localhost:27017

Start the Spring Boot app on localhost:8080

🧪 Test the API
Check if the app is running:

bash
Copy
Edit
curl http://localhost:8080/api/products
Add a product (requires Basic Auth):

bash
Copy
Edit
curl -X POST http://localhost:8080/api/products \
  -u admin:secret123 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Docker Success",
    "description": "Deployed in a container!",
    "price": 123.45
}'
🔁 Tear Down
bash
Copy
Edit
docker compose down -v
