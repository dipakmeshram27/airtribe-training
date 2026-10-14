package com.airtribe.learntrack.entity;

public class Student extends Person{
    private String batch;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void getStudentDetails(){
        System.out.println("\n=========Student Details=========");
        getDisplayName();
        System.out.println("Batch: "+batch);
        System.out.println("ACTIVE: "+active);
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString()+
                "active=" + active +
                ", batch='" + batch + '\'' +
                '}';
    }
}
