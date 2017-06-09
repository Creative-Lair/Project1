package com.example.nln.nedroid.PageOne;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {


    BarChart line_graph;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Session session;

    public FragmentFour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_four, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Students");

        session = new Session(getContext());
        line_graph = (BarChart) v.findViewById(R.id.graph);
        line_graph.setNoDataText("Loading....");

        databaseReference.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                ArrayList<String> gpas = student.getGpa();
                ArrayList<BarEntry> entries;
                if (gpas.size() > 0) {
                    entries = new ArrayList<>();
                    for (int i = 1; i < gpas.size(); i++) {
                        if (!gpas.get(i).equals("")) {
                            entries.add(new BarEntry(i, Float.parseFloat(gpas.get(i))));
                        }
                    }

                } else {
                    entries = new ArrayList<>();
                    entries.add(new BarEntry(0, 0));
                }
                BarDataSet dataSet = new BarDataSet(entries, "GPA");


                BarData barData = new BarData(dataSet);
                line_graph.setData(barData);

                line_graph.invalidate();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

}
