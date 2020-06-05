package com.example.busanapp.home;

public class ImageDBHelper {
    private String url;
    private String place;

    public ImageDBHelper(String url, String place) {
        this.url = url;
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public String getPlace() {
        return place;
    }
}