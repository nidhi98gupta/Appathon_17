package com.example.delli5547.appathon17.model;

/**
 * Created by rajan on 9/9/17.
 */

public class slots {

    String event_id;
    String event_name;
    String date;
    String start_time;
    String end_time;
    String day;
    String description;
    String organizer;
    String location;


    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public slots(String event_id, String event_name, String date, String start_time, String end_time, String day, String description, String organizer, String location) {
        this.event_name = event_name;
        this.date = date;
        this.event_id = event_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
        this.description = description;
        this.organizer = organizer;
        this.location = location;
    }


}