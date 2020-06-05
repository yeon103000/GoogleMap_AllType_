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


public class HospitalFragment extends Fragment {
    public HospitalFragment() {
    }

    public static Fragment newInstance() {
        return new HospitalFragment();
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        StrictMode.enableDefaults();
        TextView status1 = (TextView) view.findViewById(R.id.result);

        boolean initem = false, ininstit_kind = false, inmedical_insist_kind = false, inexam_part = false, ininstit_nm = false;
        boolean inMonday= false, inTuesday = false, inWednesday = false, inThursday = false, inFriday = false, inSaturday = false, inSunday = false;
        boolean instreet_nm_addr = false, inorgan_loc = false;

        String instit_kind = null, medical_insist_kind = null, exam_part = null, instit_nm = null;
        String Monday = null, Tuesday = null, Wednesday = null, Thursday = null, Friday = null, Saturday = null, Sunday = null;
        String street_nm_addr = null, organ_loc = null;


        try{
            URL url = new URL("http://apis.data.go.kr/6260000/MedicInstitService/MedicalInstitInfo?" +
                    "serviceKey=h0iKgTJif3T4juiw3eVVAZnw0dfs6YFSEbr%2F25P6a0HG%2FBeLxtWgQL7w8%2BaXKEo6uiHUHOXabtpguDeAXcMGIw%3D%3D" +
                    "&pageNo=1&numOfRows=10");

            //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("instit_kind")){ //title 만나면 내용을 받을수 있게 하자
                            ininstit_kind = true;
                        }
                        if(parser.getName().equals("medical_instit_kind")){ //address 만나면 내용을 받을수 있게 하자
                            inmedical_insist_kind = true;
                        }
                        if(parser.getName().equals("exam_part")){ //mapx 만나면 내용을 받을수 있게 하자
                            inexam_part = true;
                        }
                        if(parser.getName().equals("instit_nm")){ //mapy 만나면 내용을 받을수 있게 하자
                            ininstit_nm = true;
                        }
                        if(parser.getName().equals("Monday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMonday = true;
                        }
                        if(parser.getName().equals("Tuesday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inTuesday = true;
                        }
                        if(parser.getName().equals("Wednesday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inWednesday = true;
                        }
                        if(parser.getName().equals("Thursday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inThursday = true;
                        }
                        if(parser.getName().equals("Friday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inFriday = true;
                        }
                        if(parser.getName().equals("Saturday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inSaturday = true;
                        }
                        if(parser.getName().equals("Sunday")){ //mapy 만나면 내용을 받을수 있게 하자
                            inSunday = true;
                        }
                        if(parser.getName().equals("street_nm_addr")){ //mapy 만나면 내용을 받을수 있게 하자
                            instreet_nm_addr = true;
                        }
                        if(parser.getName().equals("organ_loc")){ //mapy 만나면 내용을 받을수 있게 하자
                            inorgan_loc = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(ininstit_kind){ //isTitle이 true일 때 태그의 내용을 저장.
                            instit_kind = parser.getText();
                            ininstit_kind = false;
                        }
                        if(inmedical_insist_kind){ //isAddress이 true일 때 태그의 내용을 저장.
                            medical_insist_kind = parser.getText();
                            inmedical_insist_kind = false;
                        }
                        if(inexam_part){ //isMapx이 true일 때 태그의 내용을 저장.
                            exam_part = parser.getText();
                            inexam_part = false;
                        }
                        if(ininstit_nm){ //isMapy이 true일 때 태그의 내용을 저장.
                            instit_nm = parser.getText();
                            ininstit_nm = false;
                        }
                        if(inMonday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Monday = parser.getText();
                            inMonday = false;
                        }
                        if(inTuesday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Tuesday = parser.getText();
                            inTuesday = false;
                        }
                        if(inWednesday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Wednesday = parser.getText();
                            inWednesday = false;
                        }
                        if(inThursday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Thursday = parser.getText();
                            inThursday = false;
                        }
                        if(inFriday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Friday = parser.getText();
                            inFriday = false;
                        }
                        if(inSaturday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Saturday = parser.getText();
                            inSaturday = false;
                        }
                        if(inSunday){ //isMapy이 true일 때 태그의 내용을 저장.
                            Sunday = parser.getText();
                            inSunday = false;
                        }
                        if(instreet_nm_addr){ //isMapy이 true일 때 태그의 내용을 저장.
                            street_nm_addr = parser.getText();
                            instreet_nm_addr = false;
                        }
                        if(inorgan_loc){ //isMapy이 true일 때 태그의 내용을 저장.
                            organ_loc = parser.getText();
                            inorgan_loc = false;
                        }


                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"병원 이름 : "+ instit_nm +"\n 주소 : " + street_nm_addr
                                    +"\n 교통 : " + organ_loc + "\n구분 : "+ instit_kind +"\n 병원 구분 : " + medical_insist_kind
                                    +"\n 분류 : " + exam_part +  "\n 월요일 운영 시간 : " + Monday +"\n 화요일 운영 시간 : " + Tuesday
                                    +"\n 수요일 운영 시간 : " + Wednesday + "\n 목요일 운영 시간 : " +Thursday+ "\n 금요일 운영 시간 : " +Friday
                                    +"\n 토요일 운영 시간 : " + Saturday + "\n 일요일 운영 시간 : " + Sunday +"\n" + "──────────────────" + "\n\n" );
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