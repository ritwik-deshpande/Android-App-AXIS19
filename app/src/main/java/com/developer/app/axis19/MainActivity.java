package com.developer.app.axis19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

//import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser user;

    SharedPreferences.Editor sp, spa;
    SharedPreferences userInfo;
    SharedPreferences firstTime;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener mValueEventListener;
    private DatabaseReference mPushDatabaseReference;

    public static int valid = 0;
    public static String Email, name;
    TextView navDrawerUsername, navDrawerUseremailid;
    private static final String TAG = "MainActivity";

    private static final int RC_SIGN_IN = 123;
    private boolean isNewUser ;

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


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("users");

        userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        sp = userInfo.edit();
        firstTime = getSharedPreferences("firstTime", Context.MODE_PRIVATE);

        mFirebaseAuth = FirebaseAuth.getInstance();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        View headerview = navigationView.getHeaderView(0);
        navDrawerUsername = (TextView) headerview.findViewById(R.id.UserName);
        navDrawerUseremailid = (TextView) headerview.findViewById(R.id.AxisId);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Email = user.getEmail();
                    name = user.getDisplayName();
                    mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        String key = getUser_key(Email);
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            isNewUser = true;
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String x = (String) snapshot.getKey();
                                if (key.equals(x)) {
                                    Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                                    isNewUser = false;
                                    if((long)snapshot.child("phone").getValue() == -1)
                                    {
                                        //Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                        //startActivity(i);
                                    }
                                    break;
                                }
                            }
                            if(isNewUser)
                            {
                                Toast.makeText(MainActivity.this, "Welcome new user!", Toast.LENGTH_SHORT).show();
                                User user = new User(name,Email,generate_axisid());
                                mPushDatabaseReference.child(key).setValue(user);
                                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(i);

                            }

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    // Code to save userdata
                    Log.v("Userdetails", user.getDisplayName() + " " + user.getEmail());
                    sp.putString("name", user.getDisplayName());
                    sp.putString("email", user.getEmail());
                    sp.putString("profile", user.getPhotoUrl().toString());
                    sp.putString("UID", user.getUid());
                    navDrawerUsername.setText((String) user.getDisplayName());
                    navDrawerUseremailid.setText((String) user.getEmail());

                    sp.commit();



                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "User signed out", Toast.LENGTH_SHORT).show();
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.GoogleBuilder().build());
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .setLogo(R.drawable.nav_header)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adding Fragments

        viewPagerAdapter.addFragment(new News(), "News and More");
        viewPagerAdapter.addFragment(new Competition(), "Competitions");
        viewPagerAdapter.addFragment(new guestlectures(), "Guest Lectures");
        viewPagerAdapter.addFragment(new Informals(), "Informals");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                navDrawerUsername.setText((String) user.getDisplayName());
                navDrawerUseremailid.setText((String) user.getEmail());
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
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
        Uri uri = null;
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
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.myRegistrations_menuItem) {

        } else if (id == R.id.sponsors_menuItem) {

        } else if (id == R.id.core_menuItem) {
            Intent i = new Intent(MainActivity.this, coreteam.class);
            startActivity(i);


        } else if (id == R.id.developers_menuItem) {

        } else if (id == R.id.sign_out) {

            navDrawerUsername.setText("");
            navDrawerUseremailid.setText("");
            AuthUI.getInstance()
                    .signOut(MainActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {

                            valid=0;
                            navigationView.getMenu().getItem(0).setChecked(true);
                        }
                    });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    public String getUser_key(String email)
    {
        email = email.substring(0,email.indexOf('@'));
        email.replaceAll(".","");
        return email;
    }

    public String generate_axisid()
    {
        Random rand = new Random();
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        String t = today.toString();
        t = t.split("")[3];
        long milisecond = today.getTime()%1000;
        long r = (rand.nextInt(999)+100)%1000;
        long id = ((long)Math.pow(r,2) +(long) Math.pow(milisecond,2) ) %1000;
        String axisid = "AXIS19" + t + ((id < 100)?'0': ((id<10)?"00":"") )+ id;
        return axisid;
    }
}

