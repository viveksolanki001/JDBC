<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.student.model.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Students - Student Management System</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Student List</h1>
            <p class="subtitle">All enrolled students retrieved via JDBC</p>
        </header>

        <div class="top-bar">
            <a href="index.html" class="btn btn-outline">&larr; Back to Registration</a>
        </div>

        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
        %>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Course</th>
                        <th>Age</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Student s : students) {
                    %>
                    <tr>
                        <td><%= s.getId() %></td>
                        <td><%= s.getName() %></td>
                        <td><%= s.getEmail() %></td>
                        <td><%= s.getCourse() %></td>
                        <td><%= s.getAge() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            } else {
        %>
        <div class="empty-msg">
            <p>No students found in the database. <a href="index.html">Click here to register a student</a>.</p>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
