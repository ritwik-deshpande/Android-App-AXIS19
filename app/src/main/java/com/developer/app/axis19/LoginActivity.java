package com.developer.app.axis19;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.developer.app.axis19.MainActivity.Email;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginForm";

    String username;
    String email;
    String axisid;
    String college;
    String country;
    String DOB;
    String address;
    String gender;
    String phone;
    String zipcode;
    EditText dob;
    Calendar c;
    Button submit;
    User login_user;

    RadioGroup radioGroup;
    private DatabaseHelper db;
    private UtilFunctions utilFunctions;
    DatePickerDialog dpd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper();
        submit=(Button)findViewById(R.id.submit);
        dob=(EditText)findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c=Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                        month++;
                        dob.setText(year+"/"+month+"/"+day);

                    }
                },day,month,year);
                dpd.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = MainActivity.name;
                email = Email;
                ((TextView)findViewById(R.id.email)).setText(email);
                ((TextView)findViewById(R.id.username)).setText(username);
                axisid = MainActivity.axisid;
                college = ((EditText)findViewById(R.id.college)).getText().toString();
                country = ((EditText)findViewById(R.id.country)).getText().toString();
                address = ((EditText)findViewById(R.id.address)).getText().toString();
                radioGroup = ((RadioGroup)findViewById(R.id.gender));
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                gender = ((RadioButton) findViewById(selectedId)).getText().toString();

                phone = ((EditText)findViewById(R.id.contactno)).getText().toString();
                zipcode = ((EditText)findViewById(R.id.zipcode)).getText().toString();
                DOB  = dob.getText().toString();
                login_user = new User(username, email,axisid,college,country, DOB,address,gender,phone,zipcode);
                db.createUser(login_user);
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public String getUser_key(String email)
    {
        email = email.substring(0,email.indexOf('@'));
        email.replaceAll(".","");
        return email;
    }


}

