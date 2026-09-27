package com.student.dao;

import com.student.model.Student;
import com.student.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for handling Student CRUD operations using raw JDBC.
 */
public class StudentDAO {

    private static final String INSERT_STUDENT_SQL =
            "INSERT INTO students (name, email, course, age) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL_STUDENTS_SQL =
            "SELECT id, name, email, course, age FROM students";

    /**
     * Inserts a new student record into the database.
     *
     * @param student the Student object containing student details
     * @return true if the row was successfully inserted, false otherwise
     */
    public boolean addStudent(Student student) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_STUDENT_SQL)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getAge());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all student records from the database.
     *
     * @return a List of Student objects
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_STUDENTS_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                int age = rs.getInt("age");

                students.add(new Student(id, name, email, course, age));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }
}
