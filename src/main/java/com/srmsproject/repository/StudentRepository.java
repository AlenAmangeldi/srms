package com.srmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srmsproject.model.Student;
import org.springframework.ui.Model;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
