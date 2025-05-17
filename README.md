# springboot-productapi
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
Database
MongoDB must be running locally

Database name: productdb

Collection: products

License
MIT

yaml
Copy
Edit

---

Drop that into `README.md`, commit + push:

```bash
git add README.md
git commit -m "Add README for product API"
git push
