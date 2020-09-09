package com.example.ecomapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginUser implements Serializable {
    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public LoginUser(String user_email, String user_pass) {
        this.user_email = user_email;
        this.user_pass = user_pass;
    }

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_pass")

    private String user_pass;


}
