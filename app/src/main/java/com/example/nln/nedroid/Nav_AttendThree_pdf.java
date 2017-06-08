package com.example.nln.nedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.nln.nedroid.Attendance.DetailAdaptor;
import com.example.nln.nedroid.Attendance.Lecture;
import com.example.nln.nedroid.Attendance.Lecture_detail;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Nav_AttendThree_pdf extends AppCompatActivity {

    private ActionBar actionBar;

    private String section, code;
    private Session session;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference lectureRef;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Lecture_detail> details;
    private DetailAdaptor adapter;

    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_three_pdf);
        setTitle("Lecture Detail");
        recyclerView = (RecyclerView) findViewById(R.id.list);

        session = new Session(this);
        if (!session.getLogin()) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        details = new ArrayList<>();

        adapter = new DetailAdaptor(this, details);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        section = session.getSection();
        code = session.getSubject();

        firebaseDatabase = FirebaseDatabase.getInstance();
        lectureRef = firebaseDatabase.getReference().child("Classes");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Lecture lecture = dataSnapshot.getValue(Lecture.class);
                System.out.println(lecture.getSection() + " " + lecture.getSubject());
                if (lecture.getSection().equals(section) && lecture.getSubject().equals(code)) {
                    Lecture_detail detail = new Lecture_detail(lecture.getTimeslot(), lecture.getDate(), lecture.getLectureTopic());
                    details.add(detail);
                    adapter.notifyDataSetChanged();
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
        };

        lectureRef.addChildEventListener(childEventListener);


    }

    @Override
    protected void onPause() {
        super.onPause();

        details.clear();
        if (childEventListener != null) {
            lectureRef.removeEventListener(childEventListener);
            childEventListener = null;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Lecture lecture = dataSnapshot.getValue(Lecture.class);
                    System.out.println(lecture.getSection() + " " + lecture.getSubject());
                    if (lecture.getSection().equals(section) && lecture.getSubject().equals(code)) {
                        Lecture_detail detail = new Lecture_detail(lecture.getTimeslot(), lecture.getDate(), lecture.getLectureTopic());
                        details.add(detail);
                        adapter.notifyDataSetChanged();
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
            };
            lectureRef.addChildEventListener(childEventListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                break;
        }


        return true;
    }
}
