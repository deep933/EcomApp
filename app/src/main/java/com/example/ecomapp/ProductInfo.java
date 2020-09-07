package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductInfo extends AppCompatActivity {

    private ImageView product_img;
    private TextView product_title;
    private TextView product_price;
    private TextView author_name;
    private Button add_cart;


    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        product_img = findViewById(R.id.product_img);
        product_title = findViewById(R.id.product_title);
        product_price = findViewById(R.id.product_price);
        author_name = findViewById(R.id.author_name);
        add_cart = findViewById(R.id.add_cart);

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent in = getIntent();
        Book book = (Book) in.getSerializableExtra("book");

        if(book!=null){
            Glide.with(ProductInfo.this).load(book.getBook_url()).into(product_img);
            product_title.setText(book.getBook_title());
            product_price.setText(String.valueOf("$"+book.getBook_price()));        }
        author_name.setText(book.getBook_author());

    }
}
