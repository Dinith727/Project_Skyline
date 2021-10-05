package com.example.task_it;

public class JobHisClass {
    private String date,jobid,duration,fee,endTime,feedback,jobname,rating,startTime,status,emp;

    public JobHisClass() {
    }

    public JobHisClass(String date, String jobid, String duration, String fee, String endTime, String feedback, String jobname, String rating, String startTime, String status, String emp) {
        this.date = date;
        this.jobid = jobid;
        this.duration = duration;
        this.fee = fee;
        this.endTime = endTime;
        this.feedback = feedback;
        this.jobname = jobname;
        this.rating = rating;
        this.startTime = startTime;
        this.status = status;
        this.emp = emp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }
}
