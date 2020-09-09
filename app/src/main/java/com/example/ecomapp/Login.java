package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class Login extends AppCompatActivity {
    private ImageView back;
    private Button signin;
    private TextView signup;
    private EditText email_input,pass_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back = findViewById(R.id.backbtn);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.login);

        email_input = findViewById(R.id.email_input);
        pass_input = findViewById(R.id.pass_input);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this,HomeActivity.class);
                startActivity(in);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this, Signup.class);
                startActivity(in);

            }
        });
    }


}
