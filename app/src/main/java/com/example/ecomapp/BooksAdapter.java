package com.example.ecomapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BooksAdapter extends BaseAdapter {

    Book[] books;
    Context mContext;

    @Override
    public int getCount() {
        return 0;
    }

    public BooksAdapter(Context context, Book[] books) {
        this.mContext = context;
        this.books = books;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
