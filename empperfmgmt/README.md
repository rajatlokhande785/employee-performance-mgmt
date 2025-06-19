#  Employee Performance Management System (Spring Boot REST API)

This is a Spring Boot-based REST API to manage employee performance, departments, projects, and reviews. The system enables filtered employee queries and detailed profile views based on performance, department, and project data.

---

## Features

- Filter employees by:
    - Performance review score (on a given review date)
    - Department(s)
    - Project(s)
- Get detailed employee profile:
    - Department name
    - Assigned projects
    - Last 3 performance reviews
-  Global exception handling
-  Structured DTOs and layered architecture
- SLF4J logging in service layer
- Uses H2 in-memory DB with `data.sql` for bootstrapping

---

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Data JPA (Hibernate)
- H2 Database (in-memory)
- Lombok
- ModelMapper
- SLF4J (logging)
- Maven (build tool)

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/employee-performance-mgmt.git
cd employee-performance-mgmt
```
### 1. Clone the Repository

```bash
./mvnw spring-boot:run
```
### 3. Access H2 Console
URL: http://localhost:8080/h2  
JDBC URL: jdbc:h2:mem:testdb  
Username: sa  
Password: (empty)

---
## 4. API Endpoints
### Filter Employees
```bash
GET /api/employees
```
Optional Query Parameters:
```plaintext 
score — e.g., 4  
reviewDate — yyyy-MM-dd (e.g., 2024-01-01)
departments — list of department names (e.g., HR,Engineering)
projects — list of project names (e.g., Project Alpha)
```
Example:
```bash
curl "http://localhost:8080/api/employees?score=4&reviewDate=2024-01-01&departments=Engineering&projects=Project Alpha"
```

### Get Employee Details by ID
```bash
GET /api/employees/{id}
```
Sample Response:
```json
"id": 2,
"name": "Bob",
"email": "bob@example.com",
"departmentName": "Engineering",
"projectNames": ["Project Alpha", "Project Beta"],
"recentReviews": [
{
"reviewDate": "2024-06-01",
"score": 3,
"reviewComments": "Needs improvement"
},
...
]
}
```
### Sample Data
Preloaded via src/main/resources/data.sql:  
```sql
Departments: Engineering, HR  
Projects: Project Alpha, Project Beta
Employees: Alice, Bob, Eve
Reviews: 3 reviews for Bob
Mappings: Bob assigned to both projects
```
## Error Handling
Custom exceptions like ResourceNotFoundException  
Global exception handler returns structured responses