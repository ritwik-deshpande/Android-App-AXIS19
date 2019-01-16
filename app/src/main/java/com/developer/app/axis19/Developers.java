package com.developer.app.axis19;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;

public class Developers extends AppCompatActivity {

    String TAG ="DevelopersActivity";
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterDev r;

    ArrayList<Profile> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
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

        runAnimation(0);

    }

    void initProfile(){
        lst.add(new Profile("Chaitya",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","3rd year CSE"));
        lst.add(new Profile("Ritwik",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","3rd year CSE"));
        lst.add(new Profile("Bhumesh",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","3rd year CSE"));
        lst.add(new Profile("Abdul",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","3rd year CSE"));
        initRecyclerView();
        //callandSave("Sathish","9820188402");

    }



    void initRecyclerView(){
        Log.d(TAG,"Inisde Init Recycler View"+lst.size());
        recyclerView= findViewById(R.id.dev_recyclerview);
        r=new RecyclerViewAdapterDev(lst,this);
        recyclerView.setAdapter(r);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

    }

    private void runAnimation(int type) {


        initProfile();
        Context context = recyclerView.getContext();
        LayoutAnimationController controller=null;

        // 0 denotes fall_down animation
        if(type==0){
            controller= AnimationUtils.loadLayoutAnimation(this,R.anim.item_falldown_animation);
        }


        //Set animations
        //Log.d("CoreTeam","Setting Animations");
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
}