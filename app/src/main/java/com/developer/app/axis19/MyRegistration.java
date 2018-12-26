package com.developer.app.axis19;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.developer.app.axis19.MainActivity.Email;
import static java.security.AccessController.getContext;


public class MyRegistration extends AppCompatActivity {

    String TAG ="MyRegistrationActivity";
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterRegistration r;

    ArrayList<Event> lst = new ArrayList<>();
    ArrayList<String> eventName = new ArrayList<String>();

    DatabaseReference rootRef,usersRef,eventRef;
    ValueEventListener valueEventListener,valueEventListener1;

    UtilFunctions utilFunctions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_registration);
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

        utilFunctions = new UtilFunctions();
        Log.d(TAG,"Inisde Init Recycler View");
        recyclerView = findViewById(R.id.myreg);
        //recyclerView.setHasFixedSize(true);
        r=new RecyclerViewAdapterRegistration(lst,this);
        recyclerView.setAdapter(r);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users").child(utilFunctions.getUser_key(Email)).child("Competitions");

        MyRegistration.FetchEventList fel = new MyRegistration.FetchEventList();
        fel.execute();
    }

    public void updateUI(){

        r = new RecyclerViewAdapterRegistration(lst,this);
        recyclerView.setAdapter(r);
        //recyclerView.scrollToPosition(0);
        //pg.setVisibility(View.GONE);
    }


    public class FetchEventList extends AsyncTask<Void,Void,ArrayList<Event>> {

        @Override
        protected void onPreExecute() {
            //bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Event> doInBackground(Void... params) {

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Toast.makeText(getContext(),"retrieving data",Toast.LENGTH_SHORT).show();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        eventName.add(ds.getKey());
                    }
                    //initRecyclerView();
                    System.out.println(eventName);

                    eventRef = rootRef.child("Events").child("Competitions");
                    System.out.println(eventName);
                    eventRef.addListenerForSingleValueEvent(valueEventListener1 = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    //Toast.makeText(getContext(),"retrieving data",Toast.LENGTH_SHORT).show();
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                        if (eventName.contains(ds.getKey())) {
                                            lst.add(ds.getValue(Event.class));
                                            System.out.println(ds.getKey());
                                        }
                                        System.out.println(ds.getKey());
                                    }
                                    //initRecyclerView();
                                    System.out.println(lst);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            }

                    );
                    updateUI();
                }

                    //LayoutAnimationController controller=null;

                    // 0 denotes fall_down animation
                    //controller= AnimationUtils.loadLayoutAnimation(MyRegistration.this,R.anim.item_falldown_animation);


                    //Set animations
                    //Log.d("My Registrations","Setting Animations");
                    //recyclerView.setLayoutAnimation(controller);
                    //recyclerView.getAdapter().notifyDataSetChanged();
                    //recyclerView.scheduleLayoutAnimation();

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            usersRef.addListenerForSingleValueEvent(valueEventListener);
            return null;
        }
    }
}
