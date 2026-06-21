

# Airbnb Backend Clone

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Railway](https://img.shields.io/badge/Deployment-Railway-purple)
![Supabase](https://img.shields.io/badge/Database-Supabase-green)

A production-inspired hotel booking backend built using **Spring Boot** that simulates the core functionalities of modern booking platforms such as Airbnb.

The project focuses on authentication, hotel management, inventory tracking, booking workflows, payment integration, and dynamic pricing while following clean backend architecture principles.

---

## 🚀 Live Links

### Live Application

https://airbnb-backend-production-bc0b.up.railway.app

### Swagger Documentation

https://airbnb-backend-production-bc0b.up.railway.app/api/v1/swagger-ui/index.html

### OpenAPI Specification

https://airbnb-backend-production-bc0b.up.railway.app/api/v1/v3/api-docs

---

## ✨ Features

### Authentication & Authorization

- JWT-based Authentication
- User Registration & Login
- Role-Based Access Control
  - User
  - Hotel Manager

### Hotel Management

- Create Hotels
- Manage Room Types
- Hotel Ownership Management
- Hotel Information Management

### Inventory Management

- Date-wise Room Inventory Tracking
- Availability Validation
- Inventory Updates During Booking Lifecycle
- Booking-Based Inventory Reduction

### Booking System

- Hotel Search
- Booking Creation
- Guest Management
- Booking Status Tracking
- Booking Confirmation Workflow

### Payment Integration

- Stripe Payment Integration
- Payment Status Tracking
- Transaction Recording

### Dynamic Pricing

- Occupancy-Based Pricing
- Holiday Pricing Strategy
- Urgency Pricing Strategy
- Discount Strategy Support

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database

- PostgreSQL
- Supabase

### Authentication

- JWT (JSON Web Token)

### Payment Gateway

- Stripe

### Deployment

- Railway

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
Spring Security (JWT)
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ▼
Repositories
   │
   ▼
PostgreSQL (Supabase)
