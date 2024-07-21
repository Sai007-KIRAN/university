// package com.example.university.controller;

// import org.springframework.web.bind.annotation.*;
// import java.util.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import com.example.university.model.*;
// import com.example.university.service.*;

// @RestController
// public class StudentController {

//     @Autowired
//     private StudentJpaService SJS;

//     @GetMapping("/students")
//     public ArrayList<Student> getAllStudent() {
//         return SJS.getAllStudent();
//     }

//     @GetMapping("/students/{studentId}")
//     public Student getStudentById(@PathVariable("studentId") int studentId) {
//         return SJS.getStudentById(studentId);
//     }

//     @PostMapping("/students")
//     public Student getAddStudent(@RequestBody Student student) {
//         return SJS.getAddStudent(student);
//     }

//     @PutMapping("/students/{studentId}")
//     public Student getUpdateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
//         return SJS.getUpdateStudent(studentId, student);
//     }

//     @DeleteMapping("/students/{studentId}")
//     public void getDeleteStudent(@PathVariable("studentId") int studentId) {
//         SJS.getDeleteStudent(studentId);
//     }

//     @GetMapping("/students/{studentId}/courses")
//     public List<Course> getCourseStudent(@PathVariable("studentId") int studentId) {
//         return SJS.getCourseStudent(studentId);
//     }
// }

package com.example.university.controller;


import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.service.StudentJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class StudentController {


    @Autowired
    private StudentJpaService studentJpaService;


    @GetMapping("/students")
   public List<Student> getStudents(){
       return studentJpaService.getStudents();
   }


   @GetMapping("/students/{id}")
   public Student getStudentById(@PathVariable Integer id){
           return studentJpaService.getStudentById(id);
   }


   @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentJpaService.addStudent(student);
    }


    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
        return studentJpaService.updateStudent(id, student);
    }


    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        studentJpaService.deleteStudent(id);
    }


    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentCourses(@PathVariable("studentId") int studentId){
        return studentJpaService.getStudentCourses(studentId);
    }
}