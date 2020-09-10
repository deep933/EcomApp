package com.example.ecomapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Product info screen
 */

public class ProductInfo extends AppCompatActivity {

    private ImageView product_img;
    private TextView product_title;
    private TextView product_price;
    private TextView author_name;
    private Button add_cart;

    private ImageView backbtn;
    ArrayList<String> exisiting;


    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);


        Intent in = getIntent();
        final Book book = (Book) in.getSerializableExtra("book");


        product_img = findViewById(R.id.product_img);
        product_title = findViewById(R.id.product_title);
        product_price = findViewById(R.id.product_price);
        author_name = findViewById(R.id.author_name);
        add_cart = findViewById(R.id.add_cart);

        exisiting = getArrayList("books_id");


        if (exisiting.contains(book.getBook_id())) {
            add_cart.setText("Remove from cart");
        } else {
            add_cart.setText("Add to cart");

        }
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("prefrence", Arrays.toString(exisiting.toArray()));
                if (exisiting == null || exisiting.isEmpty()) {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(book.getBook_id());
                    saveArrayList(list, "books_id");
                    Toast.makeText(ProductInfo.this, "Added to cart !!", Toast.LENGTH_LONG).show();
                    add_cart.setText("Remove from cart");

                } else {
                    if (!exisiting.contains(book.getBook_id())) {
                        exisiting.add(book.getBook_id());
                        saveArrayList(exisiting, "books_id");
                        Toast.makeText(ProductInfo.this, "Added to cart !!", Toast.LENGTH_LONG).show();
                        add_cart.setText("Remove from cart");

                    } else {
                        exisiting.remove(book.getBook_id());
                        saveArrayList(exisiting, "books_id");
                        add_cart.setText("Add to cart");
                    }
                    Toast.makeText(ProductInfo.this, "Removed from Cart", Toast.LENGTH_LONG).show();

                }

            }
        });


        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (book != null) {
            Glide.with(ProductInfo.this).load(book.getBook_url()).into(product_img);
            product_title.setText(book.getBook_title());
            product_price.setText(String.valueOf("$" + book.getBook_price()));
            author_name.setText(book.getBook_author());
        }

    }


    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProductInfo.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProductInfo.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
