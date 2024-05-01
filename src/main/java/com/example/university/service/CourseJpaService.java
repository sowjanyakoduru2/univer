
package com.example.university.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.university.repository.CourseRepository;

import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.model.*;

import java.util.*;

@Service

public class CourseJpaservice implements CourseRepository {
    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Override

    public ArrayList<Course> getCourses() {

        List<Course> courseList = courseJpaRepository.findAll();
        ArrayList<Course> courses = new ArrayList<>(courseList);
        return courses;
    }

    @Override
    public Course getCourseById(int courseId) {

        try {

            Course course = courseJpaRepository.findById(courseId).get();

            return course;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @Override

    public Course addCourse(Course course) {
        try {
            Professor professor = course.getProfessor();

            int professorId = professor.getProfessorId();

            professor existingProfessor = professorJpaRepository.findById(professorId).get();
            course.setProfessor(existingProfessor);

            List<Student> students = course.getStudents();

            List<Integer> studentIds = new ArrayList<>();

            for (Student student : students) {

                studentIds.add(student.getStudentId());
            }

            List<Student> existingStudents = studentJpaRepository.findAllById(studentIds);

            course.setStudents(existingStudents);
            courseJpaRepository.save(course);
            return course;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong professorId");
        }
    }

    @Override
    public Course updateCourse(int courseId, Course course) {


        try {
            Course existingCourse = courseJpaRepository.findById(courseId).get();

            if (course.getCourseName() != null) {
                existingCourse.setCoursetName(course.getCourseName());
            }

            if (course.getCredits() !=0) {
                existingCourse.setCredits(course.getCredits());
            }

            if(course.getProfessor() != null){

                Professor professor =course.getProfessor();

                int professorId =professor.getProfessorId();

                Professor existingProfessor= professorJpaRepository.findById(professorId).get();

                existingCourse.setProfessor(existingProfessor);
            }

            if (course.getstudents() != null) {
                List<Student> students =course.getStudents();
                List<Integer> studentIds=new ArrayList();

                for(Student student: students){
                    studentIds.add(student.getStudentId())
                }
                List<Student>existingStudents=studentJpaRepository.findAllById(studntIds);
                existingCourse.setStudents(existingStudents);
            }
            courseJpaRepository.save(existingStudents);
            return existingCourse;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override


    public void deleteCourse(int courseId) {

        try {
            courseJpaRepository.deleteById(courseId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override


    public Professor getProfessorOfCourse(int courseId) {

        try {

            Course course =courseJpaRepository.findById(courseId).get();
            Professor professor=course.getProfessor();

            return professor;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override

    public List<Student> getStudentsOfCourse(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            List<Student> students=course.getStudents();
            return students;
        }

        catch (Exception e) {
            throw new ResponsestatusException(HttpStatus.NOT_FOUND);
        }
    }
}
