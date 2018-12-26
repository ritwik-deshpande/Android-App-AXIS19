package com.developer.app.axis19;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterRegistration extends  RecyclerView.Adapter<RecyclerViewAdapterRegistration.MyViewHolder>{

    String TAG="RecyclerView";
    private ArrayList<Event> events= new ArrayList<>();
    Context context;

    public RecyclerViewAdapterRegistration(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRegistration.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_myreg,viewGroup,false);

        MyViewHolder myViewHolder= new RecyclerViewAdapterRegistration.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterRegistration.MyViewHolder myViewHolder, final int i) {
        Log.d(TAG,"Setting my View holder");
        Glide.with(context).asBitmap().load(events.get(i).getImg_url()).into(myViewHolder.image);
        myViewHolder.name.setText(events.get(i).getEventName());
        myViewHolder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,events.get(i).getEventName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,EventDetails.class);
                intent.putExtra("EventImage",events.get(i).getImg_url());
                intent.putExtra("EventName",events.get(i).getEventName());
                intent.putExtra("OName1",events.get(i).getOName1());
                intent.putExtra("OName2",events.get(i).getOName2());
                intent.putExtra("EventDesc",events.get(i).getDesc());
                intent.putExtra("phone1", events.get(i).getPhone1());
                intent.putExtra("phone2", events.get(i).getPhone2());
                //Log.d("tag","The date of event is:"+list.get(i).getDate());

                intent.putExtra("Date",events.get(i).getDate());
                intent.putExtra("Venue",events.get(i).getVenue());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        AppCompatTextView name;
        AppCompatImageButton viewdetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(CircleImageView) itemView.findViewById(R.id.dp);
            name=itemView.findViewById(R.id.name);
            viewdetails=itemView.findViewById(R.id.reg_button);
        }
    }
}
