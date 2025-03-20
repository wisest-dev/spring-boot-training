package dev.wisest.exposerestservice.controller;

import dev.wisest.exposerestservice.model.Course;
import dev.wisest.exposerestservice.repository.CourseRepository;
import dev.wisest.exposerestservice.repository.exception.CourseNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses/{courseId}")
    Course getCourse(@PathVariable String courseId) {
        return courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

}
