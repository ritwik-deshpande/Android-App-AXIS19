package com.developer.app.axis19;

public class Event {
    private String EventName;
    private String OName1;
    private String OName2;
    private String desc;
    private int Image;

    public String getOName1() {
        return OName1;
    }

    public void setOName1(String OName1) {
        this.OName1 = OName1;
    }

    public String getOName2() {
        return OName2;
    }

    public void setOName2(String OName2) {
        this.OName2 = OName2;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public Event(String eventName, String OName1, String OName2, String desc, int image) {
        EventName = eventName;
        this.OName1 = OName1;
        this.OName2 = OName2;
        this.desc = desc;
        Image = image;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventName() {

        return EventName;
    }
}
