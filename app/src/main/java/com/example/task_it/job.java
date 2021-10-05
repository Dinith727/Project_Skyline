package com.example.task_it;

public class job {
    private String  Jobid;
    private String startTime;
    private String endtTime;
    private String  Jobname;
    private String Status;
    private String Feedback;
    private String Duration;
    private int Rating;
    private String Date;
    private String Fee;

    public String getEndtTime() {
        return endtTime;
    }

    public void setEndtTime(String endtTime) {
        this.endtTime = endtTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getRating() {
        return Rating;
    }



    public void setRating(int rating) {
        Rating = rating;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public String getJobid() {
        return Jobid;
    }

    public void setJobid(String jobid) {
        Jobid = jobid;
    }

    public String getJobname() {
        return Jobname;
    }

    public void setJobname(String jobname) {
        Jobname = jobname;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }





    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }



    public job(){

    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getFeedback() {
        return Feedback;
    }

    public String getStartTime(String startTime) {
        return startTime;
    }

    public String getEndtTime(String endTime) {
        return endTime;
    }

    public String getStatus(String status) {
        return status;
    }
}


