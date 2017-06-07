package com.example.nln.nedroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Helper.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Student_profile extends AppCompatActivity {

    private TextView name,discipline,year,rollno,paddress,peraddress,
    contactno,email;

    private ImageView icon;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference studentRef;

    private Session session;
    private Student student;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        init();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Profile");

        firebaseDatabase = FirebaseDatabase.getInstance();
        studentRef = firebaseDatabase.getReference().child("Students");

        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        studentRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
                setup();
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

    void init(){
        name = (TextView) findViewById(R.id.name);
        rollno = (TextView) findViewById(R.id.rollnumber);
        discipline = (TextView) findViewById(R.id.discipline);
        year = (TextView) findViewById(R.id.year);
        paddress = (TextView) findViewById(R.id.presentaddress);
        peraddress = (TextView) findViewById(R.id.permanentaddress);
        contactno = (TextView) findViewById(R.id.contactno);
        email = (TextView) findViewById(R.id.emailaddress);
        icon = (ImageView) findViewById(R.id.icon);
    }

    void setup(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data");

        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(true);
        Glide.with(icon.getContext())
                .load(student.getPhotourl())
                .into(icon);
        name.setText(student.getName().toUpperCase());
        rollno.setText(session.getUserId().substring(1).toUpperCase());
        discipline.setText(student.getDiscipline());
        year.setText(student.getYear());
        paddress.setText(student.getPresentaddress());
        peraddress.setText(student.getPermenantaddress());
        contactno.setText(student.getContactno());
        email.setText(student.getEmailaddress());

        progressDialog.hide();

    }
}
