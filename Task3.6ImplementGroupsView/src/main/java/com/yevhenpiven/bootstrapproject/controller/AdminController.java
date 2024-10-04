package com.yevhenpiven.bootstrapproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yevhenpiven.bootstrapproject.entity.User;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute User user, Model model) {
        model.addAttribute("message", "User added successfully");

        return "admin";
    }

    @PostMapping("/admin/assignRole")
    public String assignRole(@RequestParam String username, @RequestParam String roleName, Model model) {
        model.addAttribute("message", "Role assigned successfully");

        return "admin";
    }
}
