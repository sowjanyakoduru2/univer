package com.example.university.controller;

import com.example.university.model.*;
import com.example.university.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController

public class StudentController {

    @Autowired

    public Student JpaService
    student JpaService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return student JpaService.getStudents();
    }

    @GetMapping("/students/(studentld)")
    public Student getStudentById(@PathVariable("studentId") int studentId) {

        return studentJpaService.getStudentById(studentId);

    }

    @PostMapping("/students")

    public Student addStudentr(@RequestBody Student student) {

        return studentJpaService.addStudent(student);

    }

    @PutMapping("/students/(studentId)")

    public Student updateStudent (@PathVariable("studentId") int studentId, @RequestBody Student student) {

        return student JpaService.updateStudent (studentId, student);
    }

    @DeleteMapping("/students/(studentId)")

    public void deleteStudent(@PathVariable("studentId") int professorId) {

        studentJpaService.deleteStudent(studentId);
    }

    @GetMapping("/students/(studentId)/courses")
    public List<Course> getStudentCourses(@PathVariable("studentId") int studentId) {

        return studentJpaService.getStudentCourses(studentId);
    }
}
