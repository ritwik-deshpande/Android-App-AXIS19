package com.developer.app.axis19;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Championships extends Fragment {
    View v;
    private RecyclerView recyclerView;

    List<Event> lst = new ArrayList<>() ;

    DatabaseReference rootRef,imagesRef;
    ValueEventListener valueEventListener;


    public Championships(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.championships_fragment,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.championships_recyclerview);


        // runAnimation(recyclerView,0);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //recyclerView.setAdapter(recyclerViewAdapter);
        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);


        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        // runAnimation(recyclerView,0);
        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {

        } else  {
            try {
                Snackbar.make(getActivity().findViewById(R.id.nav_view),"No Internet Connection",Snackbar.LENGTH_LONG).show();
            }catch (Exception e)
            {           }

            Toast.makeText(getActivity(),"Unable to fetch latest data",Toast.LENGTH_SHORT).show();

        }
        //Competition.FetchEventList fel = new Competition.FetchEventList();
        //fel.execute();
        lst.add(new Event("Who is the Boss","Guru","Chaitya","Apti","https://drive.google.com/open?id=1DRuWJ3BaLCoDDekxAA8sd-wWx1MW68mg",9868696,896869868,"Chem","10/2/2019"));
        lst.add(new Event("Who is the Boss","Guru","Chaitya","Apti","https://drive.google.com/open?id=1DRuWJ3BaLCoDDekxAA8sd-wWx1MW68mg",8708798,696986976,"Chem","10/2/2019"));
        lst.add(new Event("Who is the Boss","Guru","Chaitya","Apti","https://drive.google.com/open?id=1DRuWJ3BaLCoDDekxAA8sd-wWx1MW68mg",86969686,869676768,"Chem","10/2/2019"));
        lst.add(new Event("Who is the Boss","Guru","Chaitya","Apti","https://drive.google.com/open?id=1DRuWJ3BaLCoDDekxAA8sd-wWx1MW68mg",86986869,87979758,"Chem","10/2/2019"));
        lst.add(new Event("Who is the Boss","Guru","Chaitya","Apti","https://drive.google.com/open?id=1DRuWJ3BaLCoDDekxAA8sd-wWx1MW68mg",790709709,97097097,"Chem","10/2/2019"));

        updateUI();

        return v;

    }

    private void runAnimation(RecyclerView recyclerView, int type) {

        Context context = recyclerView.getContext();
        LayoutAnimationController controller=null;

        // 0 denotes fall_down animation
        if(type==0){
            controller= AnimationUtils.loadLayoutAnimation(context,R.anim.item_falldown_animation);

        }

        recyclerView.setLayoutAnimation(controller);
        //recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
    public void updateUI()
    {
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getContext(),lst);
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
