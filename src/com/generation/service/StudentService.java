package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        return students.containsKey( studentId );
    }

    public void showSummary()
    {
        //TODO implement
        for (Student student : students.values()){
            System.out.println(student.toString());
        }
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }

    public boolean isEnrolledToCourse(String studentId, String courseCode)
    {
        return students.get( studentId ).isAttendingCourse(courseCode);
    }

    public void gradeStudent( String studentId, String courseCode, double score )
    {
        students.get( studentId ).setScore(courseCode, score);

    }

    public void showAverageGradeForCourse(Course course, List<Student> enrolledToCourse)
    {
        System.out.println( "Average Grade for: " +  course.getName());
        String courseCode = course.getCode();
        double total = 0.0;
        double numStudents = 0.0;
        for ( Student student : enrolledToCourse )
        {
            Student s = students.get(student.getId());
            if (s.isGraded(courseCode))
            {
                total += s.getScore(courseCode);
                numStudents++;
            }
        }
        if (numStudents > 0) {
            System.out.println(total / numStudents);
        }
        else{
            System.out.println("There are no graded students.");
        }
    }

}
