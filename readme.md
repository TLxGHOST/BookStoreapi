# Bookstore API - Spring Boot Application

## About This Project

This is a simple Bookstore API built with Spring Boot that lets you:
- Create user accounts and log in with JWT tokens
- Add, view, update, and delete books
- Search and filter books by different criteria

##  Technologies Used

- **Java 17**
- **Spring Boot 3**
- **H2 Database** (in-memory)
- **JWT Authentication**
- **Maven**

##  Getting Started

### Prerequisites
- Java 17 JDK
- Maven
- Postman (or similar API client)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/TLxGHOST/BookStoreapi.git
   ```
2. Navigate to project directory:
```bash
   cd bookstore-api
   ```
3. Run the application:
```bash
   mvn spring-boot:run
   ```
###  Authentication
1. Register user
```bash
   POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "yourpassword123"
}
   ```
2. Login
   ```bash
 POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "yourpassword123"
}
   ```
3. Create Book
   ```bash
POST /api/books
Authorization: Bearer your.jwt.token.here
Content-Type: application/json

{
  "title": "Harry Potter",
  "author": "J.K. Rowling",
  "category": "Fantasy",
  "price": 19.99,
  "rating": 4.8,
  "publishedDate": "1997-06-26"
}
   ```
##  Other Book Operations

- **GET /api/books/{id} - Get single book**
- **PUT /api/books/{id} - Update book**
- **DELETE /api/books/{id}** 

## Author 
-[Tejanshu Bhandari](https://github.com/TLxGHOST)
-[Harshit Raj](https://github.com/Haarshit19)
-[Maanveer Singh](https://github.com/Maanveer23)
-[Purushottam](https://github.com/purushottam840)
