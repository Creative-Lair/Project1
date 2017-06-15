package com.example.nln.nedroid.PageOne;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.Notification.NotificationNav;
import com.example.nln.nedroid.Profile;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.example.nln.nedroid.Setting1;

import java.util.ArrayList;
import java.util.List;

public class Teacher_FirstNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView headerName,headerID;

    private ImageView headerIcon;

    private Session session;

    private int[] tabIcons = {
            R.drawable.ic_attendance_white,
            R.drawable.ic_gtraph_white,
            R.drawable.ic_forum_white
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_nav);
        toolbar = (Toolbar) findViewById(R.id.toolbar__nav);
        setSupportActionBar(toolbar);
        setTitle("NEDroid");
        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }


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
        headerIcon = (ImageView) hView.findViewById(R.id.imageView_nav);
        Glide.with(headerIcon.getContext())
                .load(session.getPhoto())
                .into(headerIcon);

//        (setting username data from login class)
        headerName.setText(session.getUsername());
        headerID.setText(session.getUserId());
        Glide.with(headerIcon.getContext())
                .load(session.getPhoto())
                .into(headerIcon);


//        Tab Layout
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            finish();

        } if (id == R.id.nav_profile) {
            Intent i = new Intent(Teacher_FirstNav.this, Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(Teacher_FirstNav.this, Setting1.class);
            startActivity(i);

        } else if (id == R.id.nav_notice){
            Intent j = new Intent(this, NotificationNav.class);
            startActivity(j);

        } else if (id == R.id.nav_about) {
            Toast.makeText(Teacher_FirstNav.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);

        } else if (id == R.id.nav_logout) {
            session.setLogin(false);
            session.setUserId("");
            session.setUsername("");
            session.setSubject("");
            session.setSection("");
            ArrayList<String> course = new ArrayList<>();
            session.setCourses(course);
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // TAB LAYOUT
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Teacher_FragmentOne(), "F 1"); // Attendance
        adapter.addFragment(new Teacher_Fragment_Two_Two(), "Fr 2");
        adapter.addFragment(new FragmentThree(), "Fr 3");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            return null;
        }

    }

}
