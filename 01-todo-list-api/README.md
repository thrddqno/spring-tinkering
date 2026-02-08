# 📋 TodoList API

In this project, I am required to develop a RESTful API to allow users to manage their to-do list.

A project from roadmap.sh.
https://roadmap.sh/projects/todo-list-api

## ⚙️ Tech Stack

- **Runtime:** Java 21 / Spring Boot 3.4+
- **Security:** Spring Security & JWT (Stateless)
- **Persistence:** Spring Data JPA / PostgreSQL
- **Mapping:** MapStruct & Lombok

---

## ⚙️ API Specification

### 🔐 Authentication

Public endpoints for user onboarding and session management.

| Method | Endpoint         | Request Body                      | Response                |
| :----- | :--------------- | :-------------------------------- | :---------------------- |
| `POST` | `/auth/register` | `{ "name", "email", "password" }` | `{ "token": "string" }` |
| `POST` | `/auth/login`    | `{ "email", "password" }`         | `{ "token": "string" }` |

---

### 📝 Task Controller

Required Header: `Authorization: Bearer <JWT_TOKEN>`

#### Get All Tasks

`GET /api/todos?page={n}&size={m}`

**Response Body:**

```json
{
  "data": [
    {
      "id": 1,
      "title": "Master Cloud/DevOps",
      "description": "Learn Kubernetes and CI/CD"
    }
  ],
  "page": 1,
  "limit": 5,
  "total": 1
}
```

#### Manage Tasks

| Method | Endpoint          | Request Body                 | Description          |
| ------ | ----------------- | ---------------------------- | -------------------- |
| POST   | `/api/todos`      | `{ "title", "description" }` | Create a new task    |
| PUT    | `/api/todos/{id}` | `{ "title", "description" }` | Update existing task |
| DELETE | `/api/todos/{id}` |                              | Remove a task        |

### 📋 Environment Variables

Ensure these keys are present in your `application.properties` or `system environment`:

| Key            | Description                 |
| -------------- | --------------------------- |
| JWT_SECRET_KEY | 256-bit key for JWT signing |
