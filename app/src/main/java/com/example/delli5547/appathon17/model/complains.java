package com.example.delli5547.appathon17.model;

/**
 * Created by rajan on 9/9/17.
 */

public class complains {


    String std_id;
    String complain_name;
    String desc;


    public complains(String std_id, String complain_name, String desc) {
        this.std_id= std_id;
        this.complain_name= complain_name;
        this.desc= desc;
    }


    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getComplain_name() {
        return complain_name;
    }

    public void setComplain_name(String complain_name) {
        this.complain_name = complain_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
