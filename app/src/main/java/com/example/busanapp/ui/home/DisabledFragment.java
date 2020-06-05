package com.example.busanapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

public class DisabledFragment extends Fragment {


    public DisabledFragment() {

    }

    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);




    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_disabled_pereson, container, false);


        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);


        boolean initem = false, insubject = false, incontents = false, inboardCode = false, inboardCodeNm = false, inregisterDate = false;
        boolean insetValueNm = false, ingubun = false, inimgUrl = false;

        String subject = null, contents = null, boardCode = null, boardCodeNm = null, registerDate = null;
        String setValueNm = null, gubun = null, imgUrl = null;

        try{
            URL url = new URL("http://apis.data.go.kr/6260000/BusanFcltsDsgstInfoService/getFcltsDsgstInfo?"
                    + "numOfRows=100&pageNo=1&serviceKey="
                    + "UvbltoXzj6ZR42XbDzaLoI%2BIzE5hQz5KTvkq%2F%2B4v9oLAq2nIbf50kFgS9yj9PjBaG9C23HDJ4TYfTonXrAZWzA%3D%3D"
            );
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");


            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("subject")){ //title 만나면 내용을 받을수 있게 하자
                            insubject = true;
                        }
                        if(parser.getName().equals("contents")){ //address 만나면 내용을 받을수 있게 하자
                            incontents = true;
                        }
                        if(parser.getName().equals("boardCode")){ //mapx 만나면 내용을 받을수 있게 하자
                            inboardCode = true;
                        }
                        if(parser.getName().equals("boardCodeNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inboardCodeNm = true;
                        }
                        if(parser.getName().equals("registerDate")){ //mapy 만나면 내용을 받을수 있게 하자
                            inregisterDate = true;
                        }
                        if(parser.getName().equals("setValueNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            insetValueNm = true;
                        }
                        if(parser.getName().equals("gubun")){ //mapy 만나면 내용을 받을수 있게 하자
                            ingubun = true;
                        }
                        if(parser.getName().equals("imgUrl")){ //mapy 만나면 내용을 받을수 있게 하자
                            inimgUrl = true;
                        }

                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(insubject){ //isTitle이 true일 때 태그의 내용을 저장.
                            subject = parser.getText();
                            insubject = false;
                        }
                        if(incontents){ //isAddress이 true일 때 태그의 내용을 저장.
                            contents = parser.getText();
                            incontents = false;
                        }
                        if(inboardCode){ //isMapx이 true일 때 태그의 내용을 저장.
                            boardCode = parser.getText();
                            inboardCode = false;
                        }
                        if(inboardCodeNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            boardCodeNm= parser.getText();
                            inboardCodeNm = false;
                        }
                        if(inregisterDate){ //isMapy이 true일 때 태그의 내용을 저장.
                            registerDate = parser.getText();
                            inregisterDate = false;
                        }
                        if(insetValueNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            setValueNm= parser.getText();
                            insetValueNm = false;
                        }
                        if(ingubun){ //isMapy이 true일 때 태그의 내용을 저장.
                            gubun = parser.getText();
                            ingubun = false;
                        }
                        if(inimgUrl){ //isMapy이 true일 때 태그의 내용을 저장.
                            imgUrl = parser.getText();
                            inimgUrl = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"상호 : " + subject
                                    +"\n 상세정보 : "+ contents +"\n 위치구분코드 : "+ boardCode +"\n 위치구분명 : " + boardCodeNm +  "\n 작성일 : " + registerDate + "\n 편의시설종류명 : " + setValueNm
                                    +"\n 구분 : " + gubun + "\n 이미지 URL : " + imgUrl
                                    +"\n"+"\n");
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
