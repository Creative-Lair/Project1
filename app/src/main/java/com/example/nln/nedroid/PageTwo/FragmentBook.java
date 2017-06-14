package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nln.nedroid.Helper.Book;
import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBook extends Fragment {

    private ArrayList<Book> TextBooks;
    private ArrayList<Book> RefBooks;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference BooksRef;

    private ArrayAdapter adapter,adapter1;


    private Session session;
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
        RefBooks = new ArrayList<>();

        session = new Session(getContext());
        if(!session.getLogin()){
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        }

        adapter = new BookAdapter(getContext(), R.layout.booklayout ,TextBooks);
        ListView listView = (ListView) v.findViewById(R.id.listView1);
        listView.setAdapter(adapter);


        //Listview for ref books
        adapter1 = new BookAdapter(getActivity(), R.layout.booklayout, RefBooks);
        ListView listView2 = (ListView) v.findViewById(R.id.listView2);
        listView2.setAdapter(adapter1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = TextBooks.get(position);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(book.getUrl())));

            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = RefBooks.get(position);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(book.getUrl())));

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        BooksRef = firebaseDatabase.getReference().child("Books");

        BooksRef.child(session.getSubjectCode()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Book book = dataSnapshot.getValue(Book.class);
                if(book.getType().equals("Reference")){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RefBooks.add(book);
                            adapter1.notifyDataSetChanged();
                        }
                    });
                } else if (book.getType().equals("Text")){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextBooks.add(book);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }

}
