package com.developer.app.axis19;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import java.util.List;

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

    private DatabaseHelper db;
    private UtilFunctions utilFunctions;

    public static int valid = 0;
    public static String Email, name, axisid;
    TextView navDrawerUsername, navDrawerUseremailid;
    private static final String TAG = "MainActivity";

    private static final int RC_SIGN_IN = 123;
    private boolean isNewUser ;

    Dialog settingsDialog;
    FloatingActionButton fab_share,fab_fb,fab_insta;
    Animation fab_open,fab_close,fab_clock,fab_anticlock;
    Boolean isOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_share = (FloatingActionButton)findViewById(R.id.fab_share);
        fab_fb = (FloatingActionButton)findViewById(R.id.fab_fb);
        fab_insta = (FloatingActionButton)findViewById(R.id.fab_insta);

        fab_open = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        fab_clock = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise);
        fab_anticlock = AnimationUtils.loadAnimation(this,R.anim.rotate_anticlockwise);
        isOpened = false;

        settingsDialog = new Dialog(this);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.signout_dialog
                , null));

        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpened){
                    fab_fb.startAnimation(fab_close);
                    fab_fb.setClickable(false);
                    fab_insta.startAnimation(fab_close);
                    fab_insta.setClickable(false);
                    fab_share.startAnimation(fab_anticlock);
                    isOpened=false;
                }
                else
                {
                    fab_fb.startAnimation(fab_open);
                    fab_fb.setClickable(true);
                    fab_insta.startAnimation(fab_open);
                    fab_insta.setClickable(true);
                    fab_share.startAnimation(fab_clock);
                    isOpened=true;

                    fab_fb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.facebook.com/axisvnit";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                    fab_insta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.instagram.com/axis_vnit/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        utilFunctions = new UtilFunctions();
        db = new DatabaseHelper();

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

        onauthchange();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adding Fragments

        viewPagerAdapter.addFragment(new News(), "News and More");
        viewPagerAdapter.addFragment(new guestlectures(), "Featured");
        viewPagerAdapter.addFragment(new Competition(), "Competitions");
        viewPagerAdapter.addFragment(new Challenges(), "Challenges");
        viewPagerAdapter.addFragment(new Championships(), "Championships");
        viewPagerAdapter.addFragment(new Analytics(), "Analytics");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void onauthchange()
    {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                settingsDialog.show();
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Email = user.getEmail();
                    name = user.getDisplayName();

                    mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        String key = utilFunctions.getUser_key(Email);
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            isNewUser = true;
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String x = (String) snapshot.getKey();
                                if (key.equals(x)) {
                                    Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                                    isNewUser = false;
                                    axisid = (String) snapshot.child("axisid").getValue();
                                    if(snapshot.child("phone").getValue().equals("NULL"))
                                    {
                                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                        startActivity(i);
                                    }
                                    break;
                                }
                            }
                            if(isNewUser)
                            {
                                Toast.makeText(MainActivity.this, "Welcome new user!", Toast.LENGTH_SHORT).show();
                                axisid = utilFunctions.generate_axisid();
                                User user = new User(name,Email,axisid);
                                //mPushDatabaseReference.child(key).setValue(user);
                                db.createUser(user);
                                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                i.putExtra("username" , name);
                                i.putExtra("email" , Email);
                                i.putExtra("axisid", axisid);
                                startActivity(i);

                            }
                            navDrawerUsername.setText((String) user.getDisplayName());
                            navDrawerUseremailid.setText(axisid);
                            settingsDialog.dismiss();

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                   // navDrawerUsername.setText((String) user.getDisplayName());
                   // navDrawerUseremailid.setText((String) user.getEmail());


                } else {
                    // User is signed out


                    Toast.makeText(MainActivity.this, "user signed out", Toast.LENGTH_SHORT).show();
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.GoogleBuilder().build());
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setTheme(R.style.LoginTheme)
                                    .setLogo(R.drawable.axis_logo)
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .setLogo(R.drawable.nav_header)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


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
               // navDrawerUseremailid.setText((String) user.getEmail());
                navDrawerUseremailid.setText(axisid);
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
            Context c=this;
            Class cls=c.getClass();
            if(cls!=MainActivity.class)
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }


        } else if (id == R.id.myRegistrations_menuItem) {
            Intent i = new Intent(this, MyRegistration.class);
            startActivity(i);

        } else if (id == R.id.sponsors_menuItem) {

        } else if (id == R.id.core_menuItem) {
            Intent i = new Intent(MainActivity.this, coreteam.class);
            startActivity(i);


        } else if (id == R.id.developers_menuItem) {

            Intent i = new Intent(MainActivity.this, Developers.class);
            startActivity(i);

        } else if (id == R.id.sign_out) {

            settingsDialog.show();

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


}

