package com.example.nln.nedroid.Graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.R;
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
import java.util.List;

public class IndividualStudent extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner_depart;
    BarChart line_graph;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText rollnumber;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_section_graph);
        setTitle("Graph");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Students");
        line_graph = (BarChart) findViewById(R.id.graph);
        spinner_depart = (Spinner) findViewById(R.id.spinner_SingleSection);
        rollnumber = (EditText) findViewById(R.id.rollnumber);
        btn = (Button) findViewById(R.id.btn);
        rollnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (rollnumber.getText().toString().length() > 0) {
                    btn.setEnabled(true);
                } else {
                    btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn.setEnabled(false);
        btn.setOnClickListener(this);

        List<String> depart = new ArrayList<>();
        depart.add("cs");
        depart.add("ee");
        depart.add("el");
        depart.add("me");
        depart.add("mt");
        ArrayAdapter<String> SectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, depart);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_depart.setAdapter(SectionAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn:
                line_graph.setNoDataText("Loading...");

                String depart = spinner_depart.getSelectedItem().toString();
                String rolno = rollnumber.getText().toString().trim();

                String r = "s" + depart + rolno;

                databaseReference.child(r).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Student student = dataSnapshot.getValue(Student.class);
                        if (student != null) {
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
                        } else {
                            Toast.makeText(IndividualStudent.this, "Invalid id", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                break;
        }
    }
}
