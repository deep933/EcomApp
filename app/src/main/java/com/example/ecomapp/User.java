package com.example.ecomapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * User model class
 */

public class User implements Serializable {
    public User(String user_name, String user_email, String user_id, String user_pass) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_id = user_id;
        this.user_pass = user_pass;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("user_pass")
    private String user_pass;

}

