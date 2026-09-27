# Student Management System (JDBC & Servlets)

A lightweight Java Web Application demonstrating database connectivity and CRUD operations using **JDBC**, **Java Servlets (Jakarta EE 10 / Servlet 6.0)**, **JSP**, and **MySQL**.

---

## 🚀 Features

- **Student Registration**: Clean HTML5 form to register students with Name, Email, Course, and Age.
- **Student Directory**: Dynamic JSP-based table displaying all student records fetched directly from MySQL.
- **Pure JDBC & DAO Pattern**: Clean architectural separation with `StudentDAO`, `DBConnection`, and model entity `Student`.
- **Modern UI**: Polished, responsive design with CSS variables, modern card layouts, and subtle shadows.
- **Secure Configuration**: Reads database password dynamically from the `MYSQL_PASSWORD` environment variable.

---

## 🛠️ Tech Stack

- **Java**: 17+
- **Servlet API**: Jakarta Servlet 6.0 (Tomcat 10.1+ compatible)
- **Database**: MySQL 8.0+
- **JDBC Driver**: `mysql-connector-j` 8.4.0
- **Build Tool**: Apache Maven
- **Frontend**: HTML5, CSS3, JSP

---

## 📁 Project Structure

```text
Student DB/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    └── main/
        ├── java/
        │   └── com/student/
        │       ├── dao/
        │       │   └── StudentDAO.java       # Handles SQL INSERT and SELECT queries
        │       ├── model/
        │       │   └── Student.java          # Student entity POJO
        │       ├── servlet/
        │       │   └── StudentServlet.java   # HTTP GET/POST controller
        │       └── util/
        │           └── DBConnection.java     # Singleton/utility for JDBC connections
        ├── resources/
        │   └── .gitkeep
        └── webapp/
            ├── WEB-INF/
            │   └── web.xml                  # Servlet mapping configuration
            ├── index.html                   # Student registration form
            ├── students.jsp                 # Student records list view
            └── style.css                    # Modern responsive styling
```

---

## 🗄️ Database Setup

Create the MySQL database and table before running the application:

```sql
CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    age INT NOT NULL
);
```

---

## ⚙️ Configuration

Set the `MYSQL_PASSWORD` environment variable with your MySQL root password:

### Windows (PowerShell)
```powershell
$env:MYSQL_PASSWORD="your_mysql_password"
```

### Windows (Command Prompt)
```cmd
set MYSQL_PASSWORD=your_mysql_password
```

### Linux / macOS
```bash
export MYSQL_PASSWORD="your_mysql_password"
```

*(Optional)* If your MySQL username or port differs from `root` and `3306`, update the constants in [DBConnection.java](src/main/java/com/student/util/DBConnection.java).

---

## 📦 Build & Deployment

1. **Package the WAR file:**
   ```bash
   mvn clean package
   ```
   This generates `StudentJDBCWebApp.war` inside the `target/` directory.

2. **Deploy to Tomcat:**
   - Copy `target/StudentJDBCWebApp.war` into your Apache Tomcat `webapps/` folder.
   - Start Tomcat:
     - Windows: `bin\startup.bat`
     - Linux/macOS: `bin/startup.sh`

3. **Access the Application:**
   - Registration Page: `http://localhost:8080/StudentJDBCWebApp/`
   - Student Records List: `http://localhost:8080/StudentJDBCWebApp/students`

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
