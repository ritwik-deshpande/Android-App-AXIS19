package com.developer.app.axis19;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EventDetails extends AppCompatActivity {

    private static final String TAG="EventDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG,"on Create called");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        AppCompatImageButton call_person_one = (AppCompatImageButton) findViewById(R.id.call_contact_person_one);
        AppCompatImageButton call_person_two = (AppCompatImageButton) findViewById(R.id.call_contact_person_two);
        call_person_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().hasExtra("phone1"))
                    makePhoneCall(getIntent().getLongExtra("phone1",0));
            }
        });
        call_person_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().hasExtra("phone2"))
                makePhoneCall(getIntent().getLongExtra("phone2",0));
            }
        });
        getIncomingIntent();
    }
    public void getIncomingIntent(){

        Log.d(TAG,"Checking for incoming Intent");
        if(getIntent().hasExtra("EventImage") && getIntent().hasExtra("EventName") && getIntent().hasExtra("OName1") && getIntent().hasExtra("OName2") && getIntent().hasExtra("EventDesc") ) {
            int eventImg = getIntent().getStringExtra("EventImage");
            String eventName = getIntent().getStringExtra("EventName");

            String eventO1 = getIntent().getStringExtra("OName1");
            String eventO2 = getIntent().getStringExtra("OName2");

            String eventDesc = getIntent().getStringExtra("EventDesc");

            Log.d(TAG,"Our Event:\n"+eventName+"\n"+eventDesc+"\n"+eventO1+"\n"+eventO2+"\n" );
            setEvent(eventImg, eventName, eventDesc, eventO1, eventO2);
        }
    }

    public void setEvent(String event_img_url,String eventName,String eventDesc,String OName1,String OName2){

        Log.d(TAG,"Setting our event");
       // TextView textView1=(TextView)findViewById(R.id.event_name);
        setTitle(eventName);
        Log.d(TAG,"Setting our eventName"+eventName);
//        assert textView1!=null;
//        textView1.setText(eventName);
//
        AppCompatTextView textView2=(AppCompatTextView)findViewById(R.id.description_textView);
        assert textView2!=null;
        textView2.setText(eventDesc);
        AppCompatTextView textView3 =(AppCompatTextView) findViewById(R.id.contact_name_one);
        assert textView3!=null;
        AppCompatTextView textView4 =(AppCompatTextView)findViewById(R.id.contact_name_two);
        assert textView3!=null;
        textView3.setText(OName1);
        assert textView4!=null;
        textView4.setText(OName2);
        ImageView imageView=(ImageView)findViewById(R.id.event_img);

       // Glide.with(mContext).load(mResources[position]).fitCenter().into(imageView);
        Glide.with(this).load(event_img_url).fitCenter().into(imageView);

    }

    public void makePhoneCall(long phone_no)
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phone_no));
        if (ActivityCompat.checkSelfPermission(EventDetails.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            // permission not granted. Hence revoke permission
            ActivityCompat.requestPermissions(EventDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }
    private void setTitle(final String title) {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

}
