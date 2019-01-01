package com.developer.app.axis19;

public class News_info {

    private int image;
    private String news_head;
    private  String news_contennt;

    public News_info(int image, String news_head, String news_contennt) {
        this.image = image;
        this.news_head = news_head;
        this.news_contennt = news_contennt;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNews_head() {
        return news_head;
    }

    public void setNews_head(String news_head) {
        this.news_head = news_head;
    }

    public String getNews_contennt() {
        return news_contennt;
    }

    public void setNews_contennt(String news_contennt) {
        this.news_contennt = news_contennt;
    }
}
