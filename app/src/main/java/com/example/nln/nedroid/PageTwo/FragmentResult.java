package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Result.Result;
import com.example.nln.nedroid.Result.ResultAdapter;
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
public class FragmentResult extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private List<Result> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    private Session session;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference resultRef;

    public FragmentResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_fragment_result, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_result);

        session = new Session(getContext());
        if(!session.getLogin()){
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        resultRef = firebaseDatabase.getReference().child("Results");

        resultRef.child(session.getSubjectCode()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Result result = dataSnapshot.getValue(Result.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        albumList.add(result);
                        adapter.notifyDataSetChanged();
                    }
                });
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

        albumList = new ArrayList<>();
        adapter = new ResultAdapter(this, albumList);



        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        return v;
    }

  @Override
    public void onClick(View view, int position) {

        final Result city = albumList.get(position);

      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(city.getUrl())));


//        Intent i = new Intent(this, CityviewActivity.class);
//        Log.i("hello", city.getName());
//        Toast.makeText(getActivity(), " Just cliked item at position " + position, Toast.LENGTH_LONG).show();
//        startActivity(i);
//        Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();

//        ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
//        Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);

//                ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/pdf");
//        startActivity(intent);

//        Intent i = new Intent(getActivity(), PDF.class);
//        startActivity(i);

    }

}