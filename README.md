
## 📦 Backend: `iitb-course-manager-backend`

```markdown
# IITB Course Manager – Backend (Spring Boot)

This is the backend API service for the IIT Bombay Internship Assignment (2024-25). Built using **Java Spring Boot**, it provides a RESTful interface for managing courses and their delivery instances.

## 🚀 Features

- Create, view, and delete courses
- Handle course prerequisites with dependency validation
- Manage course delivery instances (by year & semester)
- REST API with proper error handling and HTTP status codes
- Dockerized for easy deployment

## 📁 Project Structure

```

src/main/java/com/iitbombay/courses/
├── entity/         # Course and CourseInstance entities
├── repository/     # Spring Data JPA Repositories
├── service/        # Business logic
├── controller/     # REST API endpoints
└── dto/            # Request/Response data transfer objects

````

## 🔌 API Endpoints

### Courses
- `POST /api/courses` – Create course (with prerequisites)
- `GET /api/courses` – List all courses
- `GET /api/courses/{id}` – Get specific course
- `DELETE /api/courses/{id}` – Delete course (with prerequisite check)

### Instances
- `POST /api/instances` – Create course instance
- `GET /api/instances/{year}/{sem}` – List instances by year & semester
- `GET /api/instances/{year}/{sem}/{id}` – Get instance detail
- `DELETE /api/instances/{year}/{sem}/{id}` – Delete instance

## 🐳 Docker

### Build and Run Locally

```bash
docker build -t course-backend .
docker run -p 8080:8080 course-backend
````

### Or use `docker-compose` (see root `docker-compose.yml`)

## 🛠 Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA + H2 / PostgreSQL
* Docker

## 📦 DockerHub Image

> `docker pull your-dockerhub-username/iitb-course-backend`

---

````

---

## 💻 Frontend: `iitb-course-manager-frontend`

```markdown
# IITB Course Manager – Frontend (React)

This is the ReactJS frontend for the IIT Bombay Internship Assignment (2024-25). It provides a clean UI to manage courses and their instances by interacting with the backend REST API.

## 🎯 Features

- Create new courses with prerequisite selection
- List all courses with their dependencies
- Create/view/delete course delivery instances
- Visual UI feedback for blocked deletes (due to dependencies)
- Fully containerized using Docker

## 🖼 UI Features

- Multi-select dropdown for prerequisites
- List view with prerequisite display
- Visual indicators (disabled buttons/warnings) when delete is restricted

## 🗂 Project Structure

````

src/
├── components/   # React UI components (CourseForm, CourseList, etc.)
├── services/     # Axios-based API calls
└── App.js        # Root application

````

## 🔌 API Base URL

> Make sure to update API base URL if running separately.

```js
// Example:
axios.get('http://localhost:8080/api/courses');
````

## 🐳 Docker

### Build and Run

```bash
docker build -t course-frontend .
docker run -p 3000:3000 course-frontend
```

