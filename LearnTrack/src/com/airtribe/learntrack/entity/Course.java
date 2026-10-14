package com.airtribe.learntrack.entity;

public class Course {
    private String id;
    private String courseName;
    private String description;
    private String durationInWeek;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDurationInWeek() {
        return durationInWeek;
    }

    public void setDurationInWeek(String durationInWeek) {
        this.durationInWeek = durationInWeek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void displayCourse(){
        System.out.println("\n=========Course Details=========");
        System.out.println("Course ID: "+id);
        System.out.println("Course Name: "+courseName);
        System.out.println("Description: "+description);
        System.out.println("Duration: "+durationInWeek);
        System.out.println("ACTIVE: "+active);
    }
}
