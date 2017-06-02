package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Attendance.Attendance1;
import com.example.nln.nedroid.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Teacher_FragmentTwo extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner_course, spinner_section, spinner_timetable;
    View v;
    Button btn;
    RadioGroup RadioGroupOne,RadioGroupTwo;
    RadioButton Regular, Compensatory, Theory, Practical;
    String buttonSelected;
    private TextView headerName,headerID;
    public String Name_DB;//for all app
    public String ID_DB;//for all app

    public Teacher_FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        // Button
        btn = (Button) v.findViewById(R.id.button_create);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonCreate();
            }
        });

        String log1 = "Name: " + headerName + " ,ID: " + headerID;
        Log.d("Start: ", log1);

        // Radio Button & Groups
        RadioGroupOne = (RadioGroup) v.findViewById(R.id.RadioGroupOne);
        RadioGroupTwo = (RadioGroup) v.findViewById(R.id.RadioGroupTwo);
        Regular = (RadioButton) v.findViewById(R.id.Regular);
        Compensatory = (RadioButton) v.findViewById(R.id.Compensatory);
        Theory = (RadioButton) v.findViewById(R.id.Theory);
        Practical = (RadioButton) v.findViewById(R.id.Practical);

        RadioGroupOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.Regular:
                        buttonSelected = "Regular Selected";
                        Compensatory.setChecked(false);
                        break;
                    case R.id.Compensatory:
                        buttonSelected = "Compensatory Selected";
                        Regular.setChecked(false);
                        break;
                    default:

                }
                Toast.makeText(getActivity(), "Selected.. " + buttonSelected, Toast.LENGTH_SHORT).show();
            }
        });

        // Spinner element
        spinner_course = (Spinner) v.findViewById(R.id.spinner_course);
        spinner_section = (Spinner) v.findViewById(R.id.spinner_section);
        spinner_timetable = (Spinner) v.findViewById(R.id.spinner_timeslot);

        // Spinner click listener
        spinner_course.setOnItemSelectedListener(this);
        spinner_section.setOnItemSelectedListener(this);
        spinner_timetable.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> course = new ArrayList<String>();
            course.add("Subject One");
            course.add("Subject Two");
            course.add("Subject Two");

        List<String> section = new ArrayList<String>();
            section.add("Section A");
            section.add("Section B");
            section.add("Section C");
            section.add("Section D");

        List<String> timetable = new ArrayList<String>();
            timetable.add("08:30-09:20 am");
            timetable.add("09:20-10:10 am");
            timetable.add("10:10-11:00 am");
            timetable.add("11:30-12:20 pm");
            timetable.add("12:20-13:10 pm");
            timetable.add("14:00-14:50 pm");
            timetable.add("14:50-15:40 pm");
            timetable.add("15:40-16:30 pm");


        // Creating adapter for spinner
        ArrayAdapter<String> CourseAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, course);
        ArrayAdapter<String> SectionAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, section);
        ArrayAdapter<String> TimeSlotAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, timetable);

        // Drop down layout style - list view with radio button
        CourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimeSlotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_course.setAdapter(CourseAdapter);
        spinner_section.setAdapter(SectionAdapter);
        spinner_timetable.setAdapter(TimeSlotAdapter);

        return v;
    }

    private void onButtonCreate() {
        Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), Attendance1.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
