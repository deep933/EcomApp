package com.example.ecomapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    private List<Book> books =new ArrayList<>();
    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));

        ListView listView = (ListView) findViewById(R.id.list_view);
        CartAdapter cartAdapter = new CartAdapter(this, books);
        listView.setAdapter(cartAdapter);

    }

}
