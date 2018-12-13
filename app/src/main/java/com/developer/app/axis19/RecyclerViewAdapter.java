package com.developer.app.axis19;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    List<Event> list;

    public RecyclerViewAdapter(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_event,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.eventName.setText(list.get(i).getEventName());
        myViewHolder.eventImg.setImageResource(list.get(i).getImage());
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,list.get(i).getEventName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,EventDetails.class);
                intent.putExtra("EventImage",list.get(i).getImage());
                intent.putExtra("EventName",list.get(i).getEventName());
                intent.putExtra("OName1",list.get(i).getOName1());
                intent.putExtra("OName2",list.get(i).getOName2());
                intent.putExtra("EventDesc",list.get(i).getDesc());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView eventName;
        private ImageView eventImg;
        private CardView parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventImg=(ImageView)itemView.findViewById(R.id.event_img);
            parentLayout=(CardView)itemView.findViewById(R.id.parent_layout);
        }
    }
}
