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

import com.yevhenpiven.bootstrapproject.entity.Group;
import com.yevhenpiven.bootstrapproject.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'STAFF', 'ADMIN')")
    public String listGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/create_group")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "group_create";
    }

    @PostMapping("/create_group")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String createGroup(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/edit_group/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showEditGroupForm(@PathVariable("id") int id, Model model) {
        Group group = groupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id: " + id));
        model.addAttribute("group", group);
        return "edit_group";
    }

    @GetMapping("/delete_group/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String showDeleteGroupForm(@PathVariable("id") int id, Model model) {
        Group group = groupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id: " + id));
        model.addAttribute("group", group);
        return "delete_group";
    }

    @PostMapping("/delete_group/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String deleteGroup(@PathVariable("id") int id) {
        groupService.deleteById(id);
        return "redirect:/groups";
    }
}
