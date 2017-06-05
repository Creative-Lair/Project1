package com.example.nln.nedroid.Forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AllQuestion extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AllQuestionAdapter adapterQuestion;
    private List<Question> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    private Session session;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference questionRef;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        setTitle(session.getQSN());

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast pass = Toast.makeText(AllQuestion.this, "To post a Question...", Toast.LENGTH_SHORT);
             //   pass.show();
                Intent i = new Intent(AllQuestion.this, PostQuestion.class);
                startActivity(i);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapterQuestion = new AllQuestionAdapter(AllQuestion.this, albumList);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterQuestion);
        adapterQuestion.setClickListener(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        questionRef = firebaseDatabase.getReference().child("Questions");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Question question = dataSnapshot.getValue(Question.class);
                question.setQid(dataSnapshot.getKey());

                if(question.getSub().equals(session.getQSC())) {
                    albumList.add(0, question);
                    adapterQuestion.notifyDataSetChanged();
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

        questionRef.orderByChild("timestamp").addChildEventListener(childEventListener);



    }

    public void onClick(View view, int position) {
        final Question city = albumList.get(position);
     //   Log.i("hello", city.getName());

        session.setQID(city.getQid());

        Intent i = new Intent(AllQuestion.this, QuestionAndAnswer.class);
        startActivity(i);

    }

    @Override
    protected void onResume() {
        super.onResume();
        albumList.clear();
        if(childEventListener==null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Question question = dataSnapshot.getValue(Question.class);
                    question.setQid(dataSnapshot.getKey());

                    if(question.getSub().equals(session.getQSC())) {
                        albumList.add(0, question);
                        adapterQuestion.notifyDataSetChanged();
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
            questionRef.orderByChild("timestamp").addChildEventListener(childEventListener);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(childEventListener!=null){
            questionRef.removeEventListener(childEventListener);
            childEventListener = null;
        }

    }
}
