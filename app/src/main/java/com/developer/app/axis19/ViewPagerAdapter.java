package com.developer.app.axis19;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<Fragment>();

    private final List<String> lstTitles = new ArrayList<String>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lstFragment.get(i);
    }

    @Override
    public int getCount() {
        return lstTitles.size();
    }
    @Override
    public CharSequence getPageTitle(int i) {
        return lstTitles.get(i);
    }


     public void addFragment(Fragment fm,String s){

        lstFragment.add(fm);
        lstTitles.add(s);

     }
}
