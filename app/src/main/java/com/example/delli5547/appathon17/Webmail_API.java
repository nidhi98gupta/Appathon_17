package com.example.delli5547.appathon17;

import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DELL I5547 on 22-08-2017.
 */

public class Webmail_API {

    private static final String LOGTAG = "RestAPI";
    public static final String REST_URL_LOGIN = "https://webmail.daiict.ac.in/service/home/~/inbox.rss?limit=1";

    private static final int TIME_OUT = 10 * 1000;

    private String user_id, password;

    Webmail_API(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }


    public boolean login() {
        return makeLoginRequest();
    }


    private boolean makeLoginRequest() {

        System.out.println("This is above try");
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            URL url = new URL(REST_URL_LOGIN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String userPassword = user_id + ":" + password;
            String encoding = Base64.encodeToString(userPassword.getBytes(), Base64.DEFAULT);
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            conn.setReadTimeout(TIME_OUT);

            conn.connect();
            System.out.println("This is below try");

            // Toast.makeText(,"1",Toast.LENGTH_SHORT).show();
            Log.d(LOGTAG, "Response Code: " + conn.getResponseCode());
            //   System.out.println("rajna"+conn.getContentEncoding());
            if (conn.getResponseCode() == 200) {
                //System.out.println("This is abovexsddjhdsd try");
                //   Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();

                Log.d(LOGTAG, "Authenticated User Successfully");
                System.out.println("Login successfull");
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                Log.d(LOGTAG, "" + total.toString());
                in.close();
                return true;
            } else {
                Log.d(LOGTAG, "Unable to Authenticate User");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
