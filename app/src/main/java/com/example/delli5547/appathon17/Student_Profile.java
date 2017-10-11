package com.example.delli5547.appathon17;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.delli5547.appathon17.ExtraData.SessionManager;
import com.example.delli5547.appathon17.ExtraData.UserInformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Student_Profile extends AppCompatActivity {

    TextView student_id;
    TextView student_name;
    EditText room_no;
    EditText contact_no;
    EditText expertise;
    EditText interest;

    String room = "";
    String contact = "";
    String exp = "";
    String inte = "";

    Button submit;

    SessionManager sessionManager;
    UserInformation userInformation;
    ProgressDialog progress;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile);

        progress = new ProgressDialog(this);
        student_id = (TextView) findViewById(R.id.student_id);
        student_name = (TextView) findViewById(R.id.student_name);
        room_no = (EditText) findViewById(R.id.room_no);
        contact_no = (EditText) findViewById(R.id.contact_no);
        expertise = (EditText) findViewById(R.id.expertise);
        interest = (EditText) findViewById(R.id.interest);

        submit = (Button) findViewById(R.id.btSubmit);

        sessionManager = new SessionManager(getApplicationContext());
        userInformation = new UserInformation(getApplicationContext());

        student_id.setText(userInformation.getStudent_id());

        getStudentName();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /* if (userInformation.getStatus()) {
                    student_name.setText(userInformation.getName());
                    room_no.setText(userInformation.getRoom_no());
                    contact_no.setText(userInformation.getContact_no());
                    expertise.setText(userInformation.getExpertise());
                    interest.setText(userInformation.getInterest());
                } else {
                    userInformation.setStatus(true);
                }*/

                room = room_no.getText().toString();
                contact = contact_no.getText().toString();
                exp = expertise.getText().toString();
                inte = interest.getText().toString();

                Log.d("room ", " " + room);
                fun_session(room, contact, exp, inte);
            }
        });

    }

    public void getStudentName() {

        progress.show();
        progress.setTitle("Please Wait");
        String url = new Constants().url;
        url = url + "api.php?id=getname";
        Log.d("URl ", "" + url);
//        String url = "http://35.154.244.19/appathon17/api.php?id=getname";
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
                paramter.put("student_id", student_id.getText().toString());
                Log.d("Hello", "Putparam" + student_id.getText().toString());
                return paramter;

            }
        };


        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    private void parseResult(String response) throws JSONException {


        JSONObject obj = new JSONObject(response);
        String text_student_name= obj.getString("message");
        boolean check = obj.getBoolean("success");

        if (check) {
            Log.d("success", "success" + response);

            student_name.setText(text_student_name);
         /*   userInformation.setName(text_student_name);
            userInformation.setRoom_no(room_no.getText().toString());
            userInformation.setContact_no(contact_no.getText().toString());
            userInformation.setExpertise(expertise.getText().toString());
            userInformation.setInterest(interest.getText().toString());*/
            progress.dismiss();
        }

    }


    public void fun_session(final String room_no, final String contact_no, final String expertise, final String interest) {
//        String url = "http://35.154.244.19/appathon17/api.php?id=setstudentdata";
        String url = new Constants().url;
        url = url + "api.php/?id=setstudentdata";
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
                        Toast.makeText(getApplicationContext(), "" +error_msg, Toast.LENGTH_LONG).show();
                       /* Intent i = new Intent(getApplicationContext(), Student.class);
                        startActivity(i);
                        finish();
*/
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

                Log.d("Details : ", " " + room_no + " " + contact_no);
                params.put("student_id", student_id.getText().toString());
                params.put("room_no", room_no);
                params.put("contact_no", contact_no);
                params.put("expertise", expertise);
                params.put("interest", interest);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "req_register");
    }

}
