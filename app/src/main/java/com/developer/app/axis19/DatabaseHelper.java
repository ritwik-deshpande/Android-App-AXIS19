package com.developer.app.axis19;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.developer.app.axis19.MainActivity.Email;
//import com.developer.app.axis19.UtilFunctions;


public class DatabaseHelper {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mPushDatabaseReference;
    UtilFunctions utilFunctions;

    public DatabaseHelper()
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        utilFunctions = new UtilFunctions();
    }

    public void createUser(User user)
    {
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("users");
        String key = utilFunctions.getUser_key(Email);
        mPushDatabaseReference.child(key).setValue(user);
    }
    private void registerUser (final DatabaseReference fromPath, final DatabaseReference toPath) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            System.out.println("Copy failed");
                        } else {
                            System.out.println("Success");

                        }
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
