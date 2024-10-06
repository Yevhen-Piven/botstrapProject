package com.yevhenpiven.bootstrapproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yevhenpiven.bootstrapproject.entity.Course;
import com.yevhenpiven.bootstrapproject.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
    
    private final CourseRepository courseRepository;

    
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }
}
