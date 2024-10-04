package com.yevhenpiven.bootstrapproject.controller;

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
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers";
    }

    @GetMapping("/edit/{id}")
    public String showEditTeacherForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        model.addAttribute("teacher", teacher);
        return "teacher_edit"; 
    }

    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable("id") int id, @ModelAttribute("teacher") Teacher teacher) {
        teacher.setTeacherId(id); 
        teacherService.save(teacher);
        return "redirect:/teachers";
    }
}
