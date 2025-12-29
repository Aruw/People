# 👥 People - GraphQL API

A clean and modern **Spring Boot GraphQL API** created **for educational purposes**, focused on demonstrating how to model, expose, and consume **People** data — including personal information, demographics, and contact details.

This project is **not a full production-ready system**. It intentionally keeps the scope limited to make the core concepts easier to understand, such as GraphQL schemas, queries, mutations, validation, and persistence. The API is designed to be **flexible**, **type-safe**, and **efficient**, allowing clients to request exactly the fields they need while avoiding over-fetching.

---

## ☕ Buy me a coffee

If this content was useful, your support makes a real difference and helps me continue sharing knowledge with the community!

[![Buy me a coffee](https://raw.githubusercontent.com/Aruw/Resources/refs/heads/main/buy-me-a-coffe-banner.jpg)](https://buymeacoffee.com/aruw)

---

## ✨ Features

- 📌 Full **CRUD operations** for People
- 🔍 Query single or multiple people
- 🧩 Strongly typed GraphQL schema
- ✅ Bean Validation for input data
- 🗄️ Persistence with **JPA + MariaDB**
- 🧭 Interactive **GraphiQL** playground
- 📅 Extended scalars for `LocalDate`, `LocalDateTime`, and `BigDecimal`

---

## 🛠️ Technologies Used

- Maven
- Lombok
- Java 25
- MariaDB
- MapStruct
- Spring Boot 4
- Spring GraphQL
- Spring Data JPA
- GraphQL Java Extended Scalars

---

## 📋 Prerequisites

Make sure you have the following installed:

- Java 25
- Maven 3.9+
- Docker Desktop (required for automatic database startup)

---

## ▶️ Running the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/Aruw/People
cd people
```

### 2️⃣ Start the application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080/people-api
```

---

## 🐳 Database (MariaDB via Docker)

This project uses **Spring Boot Docker Compose integration** to automatically start a **MariaDB** database when the application runs.

### How it works

- When the application starts, Spring Boot detects the Docker Compose configuration
- A MariaDB container is automatically started
- The application connects to the database with no manual configuration
- When the application stops, the container can be stopped automatically

### ⚠️ Requirement

Docker Desktop **must be installed and running** before starting the application.

If Docker is not available, the application will fail to start because the database will not be reachable.

Supported environments:
- Docker Desktop (Windows / macOS)
- Docker Engine (Linux)

---

## 🔗 GraphQL Endpoint

```
POST http://localhost:8080/graphql
```

This endpoint handles all GraphQL queries and mutations.

---

## 🧪 GraphiQL Endpoint

```
http://localhost:8080/graphiql
```

### What does GraphiQL do?

GraphiQL is an interactive GraphQL IDE that allows you to:

- Explore the GraphQL schema and documentation
- Write and test queries and mutations
- Validate inputs and inspect responses
- Debug GraphQL requests efficiently

---

## 📁 Project Structure

```
src/main/java/com/aruw/people
 ├── config
 ├── controller
 ├── dto
 ├── enums
 ├── exception
 ├── handler
 ├── mapper
 ├── model
 ├── repository
 ├── service
 └── PeopleApplication
```

---

## 📊 Available Operations

### 🔍 Queries

| Name | Description |
|-----|------------|
| getAllPeople | Returns all people |
| getPersonById(id) | Returns a person by ID |

### ✏️ Mutations

| Name | Description |
|-----|------------|
| createPerson(input) | Creates a new person |
| updatePerson(id, input) | Updates an existing person |
| deletePerson(id) | Deletes a person by ID |

---

## 🧩 Example Query

```graphql
query {
  getAllPeople {
    personId
    firstName
    lastName
    email
  }
}
```

---

## ✍️ Example Mutation

```graphql
mutation {
  createPerson(input: {
    firstName: "John"
    lastName: "Doe"
    documentNumber: "123456789"
    birthDate: "1990-01-01"
    gender: MALE
    maritalStatus: SINGLE
    nationality: "Brazilian"
    email: "john.doe@email.com"
    phoneNumber: "+55 11 99999-9999"
  }) {
    personId
    firstName
    lastName
  }
}
```

---

## 🤝 Contributing

Pull requests are welcome!  
Feel free to open issues, suggest improvements, or propose new features. 💡

---

## 📜 License

This project is provided for educational and personal use.

---

Enjoy building! 🚀
