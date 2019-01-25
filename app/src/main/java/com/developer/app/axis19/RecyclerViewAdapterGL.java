package com.developer.app.axis19;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

public class RecyclerViewAdapterGL extends RecyclerView.Adapter<RecyclerViewAdapterGL.MyViewHolder>{

    Context context;
    private String[] months = {"January", "February", "March","April","May","June","July","August","September","October","November","December"};
    List<Event> list;

    public RecyclerViewAdapterGL(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
        Log.d("RVA","The date of event is:"+list.get(0).getDate());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_gl,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        String [] gl_info = list.get(i).getEventName().split(",");
        myViewHolder.guestName.setText(gl_info[0]);
        myViewHolder.guestTitle.setText(gl_info[1]);


        //Setting Date
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(list.get(i).getDate());

        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        int year = calendar.get(Calendar.YEAR);

        myViewHolder.date.setText(months[month]+" "+day+" ,"+year);

        myViewHolder.eventImg.setImageResource(R.drawable.jh);




        String img = list.get(i).getImg_url();

        Glide.with(context).load(img).into(myViewHolder.eventImg);
       // Glide.with(context).load(img).into(myViewHolder.eventImg);
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,list.get(i).getEventName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,EventDetails.class);
                intent.putExtra("EventImage",list.get(i).getImg_url());
                intent.putExtra("EventName",list.get(i).getEventName());
                intent.putExtra("OName1",list.get(i).getOName1());
                intent.putExtra("OName2",list.get(i).getOName2());
                intent.putExtra("EventDesc",list.get(i).getDesc());
                intent.putExtra("phone1", list.get(i).getPhone1());
                intent.putExtra("phone2", list.get(i).getPhone2());
                //Log.d("tag","The date of event is:"+list.get(i).getDate());
                intent.putExtra("category",list.get(i).getCategory());
                intent.putExtra("Date",list.get(i).getDate());
                intent.putExtra("Venue",list.get(i).getVenue());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView guestName;
        private TextView guestTitle;
        private TextView date;
        private ImageView eventImg;
        private LinearLayout parentLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            guestName = (TextView)itemView.findViewById(R.id.gl_name);
            guestTitle= (TextView)itemView.findViewById(R.id.gl_title);
            date = (TextView)itemView.findViewById(R.id.gl_date);
            eventImg=(ImageView)itemView.findViewById(R.id.gl_img);
            parentLayout=(LinearLayout)itemView.findViewById(R.id.parent_layout);
        }
    }
}

