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

import com.example.nln.nedroid.R;

import java.util.ArrayList;
import java.util.List;

public class AllQuestion extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AllQuestionAdapter adapterQuestion;
    private List<Question> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Forum");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast pass = Toast.makeText(AllQuestion.this, "To post a Question...", Toast.LENGTH_SHORT);
                pass.show();
                Intent i = new Intent(AllQuestion.this, PostQuestion.class);
                startActivity(i);
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

    }

    public void onClick(View view, int position) {
        final Question city = albumList.get(position);
        Log.i("hello", city.getName());

        Intent i = new Intent(AllQuestion.this, QuestionAndAnswer.class);
        startActivity(i);

    }

}
