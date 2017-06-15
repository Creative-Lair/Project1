package com.example.nln.nedroid.PageOne;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nln.nedroid.Graph.IndividualStudent;
import com.example.nln.nedroid.Graph.SingleYearGraph;
import com.example.nln.nedroid.Graph.YearGraph;
import com.example.nln.nedroid.R;


/**
 * Created by NLN on 5/1/2017.
 */

public class Teacher_Fragment_Two_Two extends android.support.v4.app.Fragment {

    View v;
    Button SingleYear, AllYear, Individual;
    public Teacher_Fragment_Two_Two() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_two_two, container, false);

        SingleYear = (Button) v.findViewById(R.id.button_SingleYear);
        AllYear = (Button) v.findViewById(R.id.button_AllYear);
        Individual = (Button) v.findViewById(R.id.button_IS);

        SingleYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), SingleYearGraph.class);
                startActivity(i);
            }
        });

        AllYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), YearGraph.class);
                startActivity(i);
            }
        });

        Individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), IndividualStudent.class);
                startActivity(i);
            }
        });

        return v;
    }
}
