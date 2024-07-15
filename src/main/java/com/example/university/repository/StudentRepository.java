package com.example.university.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.*;

@Repository
public interface StudentRepository {
    ArrayList<Student> getAllStudent();

    Student getStudentById(int studentId);

    Student getAddStudent(Student student);

    Student getUpdateStudent(int studentId, Student student);

    void getDeleteStudent(int studentId);

    List<Course> getCourseStudent(int studentId);
}