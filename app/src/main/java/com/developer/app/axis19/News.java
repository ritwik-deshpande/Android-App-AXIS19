package com.developer.app.axis19;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class News extends Fragment {

    View v;
    CustomPagerAdapter mCustomPagerAdapter;
    CustomViewPager mViewPager;

    ViewPager viewPager;

    NewsViewPagerAdapter newsViewPagerAdapter;

    List<News_info> lst =new ArrayList<News_info>();;



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


        initNews();
        newsViewPagerAdapter=new NewsViewPagerAdapter(lst,getContext());

        viewPager.setPageTransformer(true,new ViewPagerStack());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(newsViewPagerAdapter);

        return v;
    }

    void initNews(){


        lst.add(new News_info(R.drawable.news_image,"Axis 19 is here!!!","Axis begins on 22nd February with the curtain raiser event that is the talk show of Nitin Gadkari. Enjoy us and expand your horizon."));
        lst.add(new News_info(R.drawable.news_image,"Axis 19 is here!!!","Axis begins on 22nd February with the curtain raiser event that is the talk show of Nitin Gadkari. Enjoy us and expand your horizon."));
        lst.add(new News_info(R.drawable.event_2,"Axis 19 is here!!!","Axis begins on 22nd February with the curtain raiser event that is the talk show of Nitin Gadkari. Enjoy us and expand your horizon."));
        lst.add(new News_info(R.drawable.event_2,"Axis 19 is here!!!","Axis begins on 22nd February with the curtain raiser event that is the talk show of Nitin Gadkari. Enjoy us and expand your horizon."));

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
}
