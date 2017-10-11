package com.example.delli5547.appathon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Student extends AppCompatActivity {

    Button calendar;
    Button profile;
    Button gallery;
    Button complain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        calendar = (Button) findViewById(R.id.btnCalendar);
        profile = (Button) findViewById(R.id.btnProfile);
        gallery = (Button) findViewById(R.id.btnGallery);
        complain = (Button) findViewById(R.id.btnComplain);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Events_Feed.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), Student_Profile.class);
                startActivity(i);

            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Complain.class);
                startActivity(i);


            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent i = new Intent(getApplicationContext(),Events_Feed.class);
//                startActivity(i);

            }
        });

    }
}
