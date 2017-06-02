package com.example.nln.nedroid.Forum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.R;

import static com.example.nln.nedroid.R.layout.activity_listview_f2;

public class QuestionAndAnswer extends AppCompatActivity {

    TextView text;
    ArrayAdapter adapter;
    String[] AndroidOS = new String[] {
            "Answer1: answer answer answer answer answer",
            "Answer2: answer answer answer answer answer",
            "Answer3: answer answer answer answer answer ",
            "Answer1: answer answer answer answer answer ",
            "Answer2: answer answer answer answer answer ",
            "Answer3: answer answer answer answer answer "
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Forum");

        adapter = new ArrayAdapter<String>(QuestionAndAnswer.this, activity_listview_f2, AndroidOS);
        ListView listView = (ListView) findViewById(R.id.ListView_Answers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                // TODO Auto-generated method stub
                String value = (String)arg0.getItemAtPosition(arg2);
            }
        });
    }

    public void onPost (View view){
        Toast pass = Toast.makeText(QuestionAndAnswer.this, "Posted !!", Toast.LENGTH_SHORT);
        pass.show();
    }

}
