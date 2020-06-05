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


public class ParkingFragment extends Fragment {

    public ParkingFragment(){ }
    @Override

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking, container, false);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);

        boolean initem = false, inpkNam = false, inpkFm = false, inpkFubun = false, inguNm = false;

        String pkNam = null, pkFm = null, pkGubun = null, guNm = null;


        try{
            URL url = new URL("http://apis.data.go.kr/6260000/BusanPblcPrkngInfoService/getPblcPrkngInfo?serviceKey=NO2EbE%2Bu5KtWhuLp1rQALIAtWWnRDgj9mCuelgBAxRS%2Frxi12vyAMLBp%2F3KEanPiRfbO3hwggbbpZ%2B0XtKIolQ%3D%3D&numOfRows=100&pageNo=1");

            //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("pkNam")){ //title 만나면 내용을 받을수 있게 하자
                            inpkNam = true;
                        }
                        if(parser.getName().equals("pkFm")){ //address 만나면 내용을 받을수 있게 하자
                            inpkFm = true;
                        }
                        if(parser.getName().equals("pkGubun")){ //mapx 만나면 내용을 받을수 있게 하자
                            inpkFubun = true;
                        }
                        if(parser.getName().equals("guNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inguNm = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inpkNam){ //isTitle이 true일 때 태그의 내용을 저장.
                            pkNam = parser.getText();
                            inpkNam = false;
                        }
                        if(inpkFm){ //isAddress이 true일 때 태그의 내용을 저장.
                            pkFm = parser.getText();
                            inpkFm = false;
                        }
                        if(inpkFubun){ //isMapx이 true일 때 태그의 내용을 저장.
                            pkGubun = parser.getText();
                            inpkFubun = false;
                        }
                        if(inguNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            guNm = parser.getText();
                            inguNm = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"주차장 이름 : "+ pkNam +"\n 구분: "+ pkFm +"\n 구분2 : " + pkGubun
                                    +"\n 소속 : " + guNm +  "\n\n");
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