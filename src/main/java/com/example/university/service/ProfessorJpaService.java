package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.university.model.*;
import com.example.university.repository.*;

@Service
public class ProfessorJpaService implements ProfessorRepository {

    @Autowired
    private ProfessorJpaRepository PJR;

    @Autowired
    private CourseJpaRepository CJR;

    @Override
    public ArrayList<Professor> getAllProfessor() {
        List<Professor> listProfessor = PJR.findAll();
        ArrayList<Professor> arrayStudent = new ArrayList<>(listProfessor);
        return arrayStudent;
    }

    @Override
    public Professor getProfessorById(int professorId) {
        try {
            Professor idProfessor = PJR.findById(professorId).get();
            return idProfessor;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Professor getAddProfessor(Professor professor) {
        PJR.save(professor);
        return professor;
    }

    @Override
    public Professor updateProfessor(int professorId, Professor professor) {
        try {
            Professor newProf = PJR.findById(professorId).get();
            if (professor.getProfessorName() != null) {
                newProf.setProfessorName(professor.getProfessorName());
            }

            if (professor.getDepartment() != null) {
                newProf.setDepartment(professor.getDepartment());
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteProfessor(int professorId) {
        try {
            PJR.deleteById(professorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Course> getCourseProfessor(int professorId) {
        try {
            Professor view = PJR.findById(professorId).get();
            return CJR.findByCourse(view);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}