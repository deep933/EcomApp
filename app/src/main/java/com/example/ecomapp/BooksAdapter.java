package com.example.ecomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * books custom list adapter
 */

public class BooksAdapter extends BaseAdapter implements Filterable {

    List<Book> originalbooks;
    List<Book> filteredbooks;
    Context mContext;

    private ItemFilter mFilter = new ItemFilter();


    @Override
    public int getCount() {
        return filteredbooks.size();
    }

    public BooksAdapter(Context context, List<Book> books) {
        this.mContext = context;
        this.filteredbooks = books;
        this.originalbooks = books;
    }

    @Override
    public Book getItem(int position) {
        return filteredbooks.get(position);
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


        ImageView product_img = (ImageView) v.findViewById(R.id.product_img);
        TextView product_title = (TextView) v.findViewById(R.id.product_title);
        TextView product_price = (TextView) v.findViewById(R.id.product_price);

        Glide.with(mContext).load(filteredbooks.get(position).getBook_url()).into(product_img);
        product_title.setText(filteredbooks.get(position).getBook_title());
        product_price.setText(String.valueOf("$" + filteredbooks.get(position).getBook_price()));


        return v;

    }

    @Override
    public Filter getFilter() {

        return mFilter;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Book> list = originalbooks;

            int count = list.size();
            final ArrayList<Book> nlist = new ArrayList<Book>(count);

            Book filterableBook;

            for (int i = 0; i < count; i++) {
                filterableBook = list.get(i);
                if (filterableBook.getBook_title().toLowerCase().contains(filterString)) {
                    nlist.add(filterableBook);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredbooks = (ArrayList<Book>) results.values;
            notifyDataSetChanged();
        }

    }
}

