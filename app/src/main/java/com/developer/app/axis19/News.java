package com.developer.app.axis19;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.relex.circleindicator.CircleIndicator;

public class News extends Fragment {

    View v;
    CustomPagerAdapter mCustomPagerAdapter;
    CustomViewPager mViewPager;
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

        return v;
    }
}
