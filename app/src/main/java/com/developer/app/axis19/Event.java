package com.developer.app.axis19;

public class Event {
    private String EventName;
    private String OName1;
    private String OName2;
    private String desc;
    private String img_url;
    private long phone1;
    private long phone2;

    public long getPhone1() {
        return phone1;
    }

    public void setPhone1(long phone1) {
        this.phone1 = phone1;
    }

    public long getPhone2() {
        return phone2;
    }

    public void setPhone2(long phone2) {
        this.phone2 = phone2;
    }


    
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public Event(String eventName, String OName1, String OName2, String desc, String image,long phone1,long phone2) {
        EventName = eventName;
        this.OName1 = OName1;
        this.OName2 = OName2;
        this.desc = desc;
        this.img_url=image;
        this.phone1=phone1;
        this.phone2=phone2;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventName() {

        return EventName;
    }
}
