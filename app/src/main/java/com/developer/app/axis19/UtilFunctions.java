package com.developer.app.axis19;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UtilFunctions {

    public String getUser_key(String email)
    {
        email = email.substring(0,email.indexOf('@'));
        email.replaceAll("[.]","");
        return email;
    }

    public String generate_axisid()
    {
        Random rand = new Random();
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        String t = today.toString();
        t = t.split("\\s+")[2];
        long milisecond = today.getTime()%1000;
        long r = (rand.nextInt(999)+100)%1000;
        long id = ((long)Math.pow(r,2) +(long) Math.pow(milisecond,2)) %1000;
        String axisid = "AXIS19" + t + ((id < 100)?'0': ((id<10)?"00":"") )+ id;
        return axisid;
    }
}
