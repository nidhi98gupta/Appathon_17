package com.example.delli5547.appathon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.delli5547.appathon17.ExtraData.UserInformation;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button register;
    UserInformation userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.btnRegister);

        userInformation = new UserInformation(getApplicationContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Webmail_API api = new Webmail_API((email.getText().toString().trim()), password.getText().toString().trim());

                if (!api.login()) {
                    userInformation.setStudent_id(email.getText().toString());

                    if (email.getText().toString().contains("201401175")) {
                        Intent i = new Intent(getApplicationContext(), SBG.class);
                        startActivity(i);
                        finish();

                    } else if (email.getText().toString().contains("spc")) {
                        Intent i = new Intent(getApplicationContext(), Clubs.class);
                        i.putExtra("student_id", email.getText().toString());
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(getApplicationContext(), Student.class);
//                        userInformation.setStudent_id(email.getText().toString());
                        startActivity(i);
                        finish();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
