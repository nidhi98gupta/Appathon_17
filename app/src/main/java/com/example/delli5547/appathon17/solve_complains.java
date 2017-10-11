package com.example.delli5547.appathon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.delli5547.appathon17.adapter.adapter_complains;
import com.example.delli5547.appathon17.model.complains;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class solve_complains extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private List<complains> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter_complains mAdapter;

    ArrayList<complains> marraylist;
    Button solve;
    TextView complain_name_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_complains);

        marraylist = new ArrayList<>();
//        solve = (Button) findViewById(R.id.solve);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new adapter_complains(eventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        complain_name_1 = (TextView) findViewById(R.id.complain_name_1);
        getComplains();
        mAdapter.notifyDataSetChanged();

    }


    public void getComplains() {

        String url = new Constants().url;
        url = url + "api.php?id=getallcomplains";

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
        JSONArray jsonArray = obj.getJSONArray("message");


        Log.d("Response", " " + response);
//        Log.d("ArryList", "Array" + jsonArray.toString());


        boolean check = obj.getBoolean("success");

        if (check) {
            Log.d("success", "success" + response.toString());


            Log.d("ArryList", "Array" + jsonArray.toString());

            String[] arr = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                arr[i] = jsonArray.getString(i);
                Log.d("Arra ", "" + arr[i]);

                String[] components = arr[i].split(",");

                String std_id = components[1].replaceAll("\"", "");
                String complain_name = components[2].replaceAll("\"", "");
                String desc = components[3].replaceAll("\"", "");

                complains items = new complains(std_id, complain_name, desc);
                marraylist.add(items);

            }
            adapter_complains customAdapter = new adapter_complains(marraylist);
            recyclerView.setAdapter(customAdapter);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onClick(View v) {

    }
}
