package com.example.nln.nedroid.Forum;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class PostQuestion extends AppCompatActivity implements View.OnClickListener{

    private EditText question;
    private Button btn;
    private Spinner spin;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dataRef;

    private Session session;
    ArrayAdapter<String> adapter;

    ArrayList<String> course;
    ArrayList<Long> sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Post a Question");

        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        course = new ArrayList<>();
        sub = new ArrayList<>();

        sub = session.getCourse();

        question = (EditText) findViewById(R.id.question);
        btn = (Button) findViewById(R.id.post);
        spin = (Spinner) findViewById(R.id.subjects);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, course);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);


        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference().child("Subjects");

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    for (long subject: sub) {
                        if(child.getKey().equals(""+subject)){
                            String n = child.getKey() + " " + child.getValue();
                            course.add(n);
                        }
                        adapter.notifyDataSetChanged();
                    }
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
        });


        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.post:

                String ques = question.getText().toString().trim();
                String n = spin.getSelectedItem().toString();
                String[] words = n.split(" ");
                String sub = words[0];
                String photo = session.getPhoto();
                String name = session.getUsername();
                String uid = session.getUserId();

                dataRef = firebaseDatabase.getReference().child("Questions");
                DatabaseReference newQuestion =  dataRef.push();
                newQuestion.child("question").setValue(ques);
                newQuestion.child("sub").setValue(sub);
                newQuestion.child("name").setValue(name);
                newQuestion.child("photourl").setValue(photo);
                newQuestion.child("uid").setValue(uid);
                newQuestion.child("timestamp").setValue(ServerValue.TIMESTAMP);
                newQuestion.child("subname").setValue(words[1]);
                newQuestion.child("semester").setValue(session.getSemester());

                finish();

                break;
        }
    }
}
