package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
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
    String[] AndroidOS = new String[] { 
            "Subject One",
            "Subject Two",
            "Subject Three",
            "Subject Four",
            "Subject Five",
            "Subject Six",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
    };

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_three, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast pass = Toast.makeText(getActivity(), "Post a Question...", Toast.LENGTH_SHORT);
                pass.show();
                Intent i = new Intent(getActivity(), PostQuestion.class);
                startActivity(i);
            }
        });

//        List View
        adapter = new ArrayAdapter<String>(getActivity(), activity_listview_f2, AndroidOS);
        ListView listView = (ListView) v.findViewById(R.id.ListView_Subject);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                // TODO Auto-generated method stub
//                Toast.makeText(getActivity(), "Subjects", Toast.LENGTH_SHORT).show();
                String value = (String)arg0.getItemAtPosition(arg2);
                Toast.makeText(getActivity(), "Clicked on \"" + value + "\"", Toast.LENGTH_SHORT).show();

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
        prepareAlbums();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(isRemoving()){
            // onBackPressed()
        }
    }

    private void prepareAlbums() {

        int[] profile = new int[]{

                // ADD PICTURES IN CARD
                R.drawable.fr_zero,
                R.drawable.fr_one,
                R.drawable.fr_two,
                R.drawable.fr_three,
                R.drawable.fr_four,
                R.drawable.fr_five,
                R.drawable.fr_six,
        };


        Question a = new Question(profile[1], "Name",
                "Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.."
                );
        albumList.add(a);

        a = new Question(profile[2], "Name",
                "Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.."
                );
        albumList.add(a);

        a = new Question(profile[3], "Name",
                "Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.."
                );
        albumList.add(a);

        a = new Question(profile[4], "Name",
                "Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.. Randomly generated Questions of all Subjects.."
                );
        albumList.add(a);

        adapterQuestion.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {
        final Question city = albumList.get(position);
        Log.i("hello", city.getName());
        Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();// To show the text on toast

        Intent i = new Intent(getActivity(), QuestionAndAnswer.class);
        startActivity(i);

    }
}
