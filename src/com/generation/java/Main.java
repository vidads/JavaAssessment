package com.generation.java;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, courseService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showAverageGrade( studentService, courseService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next().toUpperCase();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, CourseService courseService,
                                      Scanner scanner )
    {
        System.out.println("Enter student ID:");
        String studentId = scanner.next().trim();
        Student student = studentService.findStudent(studentId);
        if (student == null)
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
            return;
        }

        List<Course> enrolledCourses = student.getApprovedCourses();
        if (enrolledCourses == null)
        {
            System.out.println( student.getName() + " is not enrolled in any course." );
            return;
        }

        System.out.println("Enrolled course:");
        for(Course course : enrolledCourses){
            System.out.println("Course: " +  course.getCode() + " (" + course.getName() + ")");
        }

        System.out.println("Insert course ID to be graded");
        String courseCode = scanner.next().trim().toUpperCase();
        Course course = courseService.getCourse(courseCode);
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }

        System.out.println("Insert course grade for: " + course.getName());
        double grade = -1;
        boolean valid = false;
        while(!valid) {
            String input = scanner.next().trim();
            try {
                grade = Double.parseDouble(input);
                if (grade >= 0 && grade <= 100) {
                    valid = true;
                } else {
                    System.out.println( "Invalid grade. Re-enter a number between 0 - 100." );
                    valid=false;
                }
            } catch (Exception e) {
                System.out.println( "Invalid grade. Re-enter a number between 0 - 100." );
                valid=false;
            }
        }
        studentService.gradeStudent(studentId, courseCode, grade);
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }

    private static void showAverageGrade( StudentService studentService,
                                          CourseService courseService, Scanner scanner){
        System.out.println( "Enter course code: " );
        String courseCode = scanner.next();
        Course course = courseService.getCourse(courseCode);
        if (course == null)
        {
            System.out.println( "Course with code " + courseCode + " was not found" );
            return;
        }

        List<Student>  enrolledStudents = courseService.getEnrolledStudents( courseCode );
        if ( enrolledStudents == null)
        {
            System.out.println( "No students enrolled in the course " + course.getName());
            return;
        }

        studentService.showAverageGradeForCourse(course, enrolledStudents);
    }
}
