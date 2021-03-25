package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void login(View view) {
        TextView username = findViewById(R.id.editText1);
        TextView password = findViewById(R.id.editText2);
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        Log.d(usernameString, passwordString);

        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {

            //correcct password
        } else {
            //wrong password
        }
    }

}