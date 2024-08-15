# Devcompany Spring Project

# Author
***Demydenko Oleksii ACS201***

## Introduction

The **DevCompany** project is a Spring Boot application designed to manage a company's developers and projects. It handles CRUD operations for developers and projects, along with a web-based frontend for user interaction. The project demonstrates the use of various Spring Boot features, including MVC, Data JPA and internationalization.

## Simple Class Diagram

![https://ibb.co/hCKmsWy](https://i.ibb.co/vPcMDbf/Screenshot-2024-08-15-224617.png)

#### Developer Entity
***Attributes:***
**firstName**, **lastName**, **age**, **salary**, **endOfContract** -  basic information.
**id** -  A unique identifier for each developer (primary key).
**position** - An enumerated type representing the developer's rank or role within the organization (e.g., Trainee, Junior, Middle, Senior, Lead).

***Relationships***:
 - Many-to-Many with Project: A developer can work on multiple projects, and a project can involve multiple developers. This relationship is managed through the developer_project join table.

#### Office Entity
***Attributes:***
**name** - The name of the office.
**location** - The address or location of the office.
**officeId** - A unique identifier for each office (primary key).

***Relationships***:
 - One-to-Many with Project: An office can host multiple projects, but each project is associated with only one office. This relationship is managed through the office_project join table.

#### Project Entity
***Attributes***:
**projectName** - The name of the project.
**projectBudget** - The budget allocated to the project.
**projectId** - A unique identifier for each project (primary key).

***Relationships***:
 - **Many-to-Many with Developer**: A project can involve multiple developers, and a developer can work on multiple projects. This relationship is managed through the developer_project join table.

- **Many-to-One with Office**: A project is associated with one office, but an office can have multiple projects. This relationship is managed through the office_project join table.


#### Summary of Relationships
***Developer-Project***: A many-to-many relationship through the developer_project join table.
***Office-Project***: A one-to-many relationship through the office_project join table.
***Project-Developer (Head of Project)***: A transient relationship indicating the lead developer for a project, which is not stored in the database.

## Features

- Manage developers and their details, including name, position, and office.
- Manage projects, including project details and budget.
- Search and sort developers by name, salary, and other criteria.
- View and manage session history.
- Custom error pages for database and general errors.
- Multi-language support (English, Italian).

## Project Structure

The project is organized into the following main packages:

- **Configuration**: Contains classes for data source configuration and interceptors.
- **Controller**: Contains controllers for handling requests related to developers, projects, and session history.
- **Domain**: Contains entity classes for Developer, Project, and other domain objects.
- **Exception**: Contains custom exception classes.
- **Repository**: Contains Spring Data JPA repositories.
- **Service**: Contains service layer classes for business logic.
- **Templates**: Contains Thymeleaf templates for the frontend.

## Profiles

- ***list*** - basic profile that activates list as storage and simple scripted seeding routine.
- ***h2*** - profile that activates h2 in memory database in combiantion with JDBC.
- ***jpa*** - profile that activates JPA repsoitory layer. Works with postgreSQL.
- ***jparep*** - profile that activates JPARepository repository layer. Works with postreSQL. 

## Prerequisites

- Java 17 or higher
- Gradle
- PostgreSQL database / H2 database
