package com.example.university.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.university.model.*;
import com.example.university.service.*;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorJpaService PJS;

    @GetMapping("/professors")
    public ArrayList<Professor> getAllProfessor() {
        return PJS.getAllProfessor();
    }

    @GetMapping("/professors/{professorId}")
    public Professor getProfessorById(@PathVariable("professorId") int professorId) {
        return PJS.getProfessorById(professorId);
    }

    @PostMapping("/professors")
    public Professor getAddProfessor(@RequestBody Professor professor) {
        return PJS.getAddProfessor(professor);
    }

    @PutMapping("/professors/{professorId}")
    public Professor updateProfessor(@PathVariable("professorId") int professorId, @RequestBody Professor professor) {
        return PJS.updateProfessor(professorId, professor);
    }

    @DeleteMapping("/professors/{professorId}")
    public void deleteProfessor(@PathVariable("professorId") int professorId) {
        PJS.deleteProfessor(professorId);
    }

    @GetMapping("/professors/{professorId}/courses")
    public List<Course> getCourseProfessor(@PathVariable("professorId") int professorId){
        return PJS.getCourseProfessor(professorId)
    }
}