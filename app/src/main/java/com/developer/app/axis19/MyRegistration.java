package com.developer.app.axis19;

import android.content.Context;
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

import java.util.ArrayList;

public class MyRegistration extends AppCompatActivity {

    String TAG ="MyRegistrationActivity";
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterRegistration r;

    ArrayList<Event> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        runAnimation(0);

    }

    void initMyReg(){
        String img_url;
        lst.add(new Event("Insomnia","Kapeel","Sanika","Coding Event","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUq0PqbjaMU2LTh5HaeKvGJgYs5UR1AQtkxtOehqCU3t22amRg",9868696,89868878));
        lst.add(new Event("Insomnia","Kapeel","Sanika","Coding Event","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUq0PqbjaMU2LTh5HaeKvGJgYs5UR1AQtkxtOehqCU3t22amRg",9868696,89868878));
        lst.add(new Event("Insomnia","Kapeel","Sanika","Coding Event","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUq0PqbjaMU2LTh5HaeKvGJgYs5UR1AQtkxtOehqCU3t22amRg",9868696,89868878));
        initRecyclerView();

    }



    void initRecyclerView(){
        Log.d(TAG,"Inisde Init Recycler View");
        recyclerView = findViewById(R.id.myreg);
        r=new RecyclerViewAdapterRegistration(lst,this);
        recyclerView.setAdapter(r);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

    }

    private void runAnimation(int type) {


        initMyReg();
        Context context = recyclerView.getContext();
        LayoutAnimationController controller=null;

        // 0 denotes fall_down animation
        if(type==0){
            controller= AnimationUtils.loadLayoutAnimation(this,R.anim.item_falldown_animation);
        }


        //Set animations
        Log.d("My Registrations","Setting Animations");
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
}
