package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Book> books =new ArrayList<>();


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

        GridView gridView = (GridView)findViewById(R.id.gridview);
        BooksAdapter booksAdapter = new BooksAdapter(this, books);
        gridView.setAdapter(booksAdapter);

    }
}
