package com.example.busanapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.busanapp.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class FindFoodFragment extends Fragment {


    EditText edit;
    TextView text;
    Button btn;

    String Key = "NO2EbE%2Bu5KtWhuLp1rQALIAtWWnRDgj9mCuelgBAxRS%2Frxi12vyAMLBp%2F3KEanPiRfbO3hwggbbpZ%2B0XtKIolQ%3D%3D";
    String data;


    public FindFoodFragment() {

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




        View view = inflater.inflate(R.layout.fragment_find_food, container, false);


        StrictMode.enableDefaults();
        edit = (EditText) view.findViewById(R.id.edit);
        text = (TextView) view.findViewById(R.id.text);
        btn = (Button) view.findViewById(R.id.button);

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                data = getXmlData();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        text.setText(data);
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });
        return view;
    }

    String getXmlData() {

        StringBuffer buffer = new StringBuffer();

        String str = edit.getText().toString();
        String location = null;
        try {
            location = URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String queryUrl = "http://apis.data.go.kr/6260000/BusanTblFnrstrnStusService/getTblFnrstrnStusInfo?"
                +"serviceKey="+ Key
                +"&numOfRows=10&pageNo=1"
                +"&bsnsNm="+ location;


        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){

                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag=xpp.getName();

                        if(tag.equals("item"));
                        else if(tag.equals("bsnsSector")){
                            buffer.append("업종 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("bsnsCond")){
                            buffer.append("업태 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("bsnsNm")){
                            buffer.append("업소명 :");
                            xpp.next();
                            buffer.append(xpp.getText());//cpId
                            buffer.append("\n");
                        }
                        else if(tag.equals("addrRoad")){
                            buffer.append("소재지(도로명) :");
                            xpp.next();
                            buffer.append(xpp.getText());//cpNm
                            buffer.append("\n");
                        }
                        else if(tag.equals("addrJibun")){
                            buffer.append("소재지(지번) :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("menu")){
                            buffer.append("메뉴 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("tel")) {
                            buffer.append("전화번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("specDate")) {
                            buffer.append("지정일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("ovrdDate")) {
                            buffer.append("재지정일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("gugun")) {
                            buffer.append("구군명 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("dataDay")) {
                            buffer.append("데이터기준일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("lat")) {
                            buffer.append("위도 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        else if(tag.equals("lng")) {
                            buffer.append("경도 :");
                            xpp.next();
                            buffer.append(xpp.getText());//
                            buffer.append("\n");
                        }
                        break;
                    case  XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName();
                        if(tag.equals("item"))buffer.append("\n");
                        break;


                }
                eventType= xpp.next();
            }
        } catch(Exception e){

        }

        return buffer.toString();
    }
}

