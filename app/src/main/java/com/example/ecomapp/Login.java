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
 * Login screen
 */

public class Login extends AppCompatActivity {
    private ImageView back;
    private Button signin;
    private TextView signup;
    private EditText email_input, pass_input;


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

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);
                Call<User> call = api.signinUser(new LoginUser(email_input.getText().toString(), pass_input.getText().toString()));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user.getUser_id() != null) {
                            saveUser(user);
                            Intent in = new Intent(Login.this, HomeActivity.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(Login.this, "Wrong credential", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


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

    private void saveUser(User user) {
        SharedPreferences prefs = getSharedPreferences("userpref", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

}
