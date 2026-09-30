# Job Recommendation System

## About the Project

The Job Recommendation System is a web-based application that recommends suitable job opportunities based on the skills entered by the user.

## Features

- User registration and login
- Skill-based job recommendations
- Job and skill matching
- MySQL database for storing user and job information
- Simple and user-friendly web interface

## Technologies Used

- Java
- Java Servlets
- JDBC
- MySQL
- HTML
- CSS
- JavaScript

## How It Works

1. User registers and logs into the system.
2. User provides their skills.
3. The system compares the user's skills with the skills required for available jobs.
4. Matching jobs are retrieved from the MySQL database.
5. Recommended jobs are displayed to the user.

## Database

The project uses MySQL with tables such as:

- `jobs`
- `skills`
- `job_skills`
- User-related tables

## Project Structure

```text
src/
 └── main/
     ├── java/
     │   └── com/aijob/
     │       ├── RegisterServlet.java
     │       ├── LoginServlet.java
     │       ├── JobMatcher.java
     │       └── DBConnection.java
     └── webapp/
         ├── index.html
         ├── register.html
         ├── dashboard.html
         └── upload.html
