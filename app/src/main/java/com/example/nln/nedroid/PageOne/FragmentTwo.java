package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.PageTwo.SsecondNav;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static com.example.nln.nedroid.R.layout.listview_pageone_f2;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment implements AdapterView.OnItemClickListener{

    private View v;
    private ListView listView;
    private ArrayAdapter adapter;
    private Session session;
    private ArrayList<String> Subject;
    private TextView tv;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference subjectRef;

    private ArrayList<String> courses;
    String codes = "";

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_fragment_two2, container, false);

        session = new Session(getContext());
        if(!session.getLogin()){
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        }
        Subject = new ArrayList<>();

        courses = new ArrayList<>();

        courses = session.getCourse();


        tv = (TextView) v.findViewById(R.id.textView_sem);

        tv.setText("Semester " + session.getSemester());

        adapter = new ArrayAdapter<>(getActivity(), listview_pageone_f2, Subject);
        final ListView listView = (ListView) v.findViewById(R.id.ListView_FragTwoSubject);
        listView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        subjectRef = firebaseDatabase.getReference().child("Subjects");


        subjectRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    for (String course: courses) {
                        if(!course.equals("")) {
                            Toast.makeText(getContext(), course, Toast.LENGTH_SHORT).show();
                            if (child.getKey().equals(course) && !course.contains(child.getKey())) {
                                String n = child.getKey() + " " + child.getValue();
                                course += child.getKey();
                                Subject.add(n);
                                Toast.makeText(getContext(), n, Toast.LENGTH_SHORT).show();
                            }
                            adapter.notifyDataSetChanged();
                        }
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


        listView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String n = Subject.get(position);

        String[] words = n.split(" ");

        session.setSubjectCode(words[0]);

        String sub = "";
        for(int i=1;i<words.length;i++){
            sub += words[i] + " ";
        }

        session.setSubjectName(sub);

        Intent i = new Intent(getContext(), SsecondNav.class);
        startActivity(i);


    }
}


