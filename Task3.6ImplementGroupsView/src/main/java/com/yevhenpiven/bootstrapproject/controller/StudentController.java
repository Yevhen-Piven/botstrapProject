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

import com.yevhenpiven.bootstrapproject.entity.Student;
import com.yevhenpiven.bootstrapproject.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_create";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id: " + id));
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
