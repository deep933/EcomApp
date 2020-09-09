package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class GettingStarted extends AppCompatActivity {

    private Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        if(getUser()!=null){
            Intent in = new Intent(GettingStarted.this, HomeActivity.class);
            startActivity(in);
        }


        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(GettingStarted.this, Login.class);
                startActivity(in);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(GettingStarted.this, Signup.class);
                startActivity(in);
            }
        });
    }

    private User getUser(){
        SharedPreferences prefs =getSharedPreferences("userpref",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("user", "");
        User user = gson.fromJson(json, User.class);
        return user;
    }
}
