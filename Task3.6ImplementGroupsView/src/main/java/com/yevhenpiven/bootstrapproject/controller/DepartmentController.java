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

import com.yevhenpiven.bootstrapproject.entity.Department;
import com.yevhenpiven.bootstrapproject.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/create")
    public String showCreateDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department_create";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id: " + id));
        model.addAttribute("department", department);
        return "edit_department";
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }
}
