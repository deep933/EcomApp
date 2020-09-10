package com.example.ecomapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Signup Screen
 */

public class Signup extends AppCompatActivity {

    private ImageView back;
    private TextView signin;
    private Button signup;
    private EditText name_input, email_input, pass_input;

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
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);
                Call<User> call = api.signupUser(new User(name_input.getText().toString(), email_input.getText().toString(), "123", pass_input.getText().toString()));

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User user = response.body();
                        saveUser(user);
                        Toast.makeText(Signup.this, "Welcome " + user.getUser_name(), Toast.LENGTH_LONG).show();
                        Intent in = new Intent(Signup.this, HomeActivity.class);
                        startActivity(in);

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });


    }


    private void saveUser(User user) {
        SharedPreferences prefs = getSharedPreferences("userpref", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

    private User getUser() {
        SharedPreferences prefs = getSharedPreferences("userpref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("user", "");
        User user = gson.fromJson(json, User.class);
        return user;
    }
}
