package com.example.nln.nedroid.Notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.NavigationMenu.Attendance1;
import com.example.nln.nedroid.NavigationMenu.Profile;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageOne.FirstNav;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickListener {

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private List<Notice> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView headerName,headerID;
    public String Name_DB;//for all app
    public String ID_DB;//for all app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notice);
        setSupportActionBar(toolbar);
        setTitle("Notice");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //        Navigation Name and id
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        headerName = (TextView) hView.findViewById(R.id.textView_nav_name);
        headerID = (TextView) hView.findViewById(R.id.textView_nav_id);

        //        (setting username data from login class)
        Name_DB = getIntent().getStringExtra("USERNAME");
        headerName.setText("admin");
        ID_DB = getIntent().getStringExtra("USERID");
        headerID.setText("CS-01");

        String log1 = "Name: " + headerName + " ,ID: " + headerID;
        Log.d("Start: ", log1);


        //        (setting username data from login class)
        Name_DB = getIntent().getStringExtra("USERNAME");
        ID_DB = getIntent().getStringExtra("USERID");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new NoticeAdapter(this, albumList);

        mLayoutManager = new GridLayoutManager(NotificationNav.this, 1);
//        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        prepareAlbums();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(NotificationNav.this, FirstNav.class);
        i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
        i.putExtra("USERID", ID_DB);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void prepareAlbums() {
        int[] covers = new int[]{

                // ADD PICTURES IN CARD
                R.drawable.fr_zero,
                R.drawable.fr_one,
                R.drawable.fr_two,
                R.drawable.fr_three,
                R.drawable.fr_four,
                R.drawable.fr_five,
                R.drawable.fr_six,
        };



        Notice a = new Notice(
                "Date ",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  ",
                covers[3]);
        albumList.add(a);

        a = new Notice(
                "10-March",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  ",
                covers[2]);
        albumList.add(a);

        a = new Notice(
                "25-March",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  "
                ,covers[0]);
        albumList.add(a);

        a = new Notice(
                "30-March",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  "
                ,covers[4]);
        albumList.add(a);

        a = new Notice(
                "Date ",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  "
                ,covers[5]);
        albumList.add(a);

        a = new Notice(
                "Date ",
                "Title",
                "Notification Details....  Notification Details..... Notification Details..... Notification Details..... Notification Details.....  Notification Details..... Notification Details..... Notification Details.....  "
                ,covers[6]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(NotificationNav.this, FirstNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);


        } if (id == R.id.nav_profile) {
            Intent i = new Intent(NotificationNav.this, Profile.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_noticeboard) {
            Intent i = new Intent(NotificationNav.this, NotificationNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_attndance) {
            Intent i = new Intent(NotificationNav.this, Attendance1.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(NotificationNav.this, SettingsActivity.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Toast.makeText(NotificationNav.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

