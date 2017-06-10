package com.example.nln.nedroid.NavigationMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.nln.nedroid.Attendance.Lecture;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class Attendance extends AppCompatActivity {

    long count = 0;
    double attend = 0;
    double percent = 0;
    String code = "";
    int i = 0;
    private Toolbar toolbar;
    private Session session;
    private TextView name, rollnumber;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AttendanceList> attendance;
    private AttendanceAdapter adapter;
    private ArrayList<String> courses;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference classRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_attendance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Attendance");

        session = new Session(this);
        name = (TextView) findViewById(R.id.name);
        rollnumber = (TextView) findViewById(R.id.rollnumber);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        attendance = new ArrayList<>();
        courses = session.getCourse();

        firebaseDatabase = FirebaseDatabase.getInstance();
        classRef = firebaseDatabase.getReference().child("Classes");

        adapter = new AttendanceAdapter(this, attendance);

        name.setText(session.getUsername());
        rollnumber.setText(session.getUserId().substring(1).toUpperCase());

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        for (String c : courses) {
            if (!c.equals("")) {
                classRef.child(c).child(session.getUserSemester()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        code = courses.get(i);
                        i++;
                        count = 0;
                        attend = 0;
                        percent = 0;
                        count = dataSnapshot.getChildrenCount();
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            Lecture l = child.getValue(Lecture.class);
                            Map<String, Boolean> attendance = l.getAttendance();
                            if (attendance.get(session.getUserId().substring(1).toUpperCase())) {
                                attend++;
                            }
                        }

                        if (count > 0) {
                            percent = (attend / count) * 100;
                            DecimalFormat df = new DecimalFormat("#.##");
                            percent = Double.valueOf(df.format(percent));
                        }
                        AttendanceList list = new AttendanceList(code, (double) count, attend, percent);
                        attendance.add(list);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

    }
}