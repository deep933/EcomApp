package com.example.ecomapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * cart custom list adapter
 */

public class CartAdapter extends BaseAdapter {

    List<Book> books;
    Context mContext;
    ArrayList<String> exisiting;


    @Override
    public int getCount() {
        return books.size();
    }

    public CartAdapter(Context context,List<Book> books) {
        this.mContext = context;
        this.books = books;

        exisiting = getArrayList("books_id");

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.cartcard, null);
        }


        ImageView product_img =  (ImageView) v.findViewById(R.id.product_img);
        TextView product_title = (TextView) v.findViewById(R.id.product_title);
        TextView product_price = (TextView) v.findViewById(R.id.product_price);
        ImageView delete = (ImageView) v.findViewById(R.id.delete_item);
        delete.setTag(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();

                if(exisiting.contains(books.get(position).getBook_id())){
                    exisiting.remove(books.get(position).getBook_id());
                    saveArrayList(exisiting,"books_id");
                    ((Activity)mContext).finish();
                    ((Activity)mContext).startActivity(((Activity)mContext).getIntent());


                }

            }
        });


        Glide.with(mContext).load(books.get(position).getBook_url()).into(product_img);
        product_title.setText(books.get(position).getBook_title());
        product_price.setText(String.valueOf("$"+books.get(position).getBook_price()));

        return v;

    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}