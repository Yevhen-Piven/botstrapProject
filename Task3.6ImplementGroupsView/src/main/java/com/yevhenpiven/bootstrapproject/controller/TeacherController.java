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

import com.yevhenpiven.bootstrapproject.entity.Teacher;
import com.yevhenpiven.bootstrapproject.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'STAFF', 'ADMIN')")
    public String listTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/create-teacher")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-create";
    }

    @PostMapping("/create-teacher")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit-teacher/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showEditTeacherForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        model.addAttribute("teacher", teacher);
        return "edit-teacher";
    }

    @GetMapping("/delete-teacher/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showDeleteTeacherForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        model.addAttribute("teacher", teacher);
        return "delete-teacher";
    }

    @PostMapping("/delete-teacher/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String deleteTeacher(@PathVariable("id") int id) {
        teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
