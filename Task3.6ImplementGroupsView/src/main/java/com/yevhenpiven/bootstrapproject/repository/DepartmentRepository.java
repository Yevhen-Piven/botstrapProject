package com.yevhenpiven.bootstrapproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yevhenpiven.bootstrapproject.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
