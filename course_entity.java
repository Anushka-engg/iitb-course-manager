// Course.java
package com.iitbombay.courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Course title is required")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Course ID is required")
    @Column(name = "course_id", nullable = false, unique = true)
    private String courseId;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "course_prerequisites",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    @JsonManagedReference
    private List<Course> prerequisites = new ArrayList<>();
    
    @ManyToMany(mappedBy = "prerequisites")
    @JsonBackReference
    private List<Course> dependentCourses = new ArrayList<>();
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CourseInstance> instances = new ArrayList<>();
    
    // Constructors
    public Course() {}
    
    public Course(String title, String courseId, String description) {
        this.title = title;
        this.courseId = courseId;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<Course> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<Course> prerequisites) { this.prerequisites = prerequisites; }
    
    public List<Course> getDependentCourses() { return dependentCourses; }
    public void setDependentCourses(List<Course> dependentCourses) { this.dependentCourses = dependentCourses; }
    
    public List<CourseInstance> getInstances() { return instances; }
    public void setInstances(List<CourseInstance> instances) { this.instances = instances; }
}

// CourseInstance.java
package com.iitbombay.courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "course_instances", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"course_id", "year", "semester"})
})
public class CourseInstance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be at least 2000")
    @Max(value = 2100, message = "Year must be at most 2100")
    @Column(nullable = false)
    private Integer year;
    
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 2, message = "Semester must be at most 2")
    @Column(nullable = false)
    private Integer semester;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    private Course course;
    
    // Constructors
    public CourseInstance() {}
    
    public CourseInstance(Integer year, Integer semester, Course course) {
        this.year = year;
        this.semester = semester;
        this.course = course;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}