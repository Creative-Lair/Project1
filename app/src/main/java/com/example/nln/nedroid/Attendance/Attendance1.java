package com.example.nln.nedroid.Attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.nln.nedroid.Nav_AttendOne;
import com.example.nln.nedroid.R;

import java.util.ArrayList;
import java.util.List;

public class Attendance1 extends AppCompatActivity implements ItemClickListener{

    private RecyclerView recyclerView;
    private ABAdaptor adapter;
    private List<AButton> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Attendance");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_buttons);

        albumList = new ArrayList<>();
        adapter = new ABAdaptor(this, albumList);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        prepareAlbums();

    }

    private void prepareAlbums() {

        AButton a = new AButton("Student 1");
        albumList.add(a);

        a = new AButton("Student 2");
        albumList.add(a);

        a = new AButton("Student 3");
        albumList.add(a);

        a = new AButton("Student 4");
        albumList.add(a);

        a = new AButton("Student 5");
        albumList.add(a);

        a = new AButton("Student 6");
        albumList.add(a);

        a = new AButton("Student 7");
        albumList.add(a);

        a = new AButton("Student 8");
        albumList.add(a);

        a = new AButton("Student 9");
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    public void onClickButton (View v){
        Toast.makeText(Attendance1.this, "Clicked on Button Upload", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view, int position) {
        final AButton city = albumList.get(position);
        Toast.makeText(Attendance1.this, city.getName().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Attendance1.this, Nav_AttendOne.class);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
