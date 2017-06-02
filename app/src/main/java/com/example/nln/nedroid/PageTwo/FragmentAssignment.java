package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAssignment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private AssgnAdapter adapter;
    private List<Assg> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    public String Name_DB;//for all app

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
        Name_DB = getActivity().getIntent().getStringExtra("USERNAME");

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_assg);

        albumList = new ArrayList<>();
        adapter = new AssgnAdapter(this, albumList);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        prepareAlbums();
        return v;
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        Assg a = new Assg("Assignment 1 ");
        albumList.add(a);

        int i;
        for (i = 2; i < 11; i++) {
            a = new Assg(" Assignment " + i);
            albumList.add(a);
        }

//        a = new Assg(" I dont know what Im doing :D ");
//        albumList.add(a);
//
//        a = new Assg(" Okay these Blue Bulbs... Dont know what to write.. :P xD ");
//        albumList.add(a);
//
//        a = new Assg("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
//        albumList.add(a);
//
//        a = new Assg("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
//        albumList.add(a);
//
//        a = new Assg(" I dont know what Im doing :D ");
//        albumList.add(a);
//
//        a = new Assg(" Okay these Blue Bulbs... Dont know what to write.. :P xD ");
//        albumList.add(a);
//
//        a = new Assg("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
//        albumList.add(a);
//
//        a = new Assg("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
//        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRemoving()) {
            // onBackPressed()
        }
    }

    @Override
    public void onClick(View view, int position) {

        final Assg city = albumList.get(position);
//        Intent i = new Intent(this, CityviewActivity.class);
        Log.i("hello", city.getName());
//        Toast.makeText(getActivity(), " Assignment " + position, Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();
//        startActivity(i);


//        ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivity(intent);

//        ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
//        Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);

//        Intent i = new Intent(getActivity(), PDF.class);
//        startActivity(i);
    }
}