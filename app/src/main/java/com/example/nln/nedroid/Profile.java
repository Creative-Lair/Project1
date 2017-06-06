package com.example.nln.nedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Helper.Teacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference TeachRef;

    private ImageView image;
    private TextView name,designation,phone,email,depart,date,exp;
    private ListView subject;

    Teacher teacher;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new Session(this);
        setTitle(session.getUsername());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setData();

        //        PDF for Attendance
//        pdfView = (PDFView) findViewById(R.id.pdfView);
//        pdfView.fromAsset("Profile.pdf").load();//METHOD 1: PDF from Asset
    }

    private void setData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        TeachRef = firebaseDatabase.getReference().child("Teachers");

        image = (ImageView) findViewById(R.id.thumbnail);
        name = (TextView) findViewById(R.id.title);
        designation = (TextView) findViewById(R.id.desig);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        depart = (TextView) findViewById(R.id.depart);
        date = (TextView) findViewById(R.id.J_Date);
        exp = (TextView) findViewById(R.id.exp);
        subject = (ListView) findViewById(R.id.subjects);


        TeachRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                teacher =  dataSnapshot.getValue(Teacher.class);

                Glide.with(getApplicationContext()).load(teacher.getPhotourl()).centerCrop().into(image);
                name.setText(teacher.getName());
                phone.setText(teacher.getPhone());
                designation.setText(teacher.getDesignation());
                email.setText(teacher.getEmail());
                depart.setText(teacher.getDepartment());
                date.setText(teacher.getJoinDate());
                exp.setText(teacher.getExperence());
                ArrayList<String> subj = teacher.getSubjects();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Profile.this, android.R.layout.simple_list_item_1, subj);
                subject.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();

                break;
        }



        return true;
    }
}