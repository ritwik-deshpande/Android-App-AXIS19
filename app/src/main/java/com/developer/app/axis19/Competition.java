package com.developer.app.axis19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Competition extends Fragment {

    View v;
    private RecyclerView recyclerView;

    List<Event> lst ;

    DatabaseReference rootRef,imagesRef;
    ValueEventListener valueEventListener;
    
    
    public Competition(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lst  = new ArrayList<>();
        rootRef = FirebaseDatabase.getInstance().getReference();
        imagesRef = rootRef.child("Events").child("Competitions");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                        lst.add((ds.getValue(Event.class)));
                        Log.d("TAG","firebase created event object");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        imagesRef.addListenerForSingleValueEvent(valueEventListener);


    }

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.competitions_fragment,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.competition_recyclerview);

        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getContext(),lst);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(recyclerViewAdapter);

        return v;

    }
}