# 💸 Expense Tracker API

In this project, I am required to build an API for an expense tracker application. This API should allow users to create, read, update, and delete expenses. Users should be able to sign up and log in to the application. Each user should have their own set of expenses.

## ⚙️ Tech Stack
- **Runtime/SDK:** Java 21 | Spring Boot 4.0.2
- **Security:** Spring Security with JWT (Stateless)
- **Persistence:** PostgreSQL | Spring Data JPA
- **Mapping and Tools:** Lombok and MapStruct

## ⚙️ API Specification

### 🔒 Authentication

Public endpoints for user onboarding and session management.

| Method | Endpoint         | Request Body                      | Response                |
| :----- | :--------------- | :-------------------------------- | :---------------------- |
| `POST` | `/auth/register` | `{ "name", "email", "password" }` | `{ "token": "string" }` |
| `POST` | `/auth/login`    | `{ "email", "password" }`         | `{ "token": "string" }` |

---

### 💸 expense-controller
Required Header: `Authorization: Bearer <JWT_TOKEN>`

#### Get Expenses

`GET /api/expenses?page={n}&size={m}`

**Response Body:**

```json
{
    "data": [
        {
            "id": 1,
            "name": "McDonalds",
            "amount": 100.0,
            "expenseDate": "2026-02-22",
            "categoryId": 1
        }
      ...
    ],
    "page": 1,
    "limit": 10,
    "total": 1
}
```
___
#### Get Expenses by Filtering

`GET /api/expenses?page={n}&size={m}&filter={filter}&start={yyyy-MM-dd}&end={yyyy-MM-dd}`

| Param    | Type | Description |
|----------|------|-------------|
| `page`   | int  | Page number |
| `size`   | int | Items per page |
| `filter` | string | `week`, `month`, `3months`, `custom` |
 | `start`  | date | Required if `filter=custom`, `YYYY-MM-DD` |
| `end`    | date | Required if `filter=custom`, `YYYY-MM-DD` |

**Response Body:**
```json
{
  "data": [
    {
      "id": 1,
      "name": "McDonalds",
      "amount": 100.0,
      "expenseDate": "2026-02-22",
      "categoryId": 1
    }
  ],
  "page": 1,
  "limit": 10,
  "total": 6
}
```
    
___
#### Create Expense

`POST /api/expenses`

**Request Body:**
```json
{
  "name": "McDonalds",
  "amount": 100,
  "expenseDate": "2026-02-22",
  "categoryId": 1
}
```

**Response Body:**

```json
{
  "id": 1,
  "name": "McDonalds",
  "amount": 100.0,
  "expenseDate": "2026-02-22",
  "categoryId": 1
}
```
___
#### Update Expense

`PUT /api/expenses/{expenseId}`

**Request Body:**
```json
{
  "name": "McDonalds",
  "amount": 100,
  "expenseDate": "2026-02-22",
  "categoryId": 1
}
```

**Response Body:**

```json
{
  "id": 1,
  "name": "McDonalds",
  "amount": 100.0,
  "expenseDate": "2026-02-22",
  "categoryId": 1
}
```
___
#### Delete Expense

`DELETE /api/expenses/{expenseId}`
