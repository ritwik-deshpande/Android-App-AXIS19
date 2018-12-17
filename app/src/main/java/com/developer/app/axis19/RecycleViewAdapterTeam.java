package com.developer.app.axis19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapterTeam extends RecyclerView.Adapter<RecycleViewAdapterTeam.MyViewHolder>{

    String TAG="RecyclerView";
    private ArrayList<Profile> profiles= new ArrayList<>();
    Context context;

    public RecycleViewAdapterTeam(ArrayList<Profile> profiles,Context context) {
        this.profiles = profiles;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.profile_item,viewGroup,false);

        MyViewHolder myViewHolder= new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Log.d(TAG,"Setting my View holder");
        Glide.with(context).asBitmap().load(profiles.get(i).getImage_url()).into(myViewHolder.image);
        myViewHolder.name.setText(profiles.get(i).getName());
        myViewHolder.email.setText(profiles.get(i).getEmail());
        myViewHolder.post.setText(profiles.get(i).getPost());
        myViewHolder.number.setText(profiles.get(i).getPhone_number());

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView post;
        TextView email;
        TextView number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(CircleImageView) itemView.findViewById(R.id.dp);
            name=itemView.findViewById(R.id.name);
            post=itemView.findViewById(R.id.post);
            email= itemView.findViewById(R.id.email);
            number=itemView.findViewById(R.id.phone_number);
        }
    }

}
