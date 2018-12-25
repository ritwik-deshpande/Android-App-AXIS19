package com.developer.app.axis19;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}
