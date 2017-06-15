package com.example.nln.nedroid.Graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.nln.nedroid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SingleYearGraph extends AppCompatActivity {

    private BarChart line_graph;
    private Button btn;
    private Spinner spinner_year;

    private float avgCgpa;
    private long count;
    private ArrayList<BarEntry> entries;
    private int i = 0;

    private float barWidth;
    private float barSpace;
    private float groupSpace;
    private ArrayList<String> xValue;
    private int total;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ArrayList<String> years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_year_graph);
        setTitle("Graph");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("CGPA");
        line_graph = (BarChart) findViewById(R.id.graph);
        btn = (Button) findViewById(R.id.btn);
        spinner_year = (Spinner) findViewById(R.id.spinner_year);

        years = new ArrayList<>();

        years.add("First Year");
        years.add("Second Year");
        years.add("Third Year");
        years.add("Fourth Year");

        ArrayAdapter<String> SectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.setAdapter(SectionAdapter);

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        entries = new ArrayList<>();

        line_graph.setDrawBarShadow(false);
        line_graph.setDrawGridBackground(false);
        line_graph.setScaleEnabled(false);
        line_graph.setPinchZoom(false);

        xValue = new ArrayList<>();

        xValue.add(0, " ");
        xValue.add(1, "A");
        xValue.add(2, "B");
        xValue.add(3, "C");
        xValue.add(4, "D");


        line_graph.setNoDataText("Loading....");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner_year.getSelectedItem().toString());
                databaseReference.child(spinner_year.getSelectedItem().toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        System.out.println(dataSnapshot.getKey());
                        long _count = dataSnapshot.getChildrenCount();
                        System.out.println("Count : " + _count);

                        for (DataSnapshot child : children) {
                            Iterable<DataSnapshot> children1 = child.getChildren();
                            System.out.println(child.getKey());
                            String m = child.getKey();
                            avgCgpa = 0;
                            count = 0;
                            count = child.getChildrenCount();
                            for (DataSnapshot d : children1) {
                                System.out.println(d.getKey());
                                String n = (String) d.getValue();
                                Float g = Float.parseFloat(n);
                                avgCgpa += g;

                            }


                            if (_count > 0) {
                                if (m.equals("A")) {
                                    entries.add(new BarEntry(1, avgCgpa / count));

                                } else if (m.equals("B")) {
                                    entries.add(new BarEntry(2, avgCgpa / count));

                                } else if (m.equals("C")) {
                                    entries.add(new BarEntry(3, avgCgpa / count));

                                } else if (m.equals("D")) {
                                    entries.add(new BarEntry(4, avgCgpa / count));

                                }
                                System.out.println(avgCgpa / count);
                                i++;
                            } else {
                                entries.add(new BarEntry(i, 0));
                                i++;
                            }

                            System.out.println(i);
                            // if (i == 2) {
                            drawGraph();
                            // }
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
            }
        });


    }

    void drawGraph() {

        BarDataSet dataSet = new BarDataSet(entries, "GPA");
        System.out.println("drawing graph");
        BarData barData = new BarData(dataSet);
        line_graph.setData(barData);

        line_graph.getXAxis().setAxisMinimum(0);
        line_graph.getXAxis().setAxisMaximum(4);
        line_graph.getData().setHighlightEnabled(false);

        XAxis xAxis = line_graph.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));

        line_graph.invalidate();
        System.out.println("Graph plot");

    }


}
