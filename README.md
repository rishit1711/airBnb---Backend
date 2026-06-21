![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Railway](https://img.shields.io/badge/Deployed-Railway-purple)

# Airbnb Backend Clone

A production-inspired hotel booking backend built using Spring Boot that focuses on real-world backend engineering challenges such as inventory management, concurrent bookings, dynamic pricing, booking lifecycle management, and payment processing.

---

## Links

### GitHub Repository
https://github.com/<your-username>/<your-repository>

### Live API Documentation (Swagger UI)
https://airbnb-backend-production-bc0b.up.railway.app/swagger-ui/index.html

### OpenAPI Specification
https://airbnb-backend-production-bc0b.up.railway.app/v3/api-docs

---

## Features

### Authentication & Authorization
- JWT-based authentication
- Role-based access control (User & Hotel Manager)

### Hotel Management
- Create and manage hotels
- Manage room types, amenities, and photos

### Inventory Management
- Date-wise room availability
- Inventory locking mechanism
- Overbooking prevention

### Booking System
- Hotel search
- Room booking workflow
- Guest management
- Booking lifecycle management

### Payment Module
- Payment processing
- Transaction tracking
- Payment status management

### Dynamic Pricing
- Occupancy-based pricing
- Urgency pricing
- Holiday pricing
- Discount strategies

---

## Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JWT

---

## Architecture Highlights

- Layered Architecture
- Strategy Design Pattern
- Inventory-Driven Booking System
- Transaction Management
- RESTful APIs
- Database-Centric Booking Workflow

---

## Core Entities

- User
- Hotel
- Room
- Inventory
- Booking
- Guest
- Payment

---

## Booking Workflow

```text
Search Hotel
    ↓
Check Inventory
    ↓
Create Booking
    ↓
Lock Inventory
    ↓
Process Payment
    ↓
Confirm Booking
```

---

## Key Backend Concepts Implemented

- Authentication & Authorization
- Inventory Management
- Booking Lifecycle Handling
- Payment Workflow
- Transaction Consistency
- Dynamic Pricing Strategies
- Exception Handling
- DTO-Based API Design
- Database Relationships & Mapping

---

## Future Improvements

- Redis Caching
- Docker Support
- Elasticsearch-Based Search
- Microservices Migration
- Distributed Inventory Locking

---

## Learning Outcomes

- Inventory Management Systems
- Concurrent Booking Handling
- Transaction Management
- Dynamic Pricing Engines
- Scalable Backend Design
- Real-World Booking System Architecture

---

## Purpose

This project was built to understand how real-world hotel booking platforms handle inventory, reservations, pricing, authentication, and payment workflows beyond basic CRUD applications.

The primary focus was gaining hands-on experience with backend architecture, transactional workflows, inventory-driven systems, and scalable application design using Spring Boot.
