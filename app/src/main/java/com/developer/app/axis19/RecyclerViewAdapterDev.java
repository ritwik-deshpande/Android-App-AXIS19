package com.developer.app.axis19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterDev extends RecyclerView.Adapter<RecyclerViewAdapterDev.MyViewHolder>{

    String TAG="RecyclerView";
    private ArrayList<Profile> profiles= new ArrayList<>();
    Context context;

    public RecyclerViewAdapterDev(ArrayList<Profile> profiles,Context context) {
        this.profiles = profiles;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.dev_item,viewGroup,false);

        MyViewHolder myViewHolder= new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Log.d(TAG,"Setting my View holder");
        Glide.with(context).asBitmap().load(profiles.get(i).getImage_url()).into(myViewHolder.image);
        myViewHolder.name.setText(profiles.get(i).getName());
        //myViewHolder.email.setText(profiles.get(i).getEmail());
        myViewHolder.post.setText(profiles.get(i).getPost());
        //myViewHolder.number.setText(profiles.get(i).getPhone_number());


    }

    @Override
    public int getItemCount() {
        Log.d("TAG","The size of list is"+profiles.size());
        return profiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView post;
        TextView email;
        TextView number;
        ImageButton call;
        ImageButton save ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(CircleImageView) itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            post=itemView.findViewById(R.id.year_dept);
            //email= itemView.findViewById(R.id.email);
            //number=itemView.findViewById(R.id.phone_number);
            //call = (ImageButton) itemView.findViewById(R.id.call);
            //save= (ImageButton) itemView.findViewById(R.id.save);
        }
    }

}
