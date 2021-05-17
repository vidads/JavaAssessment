package com.generation.service;

import com.generation.model.Student;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void testSubscribeStudent() throws Exception{
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Date date = formatter.parse("01/01/2000");
        String studentId = "111";
        Student student = new Student( studentId, "Donald", "donald@gmail.com", date);

        StudentService studentService = new StudentService();
        studentService.subscribeStudent(student );
        assertTrue( studentService.isSubscribed( studentId ));
    }

    @Test
    void testFindStudent() throws Exception{
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Date date = formatter.parse("01/01/2000");
        String studentId = "111";
        Student student = new Student( studentId, "Donald", "donald@gmail.com", date);

        StudentService studentService = new StudentService();
        studentService.subscribeStudent(student );

        Student foundStudent = studentService.findStudent(studentId);

        assertEquals( student.toString(), foundStudent.toString());
    }

}