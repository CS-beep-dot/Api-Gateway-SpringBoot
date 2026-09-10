# API Gateway & Authentication Service

'A production-grade, microservice-ready API Gateway and Authentication system built with Java 21 and Spring Boot 3. Designed following Enterprise Clean Architecture principles, strict security patterns, and modular database migrations.'

---

## 💡 Project Concept & Purpose

This system serves as a **Centralized API Gateway and Auth Server** for modern microservices architectures. 

Instead of duplicating security and authentication logic across every service, this system acts as the single entry point. It manages:
* **Identity & Access Management:** User registration, password encryption, and stateless JWT token issuance.
* **Request Security:** Verifying user credentials and authorizing incoming traffic.
* **Centralized Routing:** (Planned) Securely routing external requests to downstream microservices.

---

## 🛠 Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Security:** Spring Security, BCrypt Hashing, JWT (JSON Web Tokens)
* **Database & Persistence:** PostgreSQL, Spring Data JPA, Hibernate
* **Database Migrations:** Flyway
* **Architecture:** Enterprise Clean Architecture (Layered separation of concerns)
* **Build Tool & Utilities:** Maven, Lombok

---

## 📌 Project Status & Roadmap

### Phase 1: Core Domain & Authentication (Current)
- [x] Database Schema Setup & Role Seeding via Flyway
- [x] User & Role JPA Domain Entities
- [x] Service Layer Implementation ('AuthService')
  - [x] User Registration with duplicate username validation
  - [x] BCrypt Password Hashing ('PasswordEncoder')
  - [x] Credentials Verification & Authentication Logic ('login')
- [ ] DTO Layer ('RegisterRequest', 'LoginRequest', 'AuthResponse')
- [ ] REST Controller Layer ('AuthController')

### Phase 2: Security & Stateless Authentication
- [ ] JWT Generation & Validation Utility ('JwtTokenProvider')
- [ ] Stateless Authentication Filter ('JwtAuthenticationFilter')
- [ ] Spring Security Endpoint Authorization Rules

### Phase 3: Gateway Integration & DevOps
- [ ] Centralized API Gateway Routing
- [ ] Docker Containerization ('Dockerfile' & 'docker-compose')
- [ ] GitHub Actions CI/CD Pipeline

---

## 🚀 Getting Started

### Prerequisites
* JDK 21+
* Maven 3.8+
* PostgreSQL DB running on 'localhost:5432'

### Database Setup
1. Create a PostgreSQL database named 'api_gateway_db'.
2. Configure credentials in 'src/main/resources/application.properties'.
3. Flyway will automatically execute database migrations on application startup.
