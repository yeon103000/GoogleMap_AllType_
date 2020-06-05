package com.example.busanapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.busanapp.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class BusFragment extends Fragment {

    public BusFragment(){ }
    @Override

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);
        boolean initem = false, inroute_no = false, infirst_bus_time = false, inlast_bus_time = false, instarting_point = false, inend_point = false;
        boolean intransfer_point = false, ingugun = false, incompany_nm = false, intel = false, inbus_interval = false;
        boolean inchild_cash_fare = false, inchild_card_fare = false, inteen_cash_fare = false, inteen_card_fare = false, ingeneral_card_fare = false;

        String route_no = null, first_bus_time = null, last_bus_time = null, starting_point = null, end_point = null;
        String transfer_point = null, gugun = null, company_nm = null, tel = null, bus_interval = null;
        String child_cash_fare = null, child_card_fare = null, teen_cash_fare = null, teen_card_fare = null, general_card_fare = null;


        try{
            URL url = new URL("http://apis.data.go.kr/6260000/VillageBusService/VillageBusStusInfo?serviceKey=LhJ8ZI%2B5mq2PSlEJf%2FqFKk%2FS9vSYq4tHg0UTKPhddcpzm9cKrFkdIoOVfV%2Bm0HIPkMkIwu0eR1Jag16zh33i%2Fg%3D%3D&numOfRows=5&pageNo=1");

            //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("route_no")){ //title 만나면 내용을 받을수 있게 하자
                            inroute_no = true;
                        }
                        if(parser.getName().equals("first_bus_time")){ //address 만나면 내용을 받을수 있게 하자
                            infirst_bus_time = true;
                        }
                        if(parser.getName().equals("last_bus_time")){ //mapx 만나면 내용을 받을수 있게 하자
                            inlast_bus_time = true;
                        }
                        if(parser.getName().equals("starting_point")){ //mapy 만나면 내용을 받을수 있게 하자
                            instarting_point = true;
                        }
                        if(parser.getName().equals("end_point")){ //mapy 만나면 내용을 받을수 있게 하자
                            inend_point = true;
                        }
                        if(parser.getName().equals("transfer_point")){ //mapy 만나면 내용을 받을수 있게 하자
                            intransfer_point = true;
                        }
                        if(parser.getName().equals("gugun")){ //mapy 만나면 내용을 받을수 있게 하자
                            ingugun = true;
                        }
                        if(parser.getName().equals("company_nm")){ //mapy 만나면 내용을 받을수 있게 하자
                            incompany_nm = true;
                        }
                        if(parser.getName().equals("tel")){ //mapy 만나면 내용을 받을수 있게 하자
                            intel = true;
                        }
                        if(parser.getName().equals("bus_interval")){ //mapy 만나면 내용을 받을수 있게 하자
                            inbus_interval = true;
                        }
                        if(parser.getName().equals("child_cash_fare")){ //mapy 만나면 내용을 받을수 있게 하자
                            inchild_cash_fare = true;
                        }
                        if(parser.getName().equals("child_card_fare")){ //mapy 만나면 내용을 받을수 있게 하자
                            inchild_card_fare = true;
                        }
                        if(parser.getName().equals("teen_cash_fare")){ //mapy 만나면 내용을 받을수 있게 하자
                            inteen_cash_fare = true;
                        }
                        if(parser.getName().equals("tenn_card_fare")){ //mapy 만나면 내용을 받을수 있게 하자
                            inteen_card_fare = true;
                        }
                        if(parser.getName().equals("general_card_fare")){ //mapy 만나면 내용을 받을수 있게 하자
                            ingeneral_card_fare = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inroute_no){ //isTitle이 true일 때 태그의 내용을 저장.
                            route_no = parser.getText();
                            inroute_no = false;
                        }
                        if(infirst_bus_time){ //isAddress이 true일 때 태그의 내용을 저장.
                            first_bus_time = parser.getText();
                            infirst_bus_time = false;
                        }
                        if(inlast_bus_time){ //isMapx이 true일 때 태그의 내용을 저장.
                            last_bus_time = parser.getText();
                            inlast_bus_time = false;
                        }
                        if(instarting_point){ //isMapy이 true일 때 태그의 내용을 저장.
                            starting_point = parser.getText();
                            instarting_point = false;
                        }
                        if(inend_point){ //isMapy이 true일 때 태그의 내용을 저장.
                            end_point = parser.getText();
                            inend_point = false;
                        }
                        if(intransfer_point){ //isMapy이 true일 때 태그의 내용을 저장.
                            transfer_point = parser.getText();
                            intransfer_point = false;
                        }
                        if(ingugun){ //isMapy이 true일 때 태그의 내용을 저장.
                            gugun = parser.getText();
                            ingugun = false;
                        }
                        if(incompany_nm){ //isMapy이 true일 때 태그의 내용을 저장.
                            company_nm = parser.getText();
                            incompany_nm = false;
                        }
                        if(intel){ //isMapy이 true일 때 태그의 내용을 저장.
                            tel = parser.getText();
                            intel = false;
                        }
                        if(inbus_interval){ //isMapy이 true일 때 태그의 내용을 저장.
                            bus_interval = parser.getText();
                            inbus_interval = false;
                        }
                        if(inchild_cash_fare){ //isMapy이 true일 때 태그의 내용을 저장.
                            child_cash_fare = parser.getText();
                            inchild_cash_fare = false;
                        }
                        if(inchild_card_fare){ //isMapy이 true일 때 태그의 내용을 저장.
                            child_card_fare = parser.getText();
                            inchild_card_fare = false;
                        }
                        if(inteen_cash_fare){ //isMapy이 true일 때 태그의 내용을 저장.
                            teen_cash_fare = parser.getText();
                            inteen_cash_fare = false;
                        }
                        if(inteen_card_fare){ //isMapy이 true일 때 태그의 내용을 저장.
                            teen_card_fare = parser.getText();
                            inteen_card_fare = false;
                        }
                        if(ingeneral_card_fare){ //isMapy이 true일 때 태그의 내용을 저장.
                            general_card_fare = parser.getText();
                            ingeneral_card_fare = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"버스 정보 : "+ route_no +"\n 첫차 시각: "+ first_bus_time +"\n 막차 시각 : " + last_bus_time
                                    +"\n 기점 : " + starting_point +  "\n 종점 :" + end_point +"\n 환승 구간 : " +transfer_point
                                    +"\n 지역 : " + gugun + "\n 회사 : " + company_nm + "\n 연락처 : " + tel
                                    +"\n 버스 간격 : " +bus_interval + "\n 어린이 현금 요금 : " + child_cash_fare + "\n 어린이 카드 요금 : " + child_card_fare
                                    + "\n 청소년 현금 요금 : " +teen_cash_fare + "\n 청소년 카드 요금 : " + teen_card_fare
                                    +"\n 성인 현금 요금 : " + general_card_fare +"\n\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
        }



        return view;
    }
}