package com.example.delli5547.appathon17.ExtraData;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by demon on 3/17/2016.
 */
public class UserInformation {

    private static String TAG = UserInformation.class.getSimpleName();

    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "USER_DATA";

    String student_id;
    String name;
    String room_no;
    String contact_no;
    String expertise;
    String interest;

    boolean SECTION_ALERT_STATUS = false;

    public UserInformation(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getFirstName() {
        return pref.getString("FirstName", null);
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;

        editor.putString("student_id", student_id);
        editor.commit();
    }

    public String getStudent_id() {
        return pref.getString("student_id", null);
    }


    public void setName(String student_name) {
        this.name = student_name;
        editor.putString("student_name", student_name);
        editor.commit();
    }


    public String getName() {
        return pref.getString("student_name", null);

    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
        editor.putString("room_no", room_no);
        editor.commit();
    }


    public String getRoom_no() {
        return pref.getString("room_no", null);

    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
        editor.putString("contact_no", contact_no);
        editor.commit();
    }


    public String getContact_no() {
        return pref.getString("contact_no", null);
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
        editor.putString("expertise", expertise);
        editor.commit();
    }


    public String getExpertise() {
        return pref.getString("expertise", null);
    }

    public void setInterest(String interest) {
        this.interest = interest;
        editor.putString("interest", interest);
        editor.commit();
    }


    public String getInterest() {
        return pref.getString("interest", null);
    }


    public boolean getStatus() {
        return pref.getBoolean("Status", false);
    }

    public void setStatus(boolean status) {
//        this.status = status;

        editor.putBoolean("Status", status);
        editor.commit();
    }


}