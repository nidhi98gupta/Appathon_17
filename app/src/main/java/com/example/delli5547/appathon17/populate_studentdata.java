package com.example.delli5547.appathon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.delli5547.appathon17.adapter.adapter_studentcardview;
import com.example.delli5547.appathon17.model.student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nidhi on 09-09-2017.
 */

public class populate_studentdata extends AppCompatActivity {
    private List<student> studentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter_studentcardview mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.populate_students);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new adapter_studentcardview(studentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getStudentList();

        mAdapter.notifyDataSetChanged();
    }

    public void getStudentList() {

        String url = new Constants().url;
        url = url + "api.php?id=getbatchstudents";

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

        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(jsonObjectRequest);


    }

    private void parseResult(String response) throws JSONException {

        JSONObject obj = new JSONObject(response);
        JSONArray jsonArray = obj.optJSONArray("message");

        Log.d("Response", " " + response.toString());
//        Log.d("ArryList", "Array" + jsonArray.toString());


        boolean check = obj.getBoolean("success");

        if (check) {
            Log.d("success", "success" + response.toString());


            Log.d("ArryList", "Array" + jsonArray.toString());
            Log.d("size", "yo" + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {


                // JSONObject post =(JSONObject) jsonArray.get(i);
                String student_id = jsonArray.getJSONArray(i).getString(0);
                String student_name = jsonArray.getJSONArray(i).getString(1);
                String room_no = jsonArray.getJSONArray(i).getString(2);
                String phone = jsonArray.getJSONArray(i).getString(3);
                String expertise = jsonArray.getJSONArray(i).getString(4);
                String interest = jsonArray.getJSONArray(i).getString(5);
                //Log.d("Quiz", "User " + " Lat " + post.toString());
                student data = new student(student_name, student_id, room_no, phone, interest, expertise);

                studentList.add(data);

            }

            adapter_studentcardview customAdapter = new adapter_studentcardview(studentList);
            recyclerView.setAdapter(customAdapter);
        }
    }

}
