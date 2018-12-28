package com.developer.app.axis19;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.developer.app.axis19.MainActivity.Email;
import static com.developer.app.axis19.MainActivity.axisid;
//import com.developer.app.axis19.UtilFunctions;


public class DatabaseHelper {

    private FirebaseDatabase mFirebaseDatabase;
    private  DatabaseReference mPushDatabaseReference, rootRef, fromRef, toRef;
    private UtilFunctions utilFunctions;
    private String key;
    public DatabaseHelper()
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = mFirebaseDatabase.getReference();
        utilFunctions = new UtilFunctions();

    }

    public void createUser(User user)
    {
        key = utilFunctions.getUser_key(Email);
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("users");
        mPushDatabaseReference.child(key).setValue(user);
    }
    public void registerUser (String category, String event_name) {

        final DatabaseReference fromPath, toPath;
        ValueEventListener valueEventListener;
        key = utilFunctions.getUser_key(Email);
        toPath = rootRef.child("Events").child(category).child(event_name).child("Registrations").child(key);
        fromPath = rootRef.child("users").child(key);
        toPath.setValue(axisid, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                if (firebaseError != null) {
                    System.out.println("Copy failed");
                } else {
                    System.out.println("Success"+axisid);

                }
            }
        });


        fromPath.child(category).child(event_name).setValue("Registered", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                if (firebaseError != null) {
                    System.out.println("Copy failed");
                } else {
                    System.out.println("Success"+ axisid+"$");

                }
            }
        });

    }

    public void teamRegister(String teamName, final ArrayList<String> emails, final ArrayList<String> axisids,
                             String category, String eventName)
    {
        final ArrayList <String> keys = new ArrayList<>();
        final DatabaseReference fromPath, toPath, userPath;
        ValueEventListener valueEventListener;
        toPath = rootRef.child("Events").child(category).child(eventName).child("Registrations");
        fromPath = rootRef.child("users");
        String axisId;
        for(int i=0; i < emails.size(); i++)
        {
            keys.add(utilFunctions.getUser_key(emails.get(i)));
        }
        /*
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(getContext(),"retrieving data",Toast.LENGTH_SHORT).show();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    if(keys.contains(ds.getKey()))
                    {

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        fromPath.addListenerForSingleValueEvent(valueEventListener);
        */
        for(int i=0;i < emails.size(); i++)
        {
            key = utilFunctions.getUser_key(emails.get(i));
            toPath.child(teamName).child(key).setValue(axisids.get(i), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                    if (firebaseError != null) {
                        System.out.println("Copy failed");
                    } else {
                        System.out.println("Success");
                    }
                }
            });


            fromPath.child(key).child(category).child(eventName).setValue("Registered", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                    if (firebaseError != null) {
                        System.out.println("Copy failed");
                    } else {
                        System.out.println("Success" + axisid + "$");

                    }
                }
            });
        }
    }
}
