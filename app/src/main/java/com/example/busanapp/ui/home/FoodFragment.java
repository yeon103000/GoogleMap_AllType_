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

public class FoodFragment extends Fragment {


    public FoodFragment() {

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


        View view = inflater.inflate(R.layout.fragment_food, container, false);


        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);


        boolean initem = false, inbsnsSector = false, inbsnsCond = false, inbsnsNm = false, inaddrRoad = false, inaddrJibun = false;
        boolean inmenu = false, intel = false, inspecDate = false, inovrdDate = false, ingugun = false, indataDay = false, inlat = false, inlng = false;

        String bsnsSector = null, bsnsCond = null, bsnsNm = null, addrRoad = null, addrJibun = null;
        String menu = null, tel = null, specDate = null, ovrdDate = null, gugun = null, dataDay = null, lat = null, lng = null;

        try{
            URL url = new URL("http://apis.data.go.kr/6260000/BusanTblFnrstrnStusService/getTblFnrstrnStusInfo?"
                    + "numOfRows=100&pageNo=1&serviceKey="
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
                        if(parser.getName().equals("bsnsSector")){ //title 만나면 내용을 받을수 있게 하자
                            inbsnsSector = true;
                        }
                        if(parser.getName().equals("bsnsCond")){ //address 만나면 내용을 받을수 있게 하자
                            inbsnsCond = true;
                        }
                        if(parser.getName().equals("bsnsNm")){ //mapx 만나면 내용을 받을수 있게 하자
                            inbsnsNm = true;
                        }
                        if(parser.getName().equals("addrRoad")){ //mapy 만나면 내용을 받을수 있게 하자
                            inaddrRoad = true;
                        }
                        if(parser.getName().equals("addrJibun")){ //mapy 만나면 내용을 받을수 있게 하자
                            inaddrJibun = true;
                        }
                        if(parser.getName().equals("menu")){ //mapy 만나면 내용을 받을수 있게 하자
                            inmenu = true;
                        }
                        if(parser.getName().equals("tel")){ //mapy 만나면 내용을 받을수 있게 하자
                            intel = true;
                        }
                        if(parser.getName().equals("specDate")){ //mapy 만나면 내용을 받을수 있게 하자
                            inspecDate = true;
                        }
                        if(parser.getName().equals("ovrdDate")){ //mapy 만나면 내용을 받을수 있게 하자
                            inovrdDate = true;
                        }
                        if(parser.getName().equals("gugun")){ //mapy 만나면 내용을 받을수 있게 하자
                            ingugun = true;
                        }
                        if(parser.getName().equals("dataDay")){ //mapy 만나면 내용을 받을수 있게 하자
                            indataDay = true;
                        }
                        if(parser.getName().equals("lat")){ //mapy 만나면 내용을 받을수 있게 하자
                            inlat = true;
                        }
                        if(parser.getName().equals("lng")){ //mapy 만나면 내용을 받을수 있게 하자
                            inlng = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inbsnsSector){ //isTitle이 true일 때 태그의 내용을 저장.
                            bsnsSector = parser.getText();
                            inbsnsSector = false;
                        }
                        if(inbsnsCond){ //isAddress이 true일 때 태그의 내용을 저장.
                            bsnsCond = parser.getText();
                            inbsnsCond = false;
                        }
                        if(inbsnsNm){ //isMapx이 true일 때 태그의 내용을 저장.
                            bsnsNm = parser.getText();
                            inbsnsNm = false;
                        }
                        if(inaddrRoad){ //isMapy이 true일 때 태그의 내용을 저장.
                            addrRoad= parser.getText();
                            inaddrRoad = false;
                        }
                        if(inaddrJibun){ //isMapy이 true일 때 태그의 내용을 저장.
                            addrJibun = parser.getText();
                            inaddrJibun = false;
                        }
                        if(inmenu){ //isMapy이 true일 때 태그의 내용을 저장.
                            menu = parser.getText();
                            inmenu = false;
                        }
                        if(intel){ //isMapy이 true일 때 태그의 내용을 저장.
                            tel = parser.getText();
                            intel = false;
                        }
                        if(inspecDate){ //isMapy이 true일 때 태그의 내용을 저장.
                            specDate = parser.getText();
                            inspecDate = false;
                        }
                        if(inovrdDate){ //isMapy이 true일 때 태그의 내용을 저장.
                            ovrdDate = parser.getText();
                            inovrdDate = false;
                        }
                        if(ingugun){ //isMapy이 true일 때 태그의 내용을 저장.
                            gugun = parser.getText();
                            ingugun = false;
                        }
                        if(indataDay){ //isMapy이 true일 때 태그의 내용을 저장.
                            dataDay = parser.getText();
                            indataDay = false;
                        }
                        if(inlat){ //isMapy이 true일 때 태그의 내용을 저장.
                            lat = parser.getText();
                            inlat = false;
                        }
                        if(inlng){ //isMapy이 true일 때 태그의 내용을 저장.
                            lng = parser.getText();
                            inlng = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"업종 : " + bsnsSector
                                    +"\n 업태 : "+ bsnsCond +"\n 업소명 : "+ bsnsNm +"\n 소재지(도로명) : " + addrRoad +  "\n 소재지(지번) : " + addrJibun + "\n 메뉴 : " + menu
                                    +"\n 전화번호 : " + tel + "\n 지정일자 : " + specDate + "\n 재지정일자 : " + ovrdDate+ "\n 구군명 : " + gugun+ "\n 데이터기준일자 : " + dataDay+ "\n 위도 : " + lat+ "\n 경도 : " + lng
                                    +"\n" + "──────────────────" + "\n\n");
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
