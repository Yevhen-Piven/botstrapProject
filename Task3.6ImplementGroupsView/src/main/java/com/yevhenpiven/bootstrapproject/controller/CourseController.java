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

import com.yevhenpiven.bootstrapproject.entity.Course;
import com.yevhenpiven.bootstrapproject.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/create")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_create";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") int id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id: " + id));
        model.addAttribute("course", course);
        return "edit_course";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}
