package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.university.model.*;
import com.example.university.repository.*;

@Service
public class CourseJpaService implements CourseRepository {

    @Autowired
    private CourseJpaRepository CJR;

    @Autowired
    private StudentJpaRepository SJR;

    @Autowired
    private ProfessorJpaRepository PJR;

    @Override
    public ArrayList<Course> getAllCourse() {
        List<Course> listCourse = CJR.findAll();
        ArrayList<Course> arrayCourse = new ArrayList<>(listCourse);
        return arrayCourse;
    }

    @Override
    public Course getCourseById(int courseId) {
        try {
            Course course = CJR.findById(courseId).get();
            return course;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Course getAddCourse(Course course) {
        List<Integer> courseIds = new ArrayList<>();
        for (Student student : course.getStudents()) {
            courseIds.add(student.getStudentId());
        }
        List<Student> students = SJR.findAllById(courseIds);
        if (students.size() != courseIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        course.setStudents(students);
        return CJR.save(course);
    }

    @Override
    public Course UpdateCourse(int courseId, Course course) {
        try {
            Course newCourse = CJR.findById(courseId).get();
            if (course.getCourseName() != null) {
                newCourse.setCourseName(course.getCourseName());
            }
            if (course.getCredits() != null) { // updated
                newCourse.setCredits(course.getCredits());
            }
            if (course.getProfessor() != null) {
                Professor prof = course.getProfessor();
                int pId = prof.getProfessorId();
                Professor complete = PJR.findById(pId).get();
                newCourse.setProfessor(complete);
            }
            if (course.getStudents() != null) {
                List<Integer> coursesIds = new ArrayList<>();
                for (Student student : course.getStudents()) {
                    coursesIds.add(student.getStudentId());
                }
                List<Student> students = SJR.findAllById(coursesIds);
                if (students.size() != coursesIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newCourse.setStudents(students);
            }
            return CJR.save(newCourse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            CJR.deleteById(courseId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Professor getProfessorCourse(int courseId) {
        try {
            Professor professor = CJR.findById(courseId).get();
            return PJR.findByArtist(professor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> getStudentProfessor(int courseId) {
        try {
            Course course = CJR.findById(courseId).get();
            return course.getStudents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}