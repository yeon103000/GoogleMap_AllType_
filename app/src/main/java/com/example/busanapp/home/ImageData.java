package com.example.busanapp.home;

import java.util.ArrayList;

public class ImageData {
    ArrayList<ImageDBHelper> items = new ArrayList<>();

    public ArrayList<ImageDBHelper> getItems() {

        ImageDBHelper image1 = new ImageDBHelper("https://bto.or.kr/attach/IMAGE/Board/57/2019/12/rjcFVpiTJFDbyxro.JPG", "광안대교 야경");
        ImageDBHelper image2 = new ImageDBHelper("https://bto.or.kr/attach/IMAGE/Board/57/2019/12/JigSSfw1ZlUw8TS5.JPG", "용두산 공원");
        ImageDBHelper image3 = new ImageDBHelper("https://bto.or.kr/attach/IMAGE/Board/57/2019/12/hPXDrqwquUdnYs1m.JPG", "태종대");
        ImageDBHelper image4 = new ImageDBHelper("https://bto.or.kr/attach/IMAGE/Board/57/2019/12/JQwh2pcnR9eK32BZ.JPG", "자갈치");
        ImageDBHelper image5 = new ImageDBHelper("https://bto.or.kr/attach/IMAGE/Board/57/2019/12/N173roV9Q6TiJmBq.JPG", "남향대교");

        items.add(image1);
        items.add(image2);
        items.add(image3);
        items.add(image4);
        items.add(image5);

        return items;
    }
}