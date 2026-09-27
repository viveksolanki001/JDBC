package com.student.servlet;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet controller to handle HTTP GET and POST requests for Student operations.
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.studentDAO = new StudentDAO();
    }

    /**
     * Handles HTTP GET requests: retrieves all students and forwards to students.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Student> students = studentDAO.getAllStudents();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving students from database.");
        }
    }

    /**
     * Handles HTTP POST requests: validates form parameters and adds a new student.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String ageStr = request.getParameter("age");

        // Validate that none are empty
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            course == null || course.trim().isEmpty() ||
            ageStr == null || ageStr.trim().isEmpty()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields (name, email, course, age) are required.");
            return;
        }

        // Convert age to int
        int age;
        try {
            age = Integer.parseInt(ageStr.trim());
            if (age <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Age must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid age format. Age must be a valid integer.");
            return;
        }

        // Create Student object and persist using StudentDAO
        Student student = new Student(name.trim(), email.trim(), course.trim(), age);
        boolean success = studentDAO.addStudent(student);

        if (success) {
            // Redirect to /students
            response.sendRedirect(request.getContextPath() + "/students");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to insert student into the database.");
        }
    }
}
