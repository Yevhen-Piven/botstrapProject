package com.yevhenpiven.bootstrapproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yevhenpiven.bootstrapproject.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
