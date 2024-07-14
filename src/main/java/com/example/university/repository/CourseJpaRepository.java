package com.example.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.university.model.*;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Integer> {

     List<Course> findByCourse(Professor professor);
}