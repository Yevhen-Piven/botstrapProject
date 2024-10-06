package com.yevhenpiven.bootstrapproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yevhenpiven.bootstrapproject.entity.Timetable;
import com.yevhenpiven.bootstrapproject.service.TimetableService;

@Controller
@RequestMapping("/timetables")
public class TimetableController {

    private final TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'STAFF', 'ADMIN')")
    public String listTimetables(Model model) {
        List<Timetable> timetables = timetableService.findAll();
        model.addAttribute("timetables", timetables);
        return "timetables";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showCreateTimetableForm(Model model) {
        model.addAttribute("timetable", new Timetable());
        return "timetable_create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String createTimetable(@ModelAttribute("timetable") Timetable timetable) {
        timetableService.save(timetable);
        return "redirect:/timetables";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showEditTimetableForm(@PathVariable("id") int id, Model model) {
        Timetable timetable = timetableService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid timetable Id: " + id));
        model.addAttribute("timetable", timetable);
        return "edit_timetable";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String deleteTimetable(@PathVariable("id") int id) {
        timetableService.deleteById(id);
        return "redirect:/timetables";
    }
}
