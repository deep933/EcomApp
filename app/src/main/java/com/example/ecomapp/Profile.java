package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class Profile extends AppCompatActivity {
    private TextView user_name,user_email;
    private Button logout;
    private ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user_email = findViewById(R.id.user_email);
        user_name = findViewById(R.id.user_name);
        logout = findViewById(R.id.logout);

        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void logoutUser(){
        SharedPreferences prefs =getSharedPreferences("userpref",MODE_PRIVATE);
        prefs.edit().clear().apply();
        Intent in = new Intent(Profile.this, GettingStarted.class);
        startActivity(in);
        finish();

    }
}