package com.example.nln.nedroid.Notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.NavigationMenu.Attendance;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageOne.FirstNav;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.example.nln.nedroid.Setting1;
import com.example.nln.nedroid.Student_profile;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ItemClickListener {

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private List<Notice> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView headerName,headerID;
    private ImageView headerIcon;

    private Session session;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference noticeRef, rootRef;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notice);
        setSupportActionBar(toolbar);
        setTitle("Notice");
        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        noticeRef = firebaseDatabase.getReference().child("Notice");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //        Navigation Name and id
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (session.getUserId().charAt(0) == 't') {

            Menu menu = navigationView.getMenu();

            MenuItem item = menu.findItem(R.id.nav_attndance);
            item.setVisible(false);

            floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fahad = new Intent(NotificationNav.this, Create_Notice.class);
                    startActivity(fahad);
                }
            });


        }

        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        headerName = (TextView) hView.findViewById(R.id.textView_nav_name);
        headerID = (TextView) hView.findViewById(R.id.textView_nav_id);
        headerIcon = (ImageView) hView.findViewById(R.id.imageView_nav);

        //        (setting username data from login class)
        headerName.setText(session.getUsername().toUpperCase());
        headerID.setText(session.getUserId().substring(1).toUpperCase());
        Glide.with(headerIcon.getContext())
                .load(session.getPhoto())
                .into(headerIcon);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new NoticeAdapter(this, albumList);

        mLayoutManager = new GridLayoutManager(NotificationNav.this, 1);
//        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        recyclerView.setLayoutManager(mLayoutManager);
//      recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        noticeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Notice notice = dataSnapshot.getValue(Notice.class);
                albumList.add(notice);
                adapter.notifyDataSetChanged();
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

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                Intent i = new Intent(NotificationNav.this, FirstNav.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_profile:
                Intent j = new Intent(NotificationNav.this, Student_profile.class);
                startActivity(j);
                finish();
                break;
            case R.id.nav_attndance:
                Intent k = new Intent(NotificationNav.this, Attendance.class);
                startActivity(k);
                finish();
                break;
            case R.id.nav_setting:
                Intent l = new Intent(NotificationNav.this, Setting1.class);
                startActivity(l);
                finish();
                break;

            case R.id.nav_about:
                Toast.makeText(NotificationNav.this, " Link to webite ", Toast.LENGTH_SHORT).show();
                Uri webpage = Uri.parse("http://www.android.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                finish();
                break;

            case R.id.nav_logout:

                session.setPhoto("");
                session.setLogin(false);
                session.setUserId("");
                session.setUsername("");
                session.setQID("");
                ArrayList<String> course = new ArrayList<>();
                session.setCourses(course);
                session.setNewsId("");
                session.setQSC("");
                session.setQSN("");
                session.setSemester("-1");

                Intent m = new Intent(this, Login.class);
                startActivity(m);
                finish();

                break;

            case R.id.nav_sync:
                rootRef = firebaseDatabase.getReference().child("Students");
                rootRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Toast.makeText(Login.this, dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                        Student student = dataSnapshot.getValue(Student.class);
                        session.setLogin(true);
                        session.setUserId(session.getUserId());
                        session.setUsername(student.getName());
                        session.setSemester(student.getSemester());
                        session.setPhoto(student.getPhotourl());
                        session.setCourses(student.getCourses());
                        session.setUserSemester(student.getSection());
                        Toast.makeText(NotificationNav.this, "Resync Complete", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view, int position) {

    }
}

