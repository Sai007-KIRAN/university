package com.example.university.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.*;

@Repository
public interface StudentRepository {
    ArrayList<Student> getAllStudent();

    Student getStudentById(int StudentId);

    Student getAddStudent(Student student);

    Student getUpdateStudent(int StudentId, Student student);

    Student getDeleteStudent(int StudentId);

    List<Course> getCourseStudent(int StudentId);
}