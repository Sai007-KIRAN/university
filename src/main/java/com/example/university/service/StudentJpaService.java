package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.university.model.*;
import com.example.university.repository.*;

@Service
public class StudentJpaService implements StudentRepository {

    @Autowired
    private StudentJpaRepository SJR;

    @Autowired
    private CourseJpaRepository CJR;

    @Override
    public ArrayList<Student> getAllStudent() {
        List<Student> studentList = SJR.findAll();
        ArrayList<Student> arrayStudent = new ArrayList<>(studentList);
        return arrayStudent;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = SJR.findById(studentId).get();
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student getAddStudent(Student student) {
        List<Integer> courseId = new ArrayList<>();
        for (Course course : student.getCourses()) {
            courseId.add(course.getCourseId());
        }
        List<Course> courses = CJR.findAllById(courseId);
        student.setCourses(courses);

        for (Course course : courses) {
            course.getStudents().add(student);
        }
        Student stud = SJR.save(student);
        CJR.saveAll(courses);
        return stud;
    }

    @Override
    public Student getUpdateStudent(int studentId, Student student) {
        try {
            Student newStudent = SJR.findById(studentId).get();
            if (student.getStudentName() != null) {
                newStudent.setStudentName(student.getStudentName());
            }
            if (student.getEmail() != null) {
                newStudent.setEmail(student.getEmail());
            }
            if (student.getCourses() != null) {
                List<Course> courses = newStudent.getCourses();
                for (Course course : courses) {
                    course.getStudents().remove(newStudent);
                }
                CJR.saveAll(courses);
                List<Integer> pId = new ArrayList<>();
                for (Course course : student.getCourses()) {
                    pId.add(course.getCourseId());
                }
                List<Course> newCourse = CJR.findAllById(pId);
                for (Course course : newCourse) {
                    course.getStudents().add(newStudent);
                }
                CJR.saveAll(newCourse);
                newStudent.setCourses(newCourse);
            }
            return SJR.save(newStudent);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void getDeleteStudent(int studentId) {
        try {
            Student student = SJR.findById(studentId).get();
            List<Course> course = student.getCourses();
            for (Course courses : course) {
                courses.getStudents().remove(student);
            }
            CJR.saveAll(course);
            SJR.deleteById(studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Course> getCourseStudent(int studentId) {
        try {
            Student student = SJR.findById(studentId).get();
            return student.getCourses();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}