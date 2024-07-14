package com.example.university.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.university.model.*;

@Repository
public interface CourseRepository {
    ArrayList<Course> getAllCourse();

    Course getCourseById(int courseId);

    Course getAddCourse(Course course);

    Course UpdateCourse(int courseId, Course course);

    Course deleteCourse(int courseId);

    Professor getProfessorCourse(int courseId);

    List<Student> getStudentProfessor(int courseId);
}