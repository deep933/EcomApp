package com.example.ecomapp;

import android.content.SharedPreferences;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart extends AppCompatActivity {

    private List<Book> books =new ArrayList<>();
    private ImageView backbtn;
    private ListView listView;

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

        //books.add(new Book("3415","https://m.media-amazon.com/images/I/51Ys5yejqML.jpg","Saint X: A Novel",13.56,"Alexis Schaitkin"));
        //books.add(new Book("3415","https://m.media-amazon.com/images/I/61mlrLANctL.jpg","Things in Jars: A Novel",12.34,"Jess Kidd"));
        //books.add(new Book("3415","https://m.media-amazon.com/images/I/51nyHHSxOLL.jpg","Such a Fun Age",14.70,"Kiley Reid"));

       listView = (ListView) findViewById(R.id.list_view);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        if(getArrayList("books_id")!=null) {

            Call<List<Book>> calll = api.getSomething(getArrayList("books_id"));

            calll.enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response){
                    final List<Book> bookList = response.body();

                    CartAdapter cartAdapter = new CartAdapter(Cart.this, bookList);
                    listView.setAdapter(cartAdapter);


                }

                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {

                }
            });
        }

    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Cart.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( Cart.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }


}
