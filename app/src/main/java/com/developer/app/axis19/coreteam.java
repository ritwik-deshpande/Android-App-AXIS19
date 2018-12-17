package com.developer.app.axis19;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class coreteam extends AppCompatActivity {

    String TAG ="CoreTeamActivity";
    View v;
    private RecyclerView recyclerView;

    ArrayList<Profile> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coreteam);
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

        initProfile();
    }

    void initProfile(){
        lst.add(new Profile("Sathish",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","Core-Coordinator"));
        lst.add(new Profile("Sathish",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","Publicity Incharge"));
        lst.add(new Profile("Sathish",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","Core-Coordinator"));
        lst.add(new Profile("Sathish",R.drawable.sathish,"ritwikdeshpande01@gmail.com","9820188402","Treasurer"));
        initRecyclerView();
        //callandSave("Sathish","9820188402");

    }



    void initRecyclerView(){
        Log.d(TAG,"Inisde Init Recycler View");
        RecyclerView recyclerView= findViewById(R.id.ct_recyclerview);
        RecycleViewAdapterTeam r=new RecycleViewAdapterTeam(lst,this);
        recyclerView.setAdapter(r);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

    }
}
