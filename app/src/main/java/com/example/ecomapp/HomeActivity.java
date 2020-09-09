package com.example.ecomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private List<Book> books =new ArrayList<>();
    private ImageView cart;
    private GridView gridView;
    private EditText search_input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridView = (GridView)findViewById(R.id.gridview);
        search_input = findViewById(R.id.search_input);




        //added retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

            /*books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));*/

        Call<List<Book>> call = api.getBooks();

        //call http get
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                final List<Book> bookList = response.body();
                final BooksAdapter booksAdapter = new BooksAdapter(HomeActivity.this, bookList);

                //set data to gridview using custom adapter
                gridView.setAdapter(booksAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent(HomeActivity.this,ProductInfo.class);
                        in.putExtra("book",bookList.get(position));
                        startActivity(in);
                    }
                });

                search_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
booksAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(HomeActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Retofit",t.getMessage());

            }
        });

        cart = findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this,Cart.class);
                startActivity(in);
            }
        });

    }
}
