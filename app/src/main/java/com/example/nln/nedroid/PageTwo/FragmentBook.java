package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nln.nedroid.Helper.Book;
import com.example.nln.nedroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.nln.nedroid.R.layout.activity_listview_f3;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBook extends Fragment implements AdapterView.OnClickListener {

    View v;
    FragmentManager fragmentManager = getFragmentManager();

    private ArrayList<Book> TextBooks;
    private ArrayList<Book> RefBooks;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference BooksRef;

    public FragmentBook() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_book, container, false);

        TextBooks = new ArrayList<>();


        //Listview for text book
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), activity_listview_f3, TextBooks);
        ListView listView = (ListView) v.findViewById(R.id.listView1);
        listView.setAdapter(adapter);


        //Listview for ref books
        ArrayAdapter adapter1 = new ArrayAdapter<>(getActivity(), activity_listview_f3, RefBooks);
        ListView listView2 = (ListView) v.findViewById(R.id.listView2);
        listView2.setAdapter(adapter1);

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.listView1:

                break;

            case R.id.listView2:

                break;
        }
    }
}
