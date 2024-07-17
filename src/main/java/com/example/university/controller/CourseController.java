
package com.example.university.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.university.model.*;
import com.example.university.service.*;

@RestController
public class CourseController {

    @Autowired
    private CourseJpaService CJS;

    @GetMapping("/courses")
    public ArrayList<Course> getAllCourse() {
        return CJS.getAllCourse();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable("courseId") int courseId) {
        return CJS.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public Course getAddCourse(@RequestBody Course course) {
        return CJS.getAddCourse(course);
    }

    @PutMapping("/courses/{courseId}")
    public Course UpdateCourse(@PathVariable("courseId") int courseId, @RequestBody Course course) {
        return CJS.UpdateCourse(courseId, course);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int courseId) {
        CJS.deleteCourse(courseId);
    }

    @GetMapping("/courses/{courseId}/professor")
    public Professor getProfessorCourse(@PathVariable("courseId") int courseId) {
        return CJS.getProfessorCourse(courseId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> getStudentProfessor(@PathVariable("courseId") int courseId) {
        return CJS.getStudentProfessor(courseId);
    }
}