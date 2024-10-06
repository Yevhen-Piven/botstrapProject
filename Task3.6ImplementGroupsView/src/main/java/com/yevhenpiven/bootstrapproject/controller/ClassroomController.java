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

import com.yevhenpiven.bootstrapproject.entity.Classroom;
import com.yevhenpiven.bootstrapproject.service.ClassroomService;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'STAFF', 'ADMIN')")
    public String listClassrooms(Model model) {
        List<Classroom> classrooms = classroomService.findAll();
        model.addAttribute("classrooms", classrooms);
        return "classrooms";
    }

    @GetMapping("/create_classroom")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showCreateClassroomForm(Model model) {
        model.addAttribute("classroom", new Classroom());
        return "classroom_create";
    }

    @PostMapping("/create_classroom")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String createClassroom(@ModelAttribute("classroom") Classroom classroom) {
        classroomService.save(classroom);
        return "redirect:/classrooms";
    }

    @GetMapping("/edit_classroom/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showEditClassroomForm(@PathVariable("id") int id, Model model) {
        Classroom classroom = classroomService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classroom Id: " + id));
        model.addAttribute("classroom", classroom);
        return "edit_classroom";
    }

    @GetMapping("/delete_classroom/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showDeleteClassroomForm(@PathVariable("id") int id, Model model) {
        Classroom classroom = classroomService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classroom Id: " + id));
        model.addAttribute("classroom", classroom);
        return "delete_classroom";
    }

    @PostMapping("/delete_classroom/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String deleteClassroom(@PathVariable("id") int id) {
        classroomService.deleteById(id);
        return "redirect:/classrooms";
    }
}
