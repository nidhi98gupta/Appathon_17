package com.example.delli5547.appathon17;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Complain extends AppCompatActivity {


    EditText complain_name;
    EditText complain_description;
    Button submit;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);


        complain_name = (EditText) findViewById(R.id.title);
        complain_description = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.submit);
        progress = new ProgressDialog(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setComplain();

            }
        });
    }

    public void setComplain() {
//        String url = "http://35.154.244.19/appathon17/api.php?id=setstudentdata";
        String url = new Constants().url;
        url = url + "api.php?id=setcomplain";
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
                      /*  Intent i = new Intent(getApplicationContext(), Student.class);
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

                Log.d("Details : ", "Title " + complain_name.getText() + "Des " + complain_description.getText().toString());
                params.put("student_id", "201401227");
                params.put("description", complain_description.getText().toString());
                params.put("complain_name", complain_name.getText().toString());

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "req_register");
    }

}
