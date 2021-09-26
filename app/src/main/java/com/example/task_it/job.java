package com.example.task_it;

public class job {
    private String  Jobid;
    private String  Jobname;
    private String Status;
    private String Feedback;
    private int Duration;
    private int Rating;
    private int Date;

    public String getJobid() {
        return Jobid;
    }

    public void setJobid(String jobid) {
        Jobid = jobid;

    }

    public void setJobname(String jobname) {
        Jobname = jobname;
    }

    public String getJobname() {
        return Jobname;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getDuration() {
        return Duration;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getRating() {
        return Rating;
    }

    public void setDate(int date) {
        Date = date;
    }

    public int getDate() {
        return Date;
    }

    public job(){

    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getFeedback() {
        return Feedback;
    }
}


