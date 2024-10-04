package com.yevhenpiven.bootstrapproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yevhenpiven.bootstrapproject.entity.Timetable;
import com.yevhenpiven.bootstrapproject.repository.TimetableRepository;

import dto.TimetableDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    public Optional<Timetable> findById(Integer id) {
        return timetableRepository.findById(id);
    }

    @Transactional
    public Timetable save(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Transactional
    public void deleteById(Integer id) {
        timetableRepository.deleteById(id);
    }

    public List<TimetableDTO> getTimetableByDate(LocalDate date) {
        return timetableRepository.findTimetableByDate(date);
    }
}