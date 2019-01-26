package com.developer.app.axis19;

public class News_info {

    private String image;
    private String news_head;
    private  String news_content;

    public News_info(){}
    public News_info(String image, String news_head, String news_contennt) {
        this.image = image;
        this.news_head = news_head;
        this.news_content = news_contennt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNews_head() {
        return news_head;
    }

    public void setNews_head(String news_head) {
        this.news_head = news_head;
    }

    public String getNews_contennt() {
        return news_content;
    }

    public void setNews_contennt(String news_contennt) {
        this.news_content = news_contennt;
    }
}
