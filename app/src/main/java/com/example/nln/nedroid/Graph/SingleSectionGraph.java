package com.example.nln.nedroid.Graph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nln.nedroid.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import static com.example.nln.nedroid.R.id.graph;


public class SingleSectionGraph extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner_section;
    GraphView line_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_section_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Graph");
        line_graph = (GraphView) findViewById(graph);
        spinner_section = (Spinner) findViewById(R.id.spinner_SingleSection);
        spinner_section.setOnItemSelectedListener(this);
        List<String> section = new ArrayList<String>();
        section.add("Section A");
        section.add("Section B");
        section.add("Section C");
        section.add("Section D");
        ArrayAdapter<String> SectionAdapter = new ArrayAdapter<String>(SingleSectionGraph.this, android.R.layout.simple_spinner_item, section);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_section.setAdapter(SectionAdapter);

        // Line Graph
        LineGraphSeries<DataPoint> line_series =
                new LineGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(0, 1),
                        new DataPoint(1, 2),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2.25),
                        new DataPoint(4, 3.5),
                        new DataPoint(5, 3.2),
                        new DataPoint(6, 3.4)
                });
        line_graph.addSeries(line_series);
        line_graph.getViewport().setScrollable(true);
        line_series.setThickness(7);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        String str1="Section A";
        String str2="Section B";

        if(item.equals(str1)){
            line_graph.removeAllSeries();
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, -2),
                    new DataPoint(1, 4),
                    new DataPoint(2, 5),
                    new DataPoint(3, 3),
                    new DataPoint(4, 1)
            });
            line_graph.addSeries(series);

            // styling
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    return Color.rgb((int) data.getX()*250/4, (int) Math.abs(data.getY()*250/6), 100);
                }
            });

            series.setSpacing(50);

            // draw values on top
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.DKGRAY);

        } else if (item.equals(str2)){

            line_graph.removeAllSeries();
            // Bar Graph
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, -1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            });
            line_graph.addSeries(series);

            // styling
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                }
            });

            series.setSpacing(50);

            // draw values on top
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.CYAN);

            LineGraphSeries<DataPoint> line_series =
                    new LineGraphSeries<DataPoint>(new DataPoint[]{
                            new DataPoint(0, 1),
                            new DataPoint(1, 2),
                            new DataPoint(2, 3),
                            new DataPoint(3, 2.25),
                            new DataPoint(4, 3.5),
                            new DataPoint(5, 3.2),
                            new DataPoint(6, 3.4)
                    });
            line_graph.addSeries(line_series);
            line_graph.getViewport().setScrollable(true);
            line_series.setThickness(7);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
