package com.example.nln.nedroid.PageTwo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nln.nedroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVideo extends Fragment {


    public FragmentVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_video, container, false);
    }

}
