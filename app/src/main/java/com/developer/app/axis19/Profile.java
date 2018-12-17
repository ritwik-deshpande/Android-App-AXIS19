package com.developer.app.axis19;

public class Profile  {
    String name;
    int image_url;
    String email;
    String phone_number;
    String post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Profile(String name, int image_url, String email, String phone_number, String post) {

        this.name = name;
        this.image_url = image_url;
        this.email = email;
        this.phone_number = phone_number;
        this.post = post;
    }
}
