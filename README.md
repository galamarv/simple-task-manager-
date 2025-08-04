# Simple Task Manager

A full-stack web application for managing tasks, built with a React frontend and a Spring Boot backend using Clean Architecture.

## 📌 Overview

This project is a simple To-Do application that allows users to create, view, update, and delete tasks. Each task has a title, description, and status (TODO, IN_PROGRESS, DONE).

### Features
- **Create Tasks**: Add new tasks with a title and description.
- **View Tasks**: See a list of all tasks.
- **Update Status**: Change a task's status between TODO, IN_PROGRESS, and DONE.
- **Filter Tasks**: Filter the task list to show items of a specific status.
- **Delete Tasks**: Remove tasks from the list.

---

## ⚙️ System Design

The application is architected with a clear separation between the frontend and backend.

### Backend (Spring Boot)
The backend is built using **Clean Architecture** to ensure a separation of concerns, making the application scalable and maintainable.

- **Domain Layer**: Contains the core business logic and entities (e.g., the `Task` model). It has no dependencies on any other layer.
- **Application Layer**: Contains the application-specific use cases (e.g., `TaskService`) and defines the ports for external communication (e.g., `TaskRepositoryPort`). It depends only on the Domain layer.
- **Infrastructure Layer**: Implements the external details like the REST controller, database repository (JPA), and other framework-specific configurations. It depends on the Application layer.

### Frontend (React)
The frontend is a single-page application (SPA) built with **React** and **Vite**.
- **Vite**: Provides a fast and modern development environment.
- **TypeScript**: Ensures type safety and improves code quality.
- **Bootstrap 5**: Used for responsive styling and UI components.
- **Axios**: A promise-based HTTP client for communicating with the backend REST API.

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 17
- **Apache Maven**: Version 3.8 or higher
- **Node.js**: Version 20.x or higher (which includes npm 10.x)
- **Microsoft SQL Server**: A local instance (e.g., Developer Edition 2022)
- **Make**: A build automation tool (comes standard on Linux/macOS, can be installed on Windows).

---

## 🚀 How to Run the Program

This project includes a `Makefile` to automate the setup and execution process.

### Step 1: Database Setup

1.  **Create the Database**: Open SQL Server Management Studio (SSMS) and create a new, empty database (e.g., `taskmanagerdb`).
2.  **Create a User**: Create a new SQL Server login (e.g., `taskmanageruser`) with a password.
3.  **Grant Permissions**: In the "User Mapping" section for the new login, map it to the `taskmanagerdb` database and grant it the `db_owner` role.
4.  **Configure Backend**: Open the `backend/src/main/resources/application.properties` file and update the following lines with your database details:
    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=taskmanagerdb;encrypt=true;trustServerCertificate=true;
    spring.datasource.username=taskmanageruser
    spring.datasource.password=YOUR_PASSWORD
    ```
    *Note: The application is configured to automatically create the database schema and seed it with sample data on startup.*

### Step 2: Install Dependencies

Open a terminal in the project's root directory and run the `install` command from the `Makefile`. This will install all backend (Maven) and frontend (npm) dependencies.

```bash
make install
```

### Step 3: Run the Application

To run both the backend and frontend servers concurrently, use the `run` command.

```bash
make run
```

This command will:
- Start the **backend API** on `http://localhost:8080`.
- Start the **frontend development server** on `http://localhost:5173`.

You can now open your web browser and navigate to **`http://localhost:5173`** to use the application.

---

## 📝 API Endpoints

The backend provides the following RESTful API endpoints:

| Method | Endpoint        | Description                 |
|--------|-----------------|-----------------------------|
| `POST` | `/tasks`        | Creates a new task.         |
| `GET`  | `/tasks`        | Retrieves a list of all tasks. |
| `GET`  | `/tasks/{id}`   | Retrieves a single task by its ID. |
| `PUT`  | `/tasks/{id}`   | Updates an existing task.   |
| `DELETE`| `/tasks/{id}` | Deletes a task by its ID.   |

---

## 📄 Assumptions

- **No User Authentication**: The application does not include user login or authentication, as it was not specified in the requirements. All tasks are public.
- **Single Environment**: The configuration is set up for a local development environment. No production-specific configurations are included.
