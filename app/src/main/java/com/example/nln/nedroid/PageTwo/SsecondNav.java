package com.example.nln.nedroid.PageTwo;

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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.NavigationMenu.Attendance1;
import com.example.nln.nedroid.NavigationMenu.Profile;
import com.example.nln.nedroid.Notification.NotificationNav;
import com.example.nln.nedroid.PageOne.FirstNav;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Setting1;

import java.util.ArrayList;
import java.util.List;

public class SsecondNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView headerName,headerID;
    private int[] tabIcons = {
            R.drawable.ic_assignment,
            R.drawable.ic_books,
            R.drawable.ic_lecture,
            R.drawable.ic_result
    };

    public String Name_DB;//for all app
    public String ID_DB;//for all app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssecond_nav);
        toolbar = (Toolbar) findViewById(R.id.toolbar__nav);
        setSupportActionBar(toolbar);
        setTitle("Subject");

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

//        Tab Layout
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        String log = "Name: " + Name_DB + " ,ID: " + ID_DB;
        Log.d("Start: ", log);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    // TAB LAYOUT
    private void setupViewPager(ViewPager viewPager) {
        SsecondNav.ViewPagerAdapter adapter = new SsecondNav.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentLecture(), "F 1");
        adapter.addFragment(new FragmentAssignment(), "Fr 3");
        adapter.addFragment(new FragmentBook(), "Fr 4");
        adapter.addFragment(new FragmentResult(), "Fr 5");
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SsecondNav.this, FirstNav.class);
        i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
        i.putExtra("USERID", ID_DB);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(SsecondNav.this, FirstNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);


        } if (id == R.id.nav_profile) {
            Intent i = new Intent(SsecondNav.this, Profile.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_noticeboard) {
            Intent i = new Intent(SsecondNav.this, NotificationNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_attndance) {
            Intent i = new Intent(SsecondNav.this, Attendance1.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(SsecondNav.this, Setting1.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Toast.makeText(SsecondNav.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
