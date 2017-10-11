package com.example.delli5547.appathon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Clubs extends AppCompatActivity {

    Button event;
    Button inventory;
    Button complain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);

        inventory=(Button)findViewById(R.id.btnInventory) ;
        event = (Button) findViewById(R.id.btnEvent);
        complain = (Button)findViewById(R.id.btnComplain);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Create_Event.class);
                startActivity(i);

            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), SpinnerActivity.class);
                startActivity(i);

            }
        });


        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Complain.class);
                startActivity(i);
            }
        });
    }
}
