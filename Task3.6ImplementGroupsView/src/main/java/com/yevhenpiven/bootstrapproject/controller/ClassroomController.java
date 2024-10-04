package com.yevhenpiven.bootstrapproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String listClassrooms(Model model) {
        model.addAttribute("classrooms", classroomService.findAll());
        return "classrooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditClassroomForm(@PathVariable("id") int id, Model model) {
        Classroom classroom = classroomService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classroom Id:" + id));
        model.addAttribute("classroom", classroom);
        return "edit_classroom";
    }

    @PostMapping("/update/{id}")
    public String updateClassroom(@PathVariable("id") int id, @ModelAttribute("classroom") Classroom classroom,
            Model model) {
        classroom.setClassroomId(id);
        classroomService.save(classroom);
        return "redirect:/classrooms";
    }
}
