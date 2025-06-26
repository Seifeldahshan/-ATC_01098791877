This backend is built with Spring Boot and provides RESTful APIs for a booking system with authentication and role-based access control. Key features include:

1-User registration and login with JWT authentication.

2-Role-based authorization for users and admins.

3-Admin panel APIs to manage categories, events, and bookings.

4-Automatic database table creation via JPA/Hibernate.

5-Image upload handling for events.

6-Secure endpoints protected according to user roles.

The backend connects to a relational database ( MySQL) to persist data and serves as the core business logic layer for the booking application.

the Database setup in src/main/resources/booking_sys.sql

ADMIN account for testing admin panel - email: saifsayed819@gmail.com - pass: asterqwdv
