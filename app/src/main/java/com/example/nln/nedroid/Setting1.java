package com.example.nln.nedroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.Helper.Teacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Setting1 extends AppCompatActivity {


    private ListView listView;
    private Session session;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef;

    private SettingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Settings");

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Send Feedback");
        arrayList.add("Visit Website");
        arrayList.add("About");
        arrayList.add("Resync All Data");
        arrayList.add("Logout");

        firebaseDatabase = FirebaseDatabase.getInstance();
        session = new Session(this);

        listView = (ListView) findViewById(R.id.list);
        adapter = new SettingsAdapter(this, R.layout.setting_layout, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = arrayList.get(position);

                switch (str) {
                    case "Send Feedback":
                        Intent n = new Intent(Setting1.this, Feedback.class);
                        startActivity(n);

                        break;

                    case "Visit Website":
                        Uri webpage = Uri.parse("http://www.android.com");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(webIntent);

                        break;

                    case "About":
                        Uri page = Uri.parse("http://www.android.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, page);
                        startActivity(intent);

                        break;

                    case "Resync All Data":
                        if (session.getUserId().charAt(0) == 's') {
                            rootRef = firebaseDatabase.getReference().child("Students");
                            rootRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    //Toast.makeText(Login.this, dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                                    Student student = dataSnapshot.getValue(Student.class);
                                    session.setLogin(true);
                                    session.setUserId(session.getUserId());
                                    session.setUsername(student.getName());
                                    session.setSemester(student.getSemester());
                                    session.setPhoto(student.getPhotourl());
                                    session.setCourses(student.getCourses());
                                    session.setUserSemester(student.getSection());
                                    Toast.makeText(Setting1.this, "Resync Complete", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {
                            rootRef = firebaseDatabase.getReference().child("Teachers");
                            rootRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Teacher teacher = dataSnapshot.getValue(Teacher.class);
                                    session.setLogin(true);
                                    session.setUserId(session.getUserId());
                                    session.setUsername(teacher.getName());
                                    session.setPhoto(teacher.getPhotourl());
                                    session.setCourses(teacher.getSubjects());
                                    Toast.makeText(Setting1.this, "Resync Complete", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                        break;

                    case "Logout":

                        session.setPhoto("");
                        session.setLogin(false);
                        session.setUserId("");
                        session.setUsername("");
                        session.setQID("");
                        ArrayList<String> course = new ArrayList<>();
                        session.setCourses(course);
                        session.setNewsId("");
                        session.setQSC("");
                        session.setQSN("");
                        session.setSemester("-1");

                        Intent m = new Intent(Setting1.this, Login.class);
                        startActivity(m);
                        finish();

                        break;
                }
            }
        });


    }

}
