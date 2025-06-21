// CourseController.java
package com.iitbombay.courses.controller;

import com.iitbombay.courses.entity.Course;
import com.iitbombay.courses.entity.CourseInstance;
import com.iitbombay.courses.service.CourseService;
import com.iitbombay.courses.dto.CourseCreateRequest;
import com.iitbombay.courses.dto.CourseInstanceCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Course endpoints
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseCreateRequest request) {
        try {
            Course course = courseService.createCourse(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(course);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable String courseId) {
        try {
            Course course = courseService.getCourse(courseId);
            return ResponseEntity.ok(course);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Course Instance endpoints
    @PostMapping("/instances")
    public ResponseEntity<?> createCourseInstance(@Valid @RequestBody CourseInstanceCreateRequest request) {
        try {
            CourseInstance instance = courseService.createCourseInstance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(instance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/instances/{year}/{semester}")
    public ResponseEntity<List<CourseInstance>> getCourseInstancesBySemester(
            @PathVariable Integer year, 
            @PathVariable Integer semester) {
        List<CourseInstance> instances = courseService.getCourseInstancesBySemester(year, semester);
        return ResponseEntity.ok(instances);
    }

    @GetMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<?> getCourseInstance(
            @PathVariable Integer year, 
            @PathVariable Integer semester, 
            @PathVariable String courseId) {
        try {
            CourseInstance instance = courseService.getCourseInstance(year, semester, courseId);
            return ResponseEntity.ok(instance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteCourseInstance(
            @PathVariable Integer year, 
            @PathVariable Integer semester, 
            @PathVariable String courseId) {
        try {
            courseService.deleteCourseInstance(year, semester, courseId);
            return ResponseEntity.ok("Course instance deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}