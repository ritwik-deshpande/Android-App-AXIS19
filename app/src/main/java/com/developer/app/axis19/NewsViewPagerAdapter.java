package com.developer.app.axis19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsViewPagerAdapter extends PagerAdapter {

    List<News_info> lst;
    Context context;
    private LayoutInflater mLayoutInflater;

    public NewsViewPagerAdapter(List<News_info> lst, Context context) {
        this.lst = lst;
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount()
    {
        Log.d("TAG","The size of news list is:"+lst.size());
        return lst.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = mLayoutInflater.inflate(R.layout.item_news, container, false);

        //\container.addView(v);

        ImageView newsimg=(ImageView)v.findViewById(R.id.news_image);
        //newsimg.setImageResource(lst.get(position).getImage());
        Glide.with(context).load(lst.get(position).getImage()).into(newsimg);
        TextView headLine = (TextView)v.findViewById(R.id.news_headline);
        headLine.setText(lst.get(position).getNews_head());

        TextView newscontent = (TextView)v.findViewById(R.id.news_content);
        newscontent.setText(lst.get(position).getNews_contennt());

        if(v.getParent() != null) {
            ((ViewGroup)v.getParent()).removeView(v); // <- fix
            container.addView(v);
        }



        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((LinearLayout) object);
    }
}
