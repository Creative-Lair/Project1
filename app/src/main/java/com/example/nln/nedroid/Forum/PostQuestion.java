package com.example.nln.nedroid.Forum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.nln.nedroid.R;

public class PostQuestion extends AppCompatActivity {

    public String Name_DB;//for all app
    public String ID_DB;//for all app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Forum");
    }

    public void onPost (View view){
        Toast pass = Toast.makeText(PostQuestion.this, "Posted !!", Toast.LENGTH_SHORT);
        pass.show();
    }

}
