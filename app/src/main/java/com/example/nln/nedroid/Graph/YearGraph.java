package com.example.nln.nedroid.Graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class YearGraph extends AppCompatActivity {
    BarChart line_graph;

    float avgCgpa;
    long count;
    ArrayList<BarEntry> entries;
    int i = 0;

    float barWidth;
    float barSpace;
    float groupSpace;
    ArrayList<String> xValue;
    int total;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_graph);
        setTitle("Graph");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("CGPA");
        line_graph = (BarChart) findViewById(R.id.graph);

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        entries = new ArrayList<>();

        line_graph.setDrawBarShadow(false);
        line_graph.setDrawGridBackground(false);
        line_graph.setScaleEnabled(false);
        line_graph.setPinchZoom(false);

        xValue = new ArrayList<>();

        xValue.add(0, "First");
        xValue.add(1, "Second");
        xValue.add(2, "Third");
        xValue.add(3, "Fourth");


        line_graph.setNoDataText("Loading....");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                avgCgpa = 0;
                count = 0;

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                System.out.println(dataSnapshot.getKey());
                String m = dataSnapshot.getKey();
                long _count = dataSnapshot.getChildrenCount();
                System.out.println("Count : " + _count);

                for (DataSnapshot child : children) {
                    Iterable<DataSnapshot> children1 = child.getChildren();
                    System.out.println(child.getKey());

                    for (DataSnapshot d : children1) {
                        Iterable<DataSnapshot> children2 = d.getChildren();
                        System.out.println(d.getKey());
                        count += d.getChildrenCount();

                        for (DataSnapshot e : children2) {
                            System.out.println(e.getKey());
                            String n = (String) e.getValue();
                            Float g = Float.parseFloat(n);
                            avgCgpa += g;

                        }
                    }
                }

                if (_count > 0) {
                    if (m.equals("First Year")) {
                        entries.add(new BarEntry(0, avgCgpa / count));

                    } else if (m.equals("Second Year")) {
                        entries.add(new BarEntry(1, avgCgpa / count));

                    } else if (m.equals("Third Year")) {
                        entries.add(new BarEntry(2, avgCgpa / count));

                    } else if (m.equals("Fourth Year")) {
                        entries.add(new BarEntry(3, avgCgpa / count));

                    }
                    System.out.println(avgCgpa / count);
                    i++;
                } else {
                    entries.add(new BarEntry(i, 0));
                    i++;
                }

                System.out.println(i);
                if (i == 2) {
                    drawGraph();
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

    void drawGraph() {

        BarDataSet dataSet = new BarDataSet(entries, "GPA");
        System.out.println("drawing graph");
        BarData barData = new BarData(dataSet);
        line_graph.setData(barData);
        //line_graph.invalidate();
        //line_graph.getBarData().setBarWidth(barWidth);
        line_graph.getXAxis().setAxisMinimum(0);
        line_graph.getXAxis().setAxisMaximum(4);
        line_graph.getData().setHighlightEnabled(false);

        //X-axis
        XAxis xAxis = line_graph.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
//Y-axis

        line_graph.invalidate();
        System.out.println("Graph plot");

    }


}
