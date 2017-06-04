package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Forum.AllQuestion;
import com.example.nln.nedroid.Forum.PostQuestion;
import com.example.nln.nedroid.Forum.Question;
import com.example.nln.nedroid.Forum.QuestionAdapter;
import com.example.nln.nedroid.Forum.QuestionAndAnswer;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.nln.nedroid.R.layout.activity_listview_f2;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private QuestionAdapter adapterQuestion;
    private List<Question> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    TextView text;
    ArrayAdapter adapter;
    ArrayList<String> courses;

    private Session session;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference subjectRef;
    private DatabaseReference questionRef;

    public FragmentThree() {
        // Required empty public constructor
    }

    ChildEventListener childEventListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        courses = new ArrayList<>();
        session = new Session(getContext());

        //        List View
        adapter = new ArrayAdapter<>(getActivity(), activity_listview_f2, courses);
        ListView listView = (ListView) v.findViewById(R.id.ListView_Subject);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast pass = Toast.makeText(getActivity(), "Post a Question...", Toast.LENGTH_SHORT);
               // pass.show();
                Intent i = new Intent(getActivity(), PostQuestion.class);
                startActivity(i);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        subjectRef = firebaseDatabase.getReference().child("Subjects");


        subjectRef.child("Semester" + session.getSemester()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   Toast.makeText(getContext(),"" + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    System.out.println(child.getKey() + " " + child.getValue());
                    String n = child.getKey() + " " + child.getValue();
                    courses.add(n);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){

                String n = courses.get(arg2);
                String[] words = n.split(" ");
                session.setQSC(words[0]);
                session.setQSN(words[1]);

                Intent i = new Intent(getActivity(), AllQuestion.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapterQuestion = new QuestionAdapter(this, albumList);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterQuestion);
        adapterQuestion.setClickListener(this);

        questionRef = firebaseDatabase.getReference().child("Questions");


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Question question = dataSnapshot.getValue(Question.class);
                question.setQid(dataSnapshot.getKey());
                albumList.add(0,question);
                adapterQuestion.notifyDataSetChanged();
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


        return v;
    }

    @Override
    public void onClick(View view, int position) {
        final Question city = albumList.get(position);
   //     Log.i("hello", city.getName());
   //     Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();// To show the text on toast
        session.setQID(city.getQid());

        Intent i = new Intent(getActivity(), QuestionAndAnswer.class);
        startActivity(i);

    }

    @Override
    public void onPause() {
        super.onPause();
        albumList.clear();
        if(childEventListener != null) {
            questionRef.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //albumList.cle()
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Question question = dataSnapshot.getValue(Question.class);
                    question.setQid(dataSnapshot.getKey());
                    albumList.add(0,question);
                    adapterQuestion.notifyDataSetChanged();
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
            questionRef.addChildEventListener(childEventListener);
        }

    }
}
