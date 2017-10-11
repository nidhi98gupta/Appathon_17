package com.example.delli5547.appathon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.delli5547.appathon17.adapter.adapter_slots;
import com.example.delli5547.appathon17.model.slots;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slot_Feed extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private List<slots> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter_slots mAdapter;

    ArrayList<slots> marraylist;
    String event_date;
    Button date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__feed);

        marraylist = new ArrayList<>();
        date = (Button) findViewById(R.id.date);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new adapter_slots(eventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        getEvents();


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Slot_Feed.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        mAdapter.notifyDataSetChanged();

    }

    public void getEvents() {

        String url = new Constants().url;
        url = url + "api.php?id=getevent";

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
                paramter.put("event_date", event_date);
                Log.d("Hello", "Putparam" + event_date);
                return paramter;

            }
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

            Log.d("ArryList", "Array" + jsonArray);

            String[] arr = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                arr[i] = jsonArray.getString(i);
                Log.d("Arra ", "" + arr[i]);

                String[] components = arr[i].split(",");
                String event_id = components[0].replaceAll("\"", "");
                String event_name = components[2].replaceAll("\"", "");
                String organizer = components[1].replaceAll("\"", "");
                String date = components[3].replaceAll("\"", "");
                String start_time = components[4].replaceAll("\"", "");
                String end_time = components[5].replaceAll("\"", "");
                String day = components[7].replaceAll("\"", "");
                String location = components[6].replaceAll("\"", "");
                String description = components[8].replaceAll("\"", "");

                slots items = new slots(event_id, event_name, date, start_time, end_time, day, description, organizer, location);
                marraylist.add(items);

            }

            adapter_slots customAdapter = new adapter_slots(marraylist);
            recyclerView.setAdapter(customAdapter);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        event_date = dayOfMonth + "-" + monthOfYear + "-" + year;
        getEvents();

    }
}
