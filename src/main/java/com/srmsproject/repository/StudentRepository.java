package com.srmsproject.repository;

import com.srmsproject.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srmsproject.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findAll(Pageable pageable);
    Student getStudentById(long id);
    List<Student> findByNameContaining(String name);

    void save(Group group);
}
