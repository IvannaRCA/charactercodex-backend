
# Character Codex - Backend

> RESTful API for Character Codex, a full-stack application for managing tabletop RPG characters inspired by Dungeons & Dragons.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven)

---

## 📖 Description

Character Codex Backend is the RESTful API that powers the Character Codex application. It manages authentication, characters, inventories, equipment, spells and game catalog data, exposing secure endpoints consumed by the frontend application.

---

## ✨ Features

- User registration and profile management
- Character CRUD operations
- Inventory management
- Equip and unequip weapons and armors
- Gold management
- Spell management
- Catalog endpoints
- DTO-based architecture
- Spring Security authentication

---

## 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Security | 6.x |
| Spring Data JPA | 3.x |
| Hibernate | 6.x |
| PostgreSQL | 17.x |
| Maven | 3.x |

### Development & Testing

- Postman
- pgAdmin 4
- Git
- GitHub
- Visual Studio Code

---

## 📁 Project Structure

```text
docs/
src/
├── main/
│   ├── java/
│   │   └── com/ivanna/charactercodex/
│   │       ├── config/
│   │       ├── constant/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── mapper/
│   │       ├── repository/
│   │       ├── security/
│   │       ├── service/
│   │       ├── util/
│   │       └── CharacterCodexApplication.java
│   └── resources/
├── test/
pom.xml
README.md
```

---

## 🚀 How to Run

### Prerequisites

- Java 21
- Maven 3+
- PostgreSQL
- pgAdmin 4 (optional)

Clone the repository:

```bash
git clone https://github.com/IvannaRCA/charactercodex-backend.git
cd charactercodex-backend
```

Configure `src/main/resources/application.properties` with your PostgreSQL credentials.

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run
```

API base URL:

```text
http://localhost:8080/api/v1
```

---

## 🌐 REST API

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/auth/register` |
| GET | `/auth/me` |
| PUT | `/auth/me` |

### Characters

| Method | Endpoint |
|---------|----------|
| GET | `/characters` |
| GET | `/characters/{id}` |
| POST | `/characters` |
| PUT | `/characters/{id}` |
| DELETE | `/characters/{id}` |

### Character Spells

| Method | Endpoint |
|---------|----------|
| POST | `/characters/{id}/spells` |
| DELETE | `/characters/{id}/spells/{spellId}` |

### Inventory

| Method | Endpoint |
|---------|----------|
| PUT | `/characters/{characterId}/inventory/weapon` |
| DELETE | `/characters/{characterId}/inventory/weapon` |
| PUT | `/characters/{characterId}/inventory/armor` |
| DELETE | `/characters/{characterId}/inventory/armor` |
| POST | `/characters/{characterId}/inventory/objects` |
| PUT | `/characters/{characterId}/inventory/objects/{objectId}` |
| DELETE | `/characters/{characterId}/inventory/objects/{objectId}` |
| PUT | `/characters/{characterId}/inventory/gold` |

### Catalog

| Method | Endpoint |
|---------|----------|
| GET | `/races` |
| GET | `/classes` |
| GET | `/weapons` |
| GET | `/armors` |
| GET | `/objects` |
| GET | `/spells` |

---

## 🗄 Database

The application uses PostgreSQL as its relational database. Database administration during development was performed with pgAdmin 4.

### Entity-Relationship Diagram

Place the diagram in:

`docs/database_schema.png`

![Database Schema](docs/database_schema.png)

---

## 💻 Frontend

Character Codex [Frontend repository](https://github.com/IvannaRCA/charactercodex-frontend)

---

## 📄 License

MIT License

---

## 👩‍💻 Author

**Ivanna Caraccio**

GitHub [@IvannaRCA](https://github.com/IvannaRCA)