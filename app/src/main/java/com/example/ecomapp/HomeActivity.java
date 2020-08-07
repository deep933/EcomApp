package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Book> books =new ArrayList<>();
    private ImageView cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));

        cart = findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this,Cart.class);
                startActivity(in);
            }
        });
        GridView gridView = (GridView)findViewById(R.id.gridview);
        final BooksAdapter booksAdapter = new BooksAdapter(this, books);
        gridView.setAdapter(booksAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(HomeActivity.this,ProductInfo.class);
                in.putExtra("book",books.get(position));
                startActivity(in);
            }
        });

    }
}
