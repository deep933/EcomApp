package com.example.ecomapp;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BooksAdapter extends BaseAdapter implements Filterable {

    List<Book> books;
    Context mContext;

    @Override
    public int getCount() {
        return books.size();
    }

    public BooksAdapter(Context context,List<Book> books) {
        this.mContext = context;
        this.books = books;
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
            v = vi.inflate(R.layout.book_card, null);
        }


            ImageView product_img =  (ImageView) v.findViewById(R.id.product_img);
            TextView product_title = (TextView) v.findViewById(R.id.product_title);
            TextView product_price = (TextView) v.findViewById(R.id.product_price);

            Glide.with(mContext).load(books.get(position).getBook_url()).into(product_img);
            product_title.setText(books.get(position).getBook_title());
            product_price.setText(String.valueOf("$"+books.get(position).getBook_price()));



        return v;

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
