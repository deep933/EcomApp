package com.example.ecomapp;

import android.database.Observable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "";

    @GET("books")
    Call<List<Book>> getBooks();

    @POST("signup")
    Call<User> signupUser(@Body User user);

    @POST("login")
    Call<User> signinUser(@Body LoginUser user);




}
