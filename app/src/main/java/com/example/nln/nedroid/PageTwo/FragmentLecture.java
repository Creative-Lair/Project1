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

import com.example.nln.nedroid.Lecture.Lect;
import com.example.nln.nedroid.Lecture.LectAdaptor;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLecture extends Fragment implements ItemClickListener {

    private RecyclerView LectrecyclerView;
    private LectAdaptor adapter;
    private List<Lect> LectList;
    private RecyclerView.LayoutManager lLayoutManager;

    public FragmentLecture() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_lecture, container, false);

        LectrecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_lect);

        LectList = new ArrayList<>();
        adapter = new LectAdaptor(this, LectList);

        lLayoutManager = new GridLayoutManager(getActivity(), 1);
        LectrecyclerView.setLayoutManager(lLayoutManager);
        LectrecyclerView.setItemAnimator(new DefaultItemAnimator());
        LectrecyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        prepareAlbums();
        return v;
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        Lect a = new Lect("Lecture 1");
        LectList.add(a);

        int i;
        for (i = 2; i < 16; i++) {
            a = new Lect(" Lecture " + i);
            LectList.add(a);
        }

        a = new Lect(" I dont know what Im doing :D ");
        LectList.add(a);

        a = new Lect(" Okay these Blue Bulbs... Dont know what to write.. :P xD ");
        LectList.add(a);

        a = new Lect("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
        LectList.add(a);

        a = new Lect("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
        LectList.add(a);

        a = new Lect(" I dont know what Im doing :D ");
        LectList.add(a);

        a = new Lect(" Okay these Blue Bulbs... Dont know what to write.. :P xD ");
        LectList.add(a);

        a = new Lect("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
        LectList.add(a);

        a = new Lect("Okay mission accomplished :) Now have to set the size of picture and position of picture ... ");
        LectList.add(a);

        a = new Lect(" I dont know what Im doing :D ");
        LectList.add(a);

        a = new Lect(" Okay these Blue Bulbs... Dont know what to write.. :P xD ");
        LectList.add(a);

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

        final Lect city = LectList.get(position);
//        Intent i = new Intent(this, CityviewActivity.class);
        Log.i("hello", city.getName());

//        Toast.makeText(getActivity(), " Lecture clicked item at position " + position, Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), city.getName() + "\nPosition is =  " + (position + 1), Toast.LENGTH_LONG).show();
//        startActivity(i);


//    ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivity(intent);

//    ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
//        Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);

//        Intent i = new Intent(getActivity(), PDF.class);
//        startActivity(i);

    }
}