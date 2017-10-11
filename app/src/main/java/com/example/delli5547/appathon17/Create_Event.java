package com.example.delli5547.appathon17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create_Event extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {


    Button date;
    TextView selectedDate;
    Button sTime;
    Button eTime;
    TextView startTime;
    TextView endTime;
    ProgressDialog progress;
    int id = 0;
    EditText title;
    EditText desc;
    Button submit;

    String event_name;
    String event_date;
    String event_start_time;
    String event_end_time;
    String description;
    String location;
    Spinner spinner;

    String student_id;
    String dayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        date = (Button) findViewById(R.id.btDate);
        sTime = (Button) findViewById(R.id.btSTime);
        eTime = (Button) findViewById(R.id.btETime);

        selectedDate = (TextView) findViewById(R.id.selected_date);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);

        progress = new ProgressDialog(this);
        title = (EditText) findViewById(R.id.title);
        desc = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.btSubmit);

        student_id = getIntent().getStringExtra("student_id");
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("LT");
        categories.add("SAC");
        categories.add("OAT");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Create_Event.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });

        sTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        Create_Event.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
                id = 1;
            }
        });

        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        Create_Event.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
                id = 2;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                event_name = title.getText().toString();
                description = desc.getText().toString();
                setEvent();
            }
        });

    }


    public void setEvent() {
//        String url = "http://35.154.244.19/appathon17/api.php?id=setstudentdata";
        String url = new Constants().url;
        url = url + "api.php?id=setevent";
        Log.d("RegisterActivity", "Url:" + url);
        progress.setMessage("Please Wait...");
//                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RegisterActivity", "Response:" + response);
                try {
                    JSONObject mainObject = new JSONObject(response);
                    boolean success_TAG = mainObject.getBoolean("success");
                    String error_msg = mainObject.getString("message");

                    if (success_TAG) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "" + error_msg, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Clubs.class);
                        startActivity(i);
                        finish();

                    } else {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), " " + error_msg + "  ", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                Log.d("Details : ", "Title " + event_name + "Des " + description + " " + event_date + "");
                params.put("event_name", event_name);
                params.put("organizer", student_id);
                params.put("description", description);
                params.put("event_date", event_date);
                params.put("location", location);
                params.put("event_start_time", event_start_time);
                params.put("event_end_time", event_end_time);
                params.put("day", dayOfWeek);
//                params.put("confirm", "0");

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "req_register");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");

        Date date = new Date(year, monthOfYear, dayOfMonth - 1);
        dayOfWeek = simpledateformat.format(date);

//        monthOfYear = monthOfYear+1;
        selectedDate.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
        event_date = dayOfMonth + "-" + "" + monthOfYear + "-" + "" + year;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

        if (id == 1) {
            startTime.setText(hourOfDay + " : " + minute);
            event_start_time = String.valueOf(hourOfDay);
        } else {
            endTime.setText(hourOfDay + " : " + minute);
            event_end_time = String.valueOf(hourOfDay);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        location = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), " Selected Venue  " + location, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
