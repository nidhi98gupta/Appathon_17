package com.example.delli5547.appathon17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SBG extends AppCompatActivity {


    ProgressDialog progress;
    Button btnStudent;
    Button btnComplain;
    Button inventory;
    Button btnSlot;

    Button btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbg);

        progress = new ProgressDialog(this);
        btnStudent = (Button) findViewById(R.id.btnStudent);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);


        inventory = (Button) findViewById(R.id.btnInventory);
        btnComplain = (Button) findViewById(R.id.btnComplaints);

        btnSlot = (Button) findViewById(R.id.btnSlotBooking);

        btnComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), solve_complains.class);
                startActivity(i);

            }
        });

        btnSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Slot_Feed.class);
                startActivity(i);

            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), populate_studentdata.class);
                startActivity(intent);

            }
        });


        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent i = new Intent(getApplicationContext(),);
                startActivity(i);*/
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Events_Feed.class);
                startActivity(i);

            }
        });
    }

    public void getStudentName() {

        progress.show();
        String url = "http://35.154.244.19/appathon17/api.php?id=getname";
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseResult(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> paramter = new HashMap<String, String>();
//                paramter.put("student_id", student_id.getText().toString());
                Log.d("Hello", "Putparam");
                return paramter;

            }
        };


        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    private void parseResult(String response) throws JSONException {


        JSONObject obj = new JSONObject(response);
        JSONArray jsonArray = obj.optJSONArray("messageData");

        boolean check = obj.getBoolean("success");

        if (check) {
            Log.d("success", "success" + response.toString());

            String text_student_name = jsonArray.toString();

            progress.dismiss();

        }
    }

}
