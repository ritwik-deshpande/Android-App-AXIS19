package com.developer.app.axis19;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class News extends Fragment {

    View v;
    CustomPagerAdapter mCustomPagerAdapter;
    CustomViewPager mViewPager;

    ViewPager viewPager;

    NewsViewPagerAdapter newsViewPagerAdapter;

    List<News_info> lst =new ArrayList<News_info>();

    DatabaseReference rootRef,imagesRef;
    ValueEventListener valueEventListener;

    public News(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.news_fragment,container,false);

        Context c=v.getContext();
        mCustomPagerAdapter = new CustomPagerAdapter(c);
        mViewPager = (CustomViewPager) v.findViewById(R.id.viewpager_main);
        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator);
        mViewPager.setAdapter(mCustomPagerAdapter);
        indicator.setViewPager(mViewPager);

        final Handler h = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            public void run() {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem()+1)%3, true);
                h.postDelayed(this, 3000);
            }
        };
        h.postDelayed(r, 3000);

        viewPager=(ViewPager)v.findViewById(R.id.news_viewpager);


        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {

        } else  {
            try {
                Snackbar.make(getActivity().findViewById(R.id.nav_view),"No Internet Connection",Snackbar.LENGTH_LONG).show();
            }catch (Exception e)
            {           }

            Toast.makeText(getActivity(),"Unable to fetch latest data",Toast.LENGTH_SHORT).show();

        }
        News.FetchEventList fel = new News.FetchEventList();
        fel.execute();

        return v;
    }


    private class ViewPagerStack implements ViewPager.PageTransformer {
        @Override
        public void transformPage(@NonNull View page, float position) {
            if(position>=0){
                page.setScaleX(1-0.08f*position);
                page.setScaleY(1);
                page.setTranslationX(-page.getWidth()*position);
                page.setTranslationY(-30*position);
            }
        }
    }
    public void updateUI()
    {
        newsViewPagerAdapter=new NewsViewPagerAdapter(lst,getContext());

        viewPager.setPageTransformer(true,new ViewPagerStack());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(newsViewPagerAdapter);

    }
    public class FetchEventList extends AsyncTask<Void,Void,ArrayList<Event>> {

        @Override
        protected void onPreExecute() {
            //bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Event> doInBackground(Void... params) {

            lst.clear();
            rootRef = FirebaseDatabase.getInstance().getReference();
            imagesRef = rootRef.child("News");
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Toast.makeText(getContext(),"retrieving data",Toast.LENGTH_SHORT).show();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {

                        lst.add((ds.getValue(News_info.class)));
                        Log.d("TAG","firebase created event object");
                    }
                    //Log.d("Size of list is ","size=" + ((Integer) lst.size()).toString());

                    //runAnimation(recyclerView,0);
                    updateUI();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            imagesRef.addListenerForSingleValueEvent(valueEventListener);
            return null;
        }
    }
}
