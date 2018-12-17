package com.developer.app.axis19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        tabLayout=(TabLayout)findViewById(R.id.tabLayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        // Adding Fragments

        viewPagerAdapter.addFragment(new News(),"News and More");
        viewPagerAdapter.addFragment(new Competition(),"Competitions");
        viewPagerAdapter.addFragment(new guestlectures(),"Guest Lectures");
        viewPagerAdapter.addFragment(new Informals(),"Informals");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Uri uri=null;
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //case R.id.follow_us:
            //  return true;
            //case R.id.menu_visit_website:
            //  uri = Uri.parse(getResources().getString(R.string.matrix_website));
            //break;
            case R.id.menu_follow_fb:
                uri = Uri.parse(getResources().getString(R.string.axis_fb_link));
                break;
            // case R.id.menu_follow_twitter:
            //   uri = Uri.parse(getResources().getString(R.string.matrix_twit_link));
            // break;
            case R.id.menu_follow_instagram:
                uri = Uri.parse(getResources().getString(R.string.axis_insta_link));
                break;

            //case R.id.menu_sign_out:

        }

        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homepage_menuItem) {
            // Handle the camera action
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        } else if (id == R.id.myRegistrations_menuItem) {

        } else if (id == R.id.sponsors_menuItem) {

        } else if (id == R.id.core_menuItem) {
            Intent i = new Intent(MainActivity.this,coreteam.class);
            startActivity(i);


        } else if (id == R.id.developers_menuItem) {

        } else if (id == R.id.sign_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
