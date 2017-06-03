package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nln.nedroid.Assigment.Assg;
import com.example.nln.nedroid.Assigment.AssgnAdapter;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
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
public class FragmentAssignment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private AssgnAdapter adapter;
    private List<Assg> assignList;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference assignRef;
    private Session session;

    public FragmentAssignment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_assignment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_assg);

        assignList = new ArrayList<>();
        adapter = new AssgnAdapter(this, assignList);

        session = new Session(getContext());

        firebaseDatabase = FirebaseDatabase.getInstance();
        assignRef = firebaseDatabase.getReference().child("Assignments");

        assignRef.child(session.getSubjectCode()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Assg assg = dataSnapshot.getValue(Assg.class);
                assignList.add(assg);
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
        });


        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view, int position) {

        final Assg assg = assignList.get(position);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(assg.getUrl())));

//        ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/pdf");
//        startActivity(intent);

//        ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
//        Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);

//        Intent i = new Intent(getActivity(), PDF.class);
//        startActivity(i);
    }
}