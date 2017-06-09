package com.example.nln.nedroid.PageOne;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nln.nedroid.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {


    public FragmentFour() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_four, container, false);

        // Line Graph
        GraphView line_graph = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> line_series =
                new LineGraphSeries<DataPoint>(new DataPoint[]{
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

        // set the bound
        LinearGraph(line_graph);
//        Dynamic(line_graph);
//        Static(line_graph);
//        Point(line_series);
//        Radius(line_series);
//        BG(line_series);
        return v;
    }

    public void LinearGraph (GraphView LG){
        GraphView line_graph=LG;
        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(true);
        line_graph.getViewport().setMinX(1.0);
        line_graph.getViewport().setMaxX(8.0);

        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(true);
        line_graph.getViewport().setMinY(0.0);
        line_graph.getViewport().setMaxY(4);

        line_graph.getViewport().setScrollable(true);

    }

    public void Dynamic (GraphView LG) {
        GraphView line_graph = LG;
        line_graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " $";
                }
            }
        });
    }

    public void Static (GraphView LG) {
        GraphView line_graph = LG;
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(line_graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
        staticLabelsFormatter.setVerticalLabels(new String[]{"Sun", "Mon", "Tue"});
        line_graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

    public void Point (LineGraphSeries<DataPoint> LS) {
        LineGraphSeries<DataPoint> line_series=LS;
        // custom paint to make a dotted line
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        line_series.setCustomPaint(paint);
    }

    public void Radius (LineGraphSeries<DataPoint> LS) {
        LineGraphSeries<DataPoint> line_series=LS;
        // set the radius of data point
        line_series.setDrawDataPoints(true);
        line_series.setDataPointsRadius(10);
    }

    public void BG (LineGraphSeries<DataPoint> LS) {
        LineGraphSeries<DataPoint> line_series = LS;
        // set the background color below the line
        line_series.setDrawBackground(true);
        line_series.setBackgroundColor(Color.BLUE);
    }

    // set the thickness of line
    // line_series.setThickness(20);
}
