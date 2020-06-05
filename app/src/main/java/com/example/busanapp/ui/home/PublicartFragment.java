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

public class PublicartFragment extends Fragment {


    public PublicartFragment() {

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




        View view = inflater.inflate(R.layout.fragment_publicart, container, false);


        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);


        boolean initem = false, inaddr1 = false, inaddr2 = false, intitle = false, inShape = false;
        boolean inmanageAgency = false, inmainAgent = false, inpurpose = false, inSpec = false;
        boolean ininstalled = false, inimgSrc = false;

        String addr1 = null, addr2 = null, title = null, Shape = null, manageAgency = null, mainAgent = null;
        String purpose = null, Spec = null, installed = null, imgSrc = null;

        try{
            URL url = new URL("http://apis.data.go.kr/6260000/PublicArt/getPublicArtInfo?"
                    + "pageNo=1&numOfRows=100&serviceKey="
                    + "NO2EbE%2Bu5KtWhuLp1rQALIAtWWnRDgj9mCuelgBAxRS%2Frxi12vyAMLBp%2F3KEanPiRfbO3hwggbbpZ%2B0XtKIolQ%3D%3D"
            );
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");


            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("addr1")){ //title 만나면 내용을 받을수 있게 하자
                            inaddr1 = true;
                        }
                        if(parser.getName().equals("addr2")){ //address 만나면 내용을 받을수 있게 하자
                            inaddr2 = true;
                        }
                        if(parser.getName().equals("title")){ //mapx 만나면 내용을 받을수 있게 하자
                            intitle = true;
                        }
                        if(parser.getName().equals("Shape")){ //mapy 만나면 내용을 받을수 있게 하자
                            inShape = true;
                        }
                        if(parser.getName().equals("manageAgency")){ //mapy 만나면 내용을 받을수 있게 하자
                            inmanageAgency = true;
                        }
                        if(parser.getName().equals("mainAgent")){ //mapy 만나면 내용을 받을수 있게 하자
                            inmainAgent = true;
                        }
                        if(parser.getName().equals("purpose")){ //mapy 만나면 내용을 받을수 있게 하자
                            inpurpose = true;
                        }
                        if(parser.getName().equals("Spec")){ //mapy 만나면 내용을 받을수 있게 하자
                            inSpec = true;
                        }
                        if(parser.getName().equals("installed")){ //mapy 만나면 내용을 받을수 있게 하자
                            ininstalled = true;
                        }
                        if(parser.getName().equals("imgSrc")){ //mapy 만나면 내용을 받을수 있게 하자
                            inimgSrc = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inaddr1){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr1 = parser.getText();
                            inaddr1 = false;
                        }
                        if(inaddr2){ //isAddress이 true일 때 태그의 내용을 저장.
                            addr2 = parser.getText();
                            inaddr2 = false;
                        }
                        if(intitle){ //isMapx이 true일 때 태그의 내용을 저장.
                            title = parser.getText();
                            intitle = false;
                        }
                        if(inShape){ //isMapy이 true일 때 태그의 내용을 저장.
                            Shape = parser.getText();
                            inShape = false;
                        }
                        if(inmanageAgency){ //isMapy이 true일 때 태그의 내용을 저장.
                            manageAgency = parser.getText();
                            inmanageAgency = false;
                        }
                        if(inmainAgent){ //isMapy이 true일 때 태그의 내용을 저장.
                            mainAgent = parser.getText();
                            inmainAgent = false;
                        }
                        if(inpurpose){ //isMapy이 true일 때 태그의 내용을 저장.
                            purpose = parser.getText();
                            inpurpose = false;
                        }
                        if(inSpec){ //isMapy이 true일 때 태그의 내용을 저장.
                            Spec = parser.getText();
                            inSpec = false;
                        }
                        if(ininstalled){ //isMapy이 true일 때 태그의 내용을 저장.
                            installed = parser.getText();
                            ininstalled = false;
                        }
                        if(inimgSrc){ //isMapy이 true일 때 태그의 내용을 저장.
                            imgSrc = parser.getText();
                            inimgSrc = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"작품명 : " + title
                                    +"\n 위치 : "+ addr1 +"\n 나머지주소: "+ addr2 +"\n 형상 : " + Shape +  "\n 관리기관 : " + manageAgency + "\n 건립주체 : " + mainAgent
                                    +"\n 건립취지 : " +purpose + "\n 규격 : " + Spec + "\n 건립일자 : " + installed
                                    +"\n 이미지경로 : " + imgSrc +"\n"+"\n");
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
