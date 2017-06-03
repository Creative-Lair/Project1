package com.example.nln.nedroid.PageTwo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nln.nedroid.Helper.Book;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageTwo.FragmentAssignment;
import com.example.nln.nedroid.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NLN on 8/12/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    private int layoutResource;
    ArrayList<Book> books;

    public BookAdapter(Context context, int layoutResource, ArrayList<Book> books) {
        super(context, layoutResource, books);
        this.layoutResource = layoutResource;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        TextView tilte = (TextView) view.findViewById(R.id.title);
        Book book = books.get(position);

        tilte.setText(book.getName());

        return view;
    }
}