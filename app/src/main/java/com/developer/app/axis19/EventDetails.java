package com.developer.app.axis19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class EventDetails extends AppCompatActivity {

    private static final String TAG="EventDetails";
    private String[] months = {"January", "February", "March","April","May","June","July","August","September","October","November","December"};
    private DatabaseHelper db;

    String event_img_url;
    String eventName;
    String category;
    String OName1;
    String OName2;
    long phone1;
    long phone2;
    String eventDesc;
    Date date;
    String venue;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHelper();
        Log.d(TAG,"on Create called");


        AppCompatImageButton call_person_one = (AppCompatImageButton) findViewById(R.id.call_contact_person_one);
        AppCompatImageButton call_person_two = (AppCompatImageButton) findViewById(R.id.call_contact_person_two);
        AppCompatImageButton save_person_one = (AppCompatImageButton) findViewById(R.id.save_contact_person_one);
        AppCompatImageButton save_person_two = (AppCompatImageButton) findViewById(R.id.save_contact_person_two);
        ImageButton calendar=(ImageButton)findViewById(R.id.calender);
        ImageButton maps=(ImageButton)findViewById(R.id.map);
        AppCompatButton register_button = (AppCompatButton)findViewById(R.id.reg_button);

        getIncomingIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.share_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent msg_intent = new Intent(Intent.ACTION_SEND);
                msg_intent.putExtra(Intent.EXTRA_STREAM,loadBitmap(event_img_url));
                msg_intent.setType("image/*");
                msg_intent.putExtra(Intent.EXTRA_SUBJECT,"Invite to event");
                msg_intent.setType("text/plain");
                msg_intent.putExtra(Intent.EXTRA_TEXT,eventName+"\n"+eventDesc+"\n");
                msg_intent.setType("text/plain");
                startActivity(msg_intent);
//                Bitmap bitmap =getBitmapFromView(imageView);
//                try {
//                    File file = new File(getExternalCacheDir(),"logicchip.png");
//                    FileOutputStream fOut = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//                    fOut.flush();
//                    fOut.close();
//                    file.setReadable(true, false);
//                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra(Intent.EXTRA_TEXT, eventName);
//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//                    intent.setType("image/png");
//                    startActivity(Intent.createChooser(intent, "Share image via"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setVenue(venue);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCalendarEvent((Date) getIntent().getSerializableExtra("Date"),getIntent().getStringExtra("EventName"));
            }
        });

        save_person_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_contact(OName1, phone1);
            }
        });

        save_person_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_contact(OName2,phone2);
            }
        });
        call_person_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    makePhoneCall(phone1);
            }
        });
        call_person_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    makePhoneCall(phone2);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().hasExtra("category"))
                    db.registerUser(category,eventName) ;
                Toast.makeText(EventDetails.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public Bitmap loadBitmap(String url)
    {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try
        {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
    public void getIncomingIntent(){

        Log.d(TAG,"Checking for incoming Intent");
        if(getIntent().hasExtra("EventImage") && getIntent().hasExtra("EventName") && getIntent().hasExtra("OName1") && getIntent().hasExtra("OName2") && getIntent().hasExtra("EventDesc") ) {
            event_img_url = getIntent().getStringExtra("EventImage");
            eventName = getIntent().getStringExtra("EventName");
            category = getIntent().getStringExtra("category");
            OName1 = getIntent().getStringExtra("OName1");
            OName2 = getIntent().getStringExtra("OName2");
            phone1 = getIntent().getLongExtra("phone1",0);
            phone2 = getIntent().getLongExtra("phone2",0);
            eventDesc = getIntent().getStringExtra("EventDesc");

            date=(Date) getIntent().getSerializableExtra("Date");

            venue = getIntent().getStringExtra("Venue");

            Log.d("Tag","The date is:"+date);


            Log.d(TAG,"Our Event:\n"+eventName+"\n"+eventDesc+"\n"+OName1+"\n"+OName2+"\n" );
            setEvent(event_img_url, eventName, eventDesc, OName1, OName2, date, venue);
        }
    }

    public void setEvent(String event_img_url ,String eventName,String eventDesc,String OName1,String OName2,Date date,String venue){


        Log.d(TAG,"Setting our event");
        setTitle(eventName);
        Log.d(TAG,"Setting our eventName"+eventName);
//        assert textView1!=null;
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
        imageView=(ImageView)findViewById(R.id.event_img);

        AppCompatTextView textView5 =(AppCompatTextView)findViewById(R.id.date);
        Log.d("Tag","The date is:"+date);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);

        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        int year = calendar.get(Calendar.YEAR);

        textView5.setText(months[month]+" "+day+" ,"+year);

        AppCompatTextView textView6 =(AppCompatTextView)findViewById(R.id.venue);

        textView6.setText(venue);


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
