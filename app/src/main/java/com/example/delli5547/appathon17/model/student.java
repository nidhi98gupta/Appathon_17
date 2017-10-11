package com.example.delli5547.appathon17.model;

/**
 * Created by Nidhi on 09-09-2017.
 */

public class student {
    String student_name;
    String student_id;
    String room_no;
    String mobile;
    String interests;
    String research;

    public student(String student_name,
                   String student_id,
                   String room_no,
                   String mobile,
                   String interests,
                   String research)
    {
        this.student_name=student_name;
        this.interests=interests;
        this.research=research;
        this.mobile=mobile;
        this.student_id=student_id;
        this.room_no=room_no;
    }
    public String getMobile() {
        return mobile;
    }

    public String getInterests() {
        return interests;
    }

    public String getResearch() {
        return research;
    }

    public String getRoom_no() {
        return room_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }


}
