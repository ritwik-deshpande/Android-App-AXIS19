package com.developer.app.axis19;


public class User {

    private String username;
    private String email;
    private String axisid;
    private String college;
    private String country;
    private String DOB;
    private String address;
    private String gender;
    private long phone;
    private long zipcode;

    public User(String username, String email, String axisid)
    {
        this.username = username;
        this.email = email;
        this.axisid = axisid;
        this.address = null;
        this.college = null;
        this.country = null;
        this.gender = null;
        this.DOB = null;
        this.phone = -1;
        this.zipcode = -1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAxisid() {
        return axisid;
    }

    public void setAxisid(String axisid) {
        this.axisid = axisid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
