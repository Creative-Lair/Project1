package com.example.nln.nedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Nav_AttendTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_course;
    TextView tvSecA, tvSecB, tvSecC, tvSecD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lecture Count");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvSecA = (TextView) findViewById(R.id.textView_SecA);
        tvSecB = (TextView) findViewById(R.id.textView_SecB);
        tvSecC = (TextView) findViewById(R.id.textView_SecC);
        tvSecD = (TextView) findViewById(R.id.textView_SecD);

        spinner_course = (Spinner) findViewById(R.id.spinner_subject);
        spinner_course.setOnItemSelectedListener(this);
        List<String> course = new ArrayList<String>();
        course.add("Subject One");
        course.add("Subject Two");
        course.add("Subject Three");
        course.add("Subject Four");
        ArrayAdapter<String> CourseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
        CourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(CourseAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        String str1 ="Subject One";
        String str2 = "Subject Two";
        String str3 = "Subject Three";
        String str4 = "Subject Four";

        if(item.equals(str1)){
            tvSecA.setText("10");
            tvSecB.setText("20");
            tvSecC.setText("15");
            tvSecD.setText("20");
        }
        else if(item.equals(str2)){
            tvSecA.setText("20");
            tvSecB.setText("10");
            tvSecC.setText("20");
            tvSecD.setText("15");
        }
        else if(item.equals(str3)){
            tvSecA.setText("14");
            tvSecB.setText("20");
            tvSecC.setText("40");
            tvSecD.setText("20");
        }
        else if(item.equals(str4)){
            tvSecA.setText("10");
            tvSecB.setText("27");
            tvSecC.setText("42");
            tvSecD.setText("34");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
