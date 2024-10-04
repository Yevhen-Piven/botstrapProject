package com.yevhenpiven.bootstrapproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yevhenpiven.bootstrapproject.entity.Classroom;
import com.yevhenpiven.bootstrapproject.repository.ClassroomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Integer id) {
        return classroomRepository.findById(id);
    }

    @Transactional
    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Transactional
    public void deleteById(Integer id) {
        classroomRepository.deleteById(id);
    }
}
