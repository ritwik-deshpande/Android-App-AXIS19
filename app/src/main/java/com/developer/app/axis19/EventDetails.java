package com.developer.app.axis19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class EventDetails extends AppCompatActivity {

    private static final String TAG="EventDetails";
    private String[] months = {"January", "February", "March","April","May","June","July","August","September","October","November","December"};

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
        AppCompatImageButton save_person_one = (AppCompatImageButton) findViewById(R.id.save_contact_person_one);
        AppCompatImageButton save_person_two = (AppCompatImageButton) findViewById(R.id.save_contact_person_two);
        ImageButton calendar=(ImageButton)findViewById(R.id.calender);
        ImageButton maps=(ImageButton)findViewById(R.id.map);

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().hasExtra("OName1") && getIntent().hasExtra("phone1"))
                    setVenue(getIntent().getStringExtra("Venue"));

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().hasExtra("OName1") && getIntent().hasExtra("phone1"))
                    setCalendarEvent((Date) getIntent().getSerializableExtra("Date"),getIntent().getStringExtra("EventName"));
            }
        });

        save_person_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().hasExtra("OName1") && getIntent().hasExtra("phone1"))
                    save_contact(getIntent().getStringExtra("OName1"),getIntent().getLongExtra("phone1",0));
            }
        });

        save_person_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().hasExtra("OName2") && getIntent().hasExtra("phone2"))
                    save_contact(getIntent().getStringExtra("OName2"),getIntent().getLongExtra("phone2",0));
            }
        });
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
        if(getIntent().hasExtra("EventName") && getIntent().hasExtra("EventName") && getIntent().hasExtra("OName1") && getIntent().hasExtra("OName2") && getIntent().hasExtra("EventDesc") ) {
            String event_img_url = getIntent().getStringExtra("EventImage");
            String eventName = getIntent().getStringExtra("EventName");

            String eventO1 = getIntent().getStringExtra("OName1");
            String eventO2 = getIntent().getStringExtra("OName2");

            String eventDesc = getIntent().getStringExtra("EventDesc");

            Date date=(Date) getIntent().getSerializableExtra("Date");

            String venue = getIntent().getStringExtra("Venue");

            Log.d("Tag","The date is:"+date);


            Log.d(TAG,"Our Event:\n"+eventName+"\n"+eventDesc+"\n"+eventO1+"\n"+eventO2+"\n" );
            setEvent(event_img_url, eventName, eventDesc, eventO1, eventO2,date,venue);
        }
    }

    public void setEvent(String event_img_url ,String eventName,String eventDesc,String OName1,String OName2,Date date,String venue){


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

        AppCompatTextView textView5 =(AppCompatTextView)findViewById(R.id.date);
        Log.d("Tag","The date is:"+date);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);

        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        int year = calendar.get(Calendar.YEAR);

        textView5.setText(months[month-1]+" "+day+" ,"+year);

        AppCompatTextView textView6 =(AppCompatTextView)findViewById(R.id.venue);

        textView6.setText(venue);

        AppCompatButton reg_button=(AppCompatButton)findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventDetails.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });

       // Glide.with(mContext).load(mResources[position]).fitCenter().into(imageView);
        Glide.with(this).load(event_img_url).into(imageView);

    }
    public void setCalendarEvent(Date date,String name){

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, name);
        intent.putExtra(CalendarContract.Events.ALL_DAY, true);
        intent.putExtra(
                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                cal.getTime().getTime());
        startActivity(intent);

    }

    void setVenue(String venue){

        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+Uri.encode(venue));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

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

    public void save_contact (String name, long phone_no){
        Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name );
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "" + phone_no);
        startActivity(intent);

    }
    private void setTitle(final String title) {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

}
