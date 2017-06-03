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

import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Result.Result;
import com.example.nln.nedroid.Result.ResultAdapter;

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
//        Intent i = new Intent(this, CityviewActivity.class);
        Log.i("hello", city.getName());
//        Toast.makeText(getActivity(), " Just cliked item at position " + position, Toast.LENGTH_LONG).show();
//        startActivity(i);
//        Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();

//        ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
//        Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);

//                ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivity(intent);

//        Intent i = new Intent(getActivity(), PDF.class);
//        startActivity(i);

    }

}