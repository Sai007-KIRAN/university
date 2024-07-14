package com.example.university.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.*;

@Repository
public interface ProfessorRepository {

    ArrayList<Professor> getAllProfessor();

    Professor getProfessorById(int professorId);

    Professor getAddProfessor(Professor professor);

    Professor updateProfessor(int professorId, Professor professor);

    void deleteProfessor(int professorId);

    List<Course> getCourseProfessor(int professorId);

}