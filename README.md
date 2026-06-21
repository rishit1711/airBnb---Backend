# Airbnb Backend Clone

A production-inspired hotel booking backend built using Spring Boot that focuses on real-world backend engineering challenges such as inventory management, concurrent bookings, dynamic pricing, booking lifecycle management, and payment processing.

---

## Live API Documentation

Explore and test the deployed REST APIs using Swagger UI.

### Swagger UI
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
- Booking lifecycle handling

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
- REST APIs

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

## Booking Flow

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

## Future Improvements

- Redis Caching
- Docker Support
- Elasticsearch Search

---

## Learning Outcomes

- Inventory Management
- Concurrent Booking Handling
- Transaction Consistency
- Dynamic Pricing Systems
- Scalable Backend Design

---

## Purpose

This project was built to understand how real-world booking systems handle inventory, pricing, reservations, and payments beyond basic CRUD operations.

The primary focus was to gain hands-on experience with backend architecture, transactional workflows, inventory management, pricing strategies, authentication, and scalable system design using Spring Boot.
