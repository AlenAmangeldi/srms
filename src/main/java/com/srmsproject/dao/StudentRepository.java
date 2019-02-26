package com.srmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srmsproject.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
