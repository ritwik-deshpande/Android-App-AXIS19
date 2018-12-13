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

public class Competition extends Fragment {

    View v;
    private RecyclerView recyclerView;

    List<Event> lst ;

    public Competition(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lst= new ArrayList<>();

        lst.add(new Event("Electroblitz","Kapeel","Sanika","Coding Event",R.drawable.event_1));
        lst.add(new Event("Insomnia","Kapeel","Sanika","Coding Event",R.drawable.event_2));
        lst.add(new Event("Cryptocrux","Kapeel","Sanika","Coding Event",R.drawable.event_3));
        lst.add(new Event("Pasterolic","Kapeel","Sanika","Coding Event",R.drawable.event_4));
        lst.add(new Event("Paradigma","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));
        lst.add(new Event("Turboflux","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));
        lst.add(new Event("Aquahunt","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));
        lst.add(new Event("Aquahunt","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));
        lst.add(new Event("Autobot","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));
        lst.add(new Event("Crepido","Kapeel","Sanika","Coding Event",R.drawable.viewpager_3));

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
