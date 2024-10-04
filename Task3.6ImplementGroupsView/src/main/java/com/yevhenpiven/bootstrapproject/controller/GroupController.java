package com.yevhenpiven.bootstrapproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String listGroups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "groups";
    }

    @GetMapping("/edit/{id}")
    public String showEditGroupForm(@PathVariable("id") int id, Model model) {
        Group group = groupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id: " + id));
        model.addAttribute("group", group);
        return "group_edit";
    }

    @PostMapping("/edit/{id}")
    public String updateGroup(@PathVariable("id") Long id, @ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/group";
    }
}
