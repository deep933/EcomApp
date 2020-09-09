package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    private ImageView back;
    private TextView signin;
    private Button signup;
    private EditText name_input,email_input,pass_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        back = findViewById(R.id.backbtn);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        pass_input = findViewById(R.id.pass_input);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Signup.this, Login.class);
                startActivity(in);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Signup.this, HomeActivity.class);
                startActivity(in);
            }
        });



    }
}
