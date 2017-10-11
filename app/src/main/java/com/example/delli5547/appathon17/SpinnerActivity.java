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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.delli5547.appathon17.ExtraData.SessionManager;
import com.example.delli5547.appathon17.ExtraData.UserInformation;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SpinnerActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener, com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener,AdapterView.OnItemSelectedListener  {


    // MainActivity.InventoryClass invobject=new MainActivity.InventoryClass();
    Spinner quantity;
    Spinner itemList;
    String itemName;
    Integer quantityOfItem;
    Button add;
    ProgressDialog progress;
    String student_id;
    SessionManager sessionManager;
    UserInformation userInformation;
    public int hour;
    public int minute;
    Button date;
    TextView selectedDate;
    Button sTime;
    Button eTime;
    TextView startTime;
    TextView endTime;
    String event_date;
    String event_start_time;
    String event_end_time;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventoryclub);

        date = (Button) findViewById(R.id.btDate);
        sTime = (Button) findViewById(R.id.btSTime);
        eTime = (Button) findViewById(R.id.btETime);

        selectedDate = (TextView) findViewById(R.id.selected_date);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        add = (Button) findViewById(R.id.addbutton);
        itemList = (Spinner) findViewById(R.id.item);
        progress = new ProgressDialog(this);
        sessionManager = new SessionManager(getApplicationContext());
        userInformation = new UserInformation(getApplicationContext());

        student_id = userInformation.getStudent_id();

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterOfItems = ArrayAdapter.createFromResource(this,
                R.array.items_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterOfItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        itemList.setAdapter(adapterOfItems);
        itemList.setOnItemSelectedListener(this);


        quantity = (Spinner) findViewById(R.id.numberOfItem);
        Integer[] items = new Integer[]{1, 2, 3, 4};
        ArrayAdapter<Integer> adapterOfQuantity = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items);
        quantity.setAdapter(adapterOfQuantity);
        quantity.setOnItemSelectedListener(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        SpinnerActivity.this,
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
                com.wdullaer.materialdatetimepicker.time.TimePickerDialog tpd = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
                        SpinnerActivity.this,
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
                com.wdullaer.materialdatetimepicker.time.TimePickerDialog tpd = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
                        SpinnerActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
                id = 2;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun_session(itemName, quantityOfItem);
            }
        });


    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spin = (Spinner) parent;
        if (spin.getId() == R.id.item)
            itemName = parent.getItemAtPosition(pos).toString();

        else
            quantityOfItem = (Integer) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void fun_session(final String item_name, final Integer quantity_Item) {
//        String url = "http://35.154.244.19/appathon17/api.php?id=setstudentdata";
        String url = new Constants().url;
        url = url + "api.php?id=setbooking";
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
                        Intent i = new Intent(getApplicationContext(),Clubs.class);
                        startActivity(i);

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

                Log.d("Details : ", " " + item_name + " " + quantity_Item);
                params.put("club_name", "spc");
                params.put("inventory_name", "tables");
                params.put("quantity", quantity_Item.toString());
                params.put("date", event_date);
                params.put("start_time", event_start_time);
                params.put("end_time", event_end_time);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "req_register");
    }


    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        selectedDate.setText(dayOfMonth + " : " + monthOfYear + " : " + year);
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
}
