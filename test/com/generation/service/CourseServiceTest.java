package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {
    private final Module module = new Module( "INTRO-CS", "Introduction to Computer Science",
            "Introductory module for the generation technical programs" );

    @Test
    void testRegisterCourse() {
        String courseCode = "INTRO-CS-1";
        CourseService courseService = new CourseService();
        Course actualCourse = new Course( courseCode, "Introduction to Computer Science", 9, module );
        courseService.registerCourse( actualCourse );
        Course  expectedCourse = courseService.getCourse( courseCode );

        assertEquals(actualCourse.toString(), expectedCourse.toString());
    }

    @Test
    void testEnrollStudent() throws Exception{
        String courseCode = "INTRO-CS-1";
        CourseService courseService = new CourseService();
        Course course = new Course( courseCode, "Introduction to Computer Science", 9, module );

        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Date date = formatter.parse("01/01/2000");
        Student student = new Student( "111", "Donald", "donald@gmail.com", date );
        courseService.registerCourse( course );
        courseService.enrollStudent(courseCode, student );

        assertNotNull(courseService.getEnrolledStudents(courseCode));
        assertEquals(student.toString(), courseService.getEnrolledStudents(courseCode).get(0).toString());
    }
}