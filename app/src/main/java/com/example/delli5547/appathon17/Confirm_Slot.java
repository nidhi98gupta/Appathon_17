package com.example.delli5547.appathon17;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.delli5547.appathon17.adapter.adapter_slots;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL I5547 on 10-09-2017.
 */

public class Confirm_Slot {

    public void confirmSlot(adapter_slots.MyViewHolder holder, final String event_id) {
//        String url = "http://35.154.244.19/appathon17/api.php?id=setstudentdata";
        String url = new Constants().url;
        url = url + "api.php?id=confirmslot";
        Log.d("RegisterActivity", "Url:" + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RegisterActivity", "Response:" + response);
                try {
                    JSONObject mainObject = new JSONObject(response);
                    boolean success_TAG = mainObject.getBoolean("success");
                    String error_msg = mainObject.getString("message");

                    if (success_TAG) {

                    } else {
//                        Toast.makeText(getApplicationContext(), " " + error_msg + "  ", Toast.LENGTH_SHORT).show();
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

                Log.d("Details : ", "Title " + "" + event_id.substring(1));
                params.put("event_id", event_id.substring(1));
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "req_register");
    }


}
