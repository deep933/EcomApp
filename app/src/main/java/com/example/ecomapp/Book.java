package com.example.ecomapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable {
    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(float book_price) {
        this.book_price = book_price;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public Book(String book_id, String book_url, String book_title, double book_price, String book_author) {
        this.book_id = book_id;
        this.book_url = book_url;
        this.book_title = book_title;
        this.book_price = book_price;
        this.book_author = book_author;
    }

    @SerializedName("book_id")
    private String book_id;

    @SerializedName("book_url")
    private String book_url;

    @SerializedName("book_title")
    private String book_title;

    @SerializedName("book_price")
    private double book_price;

    @SerializedName("book_author")
    private String book_author;
}
