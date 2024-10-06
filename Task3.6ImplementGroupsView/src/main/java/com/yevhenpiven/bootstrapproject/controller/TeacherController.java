package com.yevhenpiven.bootstrapproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String listTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/create")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher_create";
    }

    @PostMapping("/create")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String showEditTeacherForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        model.addAttribute("teacher", teacher);
        return "edit_teacher";
    }

    @PostMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {
        teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
