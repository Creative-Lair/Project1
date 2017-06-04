package com.example.nln.nedroid.Forum;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.nln.nedroid.R.layout.activity_listview_f2;

public class QuestionAndAnswer extends AppCompatActivity implements View.OnClickListener{

    private TextView text, tvquestion;
    private ArrayAdapter adapter;

    private EditText editTextAnswer;

    private Button btn;

    private ArrayList<Answers> answers;
    private Session session;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference questionRef;
    private DatabaseReference answersRef;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Answers");
        session = new Session(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        questionRef = firebaseDatabase.getReference().child("Questions");
        answers = new ArrayList<>();

        questionRef.child(session.getQID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
                text.setText("[Code:" + question.getSub() + "] " + question.getSubname());
                tvquestion.setText(question.getQuestion());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        text = (TextView) findViewById(R.id.textView_sem);
        tvquestion = (TextView) findViewById(R.id.textView_question);
        btn = (Button) findViewById(R.id.btn);
        editTextAnswer = (EditText) findViewById(R.id.editText_postAnswer);


        btn.setOnClickListener(this);

        answersRef = firebaseDatabase.getReference().child("Answers");

        answersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Answers answer = dataSnapshot.getValue(Answers.class);

                if(answer.getQID().equals(session.getQID())){
                    if(answer.getUID().charAt(0)=='s'){
                        answers.add(answer);
                    } else if(answer.getUID().charAt(0)=='t'){
                        answers.add(0,answer);
                    }
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
        });


        adapter = new QuestionAnswerAdapter(this, R.layout.answerlistadapter, answers);
        ListView listView = (ListView) findViewById(R.id.ListView_Answers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn:

                String ans = editTextAnswer.getText().toString().trim();

                if(!ans.equals("")){
                    String uid = session.getUserId();
                    String qid = session.getQID();
                    String name = session.getUsername();
                    String photourl = session.getPhoto();

                    Answers answers = new Answers(qid,uid,ans,name,photourl);
                    answersRef.push().setValue(answers);

                    Toast.makeText(this, "Your answer is posted", Toast.LENGTH_SHORT).show();

                    editTextAnswer.setText("");
                }


                break;
        }
    }
}
