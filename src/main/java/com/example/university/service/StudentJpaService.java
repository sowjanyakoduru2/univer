
package com.example.university.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.NoSuchElementException;

import com.example.university.repository.StudentRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.model.Student;
import com.example.university.model.Course;

import java.util.*;

@Service

public class Student paservice implements StudentRepository {

    @Autowired

    private StudentJpaRepository studentJpaRepository;

    @Autowired

    private CourseJpaRepository courseJpaRepository;

    @Override

    public ArrayList<Student> getStudents() {

        List<Student> studentstist = studentJpaRepository.findAll();
        ArrayList<Student> students = new ArrayList<>(studentsList);

        return students;
    }

    @Override

    public Student getStudentById(int studentId) {

        try {
            Student student =studentJpaRepository.findById(studentId).get();
            return student;
        }
        catch (Exception e) {   

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override

    public Student addstudent(Student student) {
        List<Integers> courseIds = new ArrayList<>();

        for (Course course : student.getCourses()) {
            courseIds.add(course.getCourseId());
        }
        List<Course> courses = courseJpaRepository.findAllById(courseIds);

        if (courses.size() != courseIds.size()) {

            throw new ResponsestatusException(HttpStatus.BAD_REQUEST, "One or more courses are not found");
        }

        student.setCourses(courses);
        for (Course course : courses) {
            course.getStudents().add(student);
        }
        studentJpaRepository.save(student);

        courseJpaRepository.saveAll(courses);
        return student;
    }

    @Override

    public Student updateStudent(int studentId, Student student) {

        try {

            Student existingStudent =studentJpaRepository.findById(studentId).get();

            if (student.getStudentName() != null) {

                existingstudent.setStudentName(student.getStudentName());
            }

            if (student.getEmail() != null) {

                existingstudent.setEmail(student.getEmail());
            }

            if (student.getCourses() != mull) {

                List <Course> existingCourses= existingstudent.getCourses();

                for (Course course: existingCourses) {
                    course.getStudents().remove(existingStudent);
                }
                courseJpaRepository.saveAll(existingcourses);

                List<Integer> courseIds= new ArrayList<>();

                for (Course course :student.getCourses()) {
                     courseIds.add(course.getCourseId());
                }

                List <Course >courses = courseJpaRepository.findAllById(courselds);

                if (courses.size()!= courselds.size()) {

                    throw new ResponsestatusException(HttpStatus.BAD REQUEST, "One or more courses are not found"); 
                }

                existingstudent.setCourses(courses);

                for (Course course :courses) {
                    course.getStudents().add(existingstudent);
                }

                courseJpatepository.saveAll(courses);
            }
            student JpaRepository.save(existingstudent);

            return existingStudent;

        } 
        catch (NoSuchElementException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }
    @Override

    public void deleteStudent(int studentId) {

        try {

            Student student =studentJpaRepository.findById(studentId).get();

            List<Course> courses =student.getCourses();

            for (Course course : courses) {

                course.getStudents().remove(student);
            }

            courseJpaRepository.saveAll(courses);

            studentJpaRepository.deleteById(student Id);

        }catch(Exception e)
        {

        throw new ResponsestatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override

    public List<Course> getCoursesOfStudent(int studentId) {

        try {
            Student student =studentJpaRepository.findById(studentId).get(); 
            List<Course> courses= student.getCourses();

            return courses;

        } catch (Exception e) {
            throw new ResponsestatusException(HttpStatus.NOT_FOUND);
        }
    }
}
