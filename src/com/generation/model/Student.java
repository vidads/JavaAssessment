package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Double> scores = new HashMap<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {
        //TODO implement this method
        courses.add(course);
        registerApprovedCourse(course);
    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO implement this method
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses( Course course )
    {
        //DONE: TODO implement this method
        if (approvedCourses.isEmpty())
        {
            average = 0.0;
            return null;
        }

        double total = 0.0;
        int numCourses = 0;
        List<Course> passedCourses = new ArrayList<>();
        for (Course aCourse : approvedCourses.values())
        {
            double score =  scores.get(aCourse.getCode());
            total += score;
            numCourses++;
            if (score >= 50) {
                passedCourses.add(aCourse);
            }
        }

        average = total / (double)numCourses;

        return passedCourses;
    }

    public void setScore(String courseCode, double score)
    {
        scores.put(courseCode, score);
    }

    public boolean isGraded(String courseCode)
    {
        return !scores.isEmpty() && scores.get(courseCode) != null;
    }

    public double getScore(String courseCode){
        return scores.get(courseCode);
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //DONE: TODO implement this method
        return approvedCourses.containsKey(courseCode);
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO implement this method
        if (approvedCourses.isEmpty())
        {
            return null;
        }

        return new ArrayList(approvedCourses.values());
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
