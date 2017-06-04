package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.NewsAndEvents.NandECreate;
import com.example.nln.nedroid.NewsAndEvents.NandEDescription;
import com.example.nln.nedroid.NewsAndEvents.News;
import com.example.nln.nedroid.NewsAndEvents.NewsAdapter;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends android.support.v4.app.Fragment implements ItemClickListener , View.OnClickListener{

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;
    private RecyclerView.LayoutManager mLayoutManager;

    //Firebase objects

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference newsref;

    private Session session;

    private ChildEventListener childEventListener;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Firebase object initialization
        firebaseDatabase = FirebaseDatabase.getInstance();
        newsref = firebaseDatabase.getReference().child("News");
        session = new Session(getContext());

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                News news = dataSnapshot.getValue(News.class);
                news.setId(dataSnapshot.getKey());
                newsList.add(news);
                adapter.notifyDataSetChanged();
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
        };

        //Get all events from firebase
        newsref.addChildEventListener(childEventListener);


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_fragment_one2, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this, newsList);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view, int position) {

        News news = newsList.get(position);

        session.setNewsId(news.getId());

        Intent i = new Intent(getActivity(), NandEDescription.class);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fab:

                Intent i = new Intent(getActivity(), NandECreate.class);
                startActivity(i);

                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        newsList.clear();
        if(childEventListener!=null) {
            newsref.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(childEventListener==null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    News news = dataSnapshot.getValue(News.class);
                    news.setId(dataSnapshot.getKey());
                    newsList.add(news);
                    adapter.notifyDataSetChanged();
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
            };
            newsref.addChildEventListener(childEventListener);
        }
    }
}