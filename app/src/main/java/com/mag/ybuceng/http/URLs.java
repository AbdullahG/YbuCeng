package com.mag.ybuceng.http;

public enum URLs {
    FOOD_LIST("http://ybu.edu.tr/sks/"),
    ANNOUNCEMENTS("http://ybu.edu.tr/muhendislik/bilgisayar/"),
    NEWS("http://ybu.edu.tr/muhendislik/bilgisayar/");

    private final String url;

    URLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
