# HOTEL MANAGEMENT SYSTEM

The Hotel Management System is a modern web application built with **Spring Boot**, **JPA (Hibernate)**, and **PostgreSQL**. It enables hotel staff to manage room bookings, track real-time room availability, and handle customer records efficiently. The system features a clean REST API backend and a user-friendly HTML/JavaScript frontend.

It allows users to book rooms, view all bookings, check room availability, and delete bookings—all through an intuitive web interface.

---

# FEATURES

- **BOOK ROOMS:** Book AC or Non-AC rooms for customers with automatic room number assignment.
- **REAL-TIME ROOM AVAILABILITY:** Instantly see how many AC/Non-AC rooms are available.
- **VIEW ALL BOOKINGS:** Display all current bookings with customer details and assigned room numbers.
- **DELETE BOOKINGS:** Remove a customer booking and automatically update room availability.
- **PERSISTENT DATA:** All bookings and room assignments are stored in PostgreSQL for reliability.
- **RESPONSIVE WEB UI:** Simple, interactive HTML frontend for easy hotel management.

---

# TECHNOLOGIES USED

- **Java 17+**: Core backend logic and REST API.
- **Spring Boot 3.2+**: Rapid application development and RESTful services.
- **Spring Data JPA (Hibernate)**: ORM for database persistence.
- **PostgreSQL 15+**: Relational database for storing bookings.
- **HTML, CSS, JavaScript**: Frontend user interface.
- **Maven**: Build automation and dependency management.

---

# DEPENDENCIES

This project uses the following Maven dependencies (see `pom.xml`):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

---

# SPRING BOOT CONFIGURATION

See `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080
```

---

# 🖥 PREREQUISITES

- Java JDK 17+
- PostgreSQL 15+
- Apache Maven 3.8.6+
- Internet connection (for Maven dependencies)
- IDE like VS Code / IntelliJ IDEA (recommended)

---

# 🗄 DATABASE SETUP

Create a PostgreSQL database named `hotel_db`:

```sql
CREATE DATABASE hotel_db;
```

Spring Boot and JPA will auto-create the necessary tables on first run.

---

# PROJECT STRUCTURE

```bash
 Hotel
└── src
    └── main
        ├── java
        │   └── com
        │       └── example
        │           └── Hotel
        │               ├── Booking.java
        │               ├── BookingController.java
        │               ├── BookingRepository.java
        │               ├── BookingService.java
        │               └── HotelApplication.java
        └── resources
            ├── application.properties
            ├── static
            │   └── hotel.html
            └── templates
                └── index.html
```

---

# HOW TO RUN

1. **Ensure PostgreSQL is running and the `hotel_db` database exists.**
2. **Update DB credentials** in `application.properties` if needed.
3. **Build and run the backend:**
   ```bash
   cd Hotel
   ./mvnw spring-boot:run
   ```
4. **Open the frontend:**
   - Open `Hotel/src/main/resources/static/hotel.html` in your browser.

---

# API ENDPOINTS

- `POST /api/hotel_db/bookings` — Book rooms (JSON: customerName, contact, roomType, count)
- `GET /api/hotel_db/bookings` — List all bookings
- `DELETE /api/hotel_db/bookings?id={bookingId}` — Delete a booking by ID
- `GET /api/hotel_db/rooms/availability?type=AC|Non-AC` — Get room availability

---

# CONTACT

- **Developer:** *Venkatesh Soma*
- **Email:** venkateshsoma2305@gmail.com
- **GitHub:** *venkatesh-soma* 

---

# SCREENSHOT

![](Hotel.png) 