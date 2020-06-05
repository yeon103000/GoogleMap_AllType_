package com.example.busanapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
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

public class FindHospitalFragment extends Fragment {

    EditText edit;
    TextView text;
    Button btn;

    String Key = "h0iKgTJif3T4juiw3eVVAZnw0dfs6YFSEbr%2F25P6a0HG%2FBeLxtWgQL7w8%2BaXKEo6uiHUHOXabtpguDeAXcMGIw%3D%3D";
    String data;

    public FindHospitalFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_hospital, container, false);

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
            location = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String queryUrl = "http://apis.data.go.kr/6260000/MedicInstitService/MedicalInstitInfo?"
                + "serviceKey=" + Key
                + "&pageNo=1&numOfRows=5"
                + "&instit_nm=" + location;

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) ;
                        else if (tag.equals("instit_kind")) {
                            buffer.append("구분 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("medical_instit_kind")) {
                            buffer.append("병원 구분 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("exam_part")) {
                            buffer.append("분류 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("instit_nm")) {
                            buffer.append("병원 이름 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Monday")) {
                            buffer.append("월요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Tuesday")) {
                            buffer.append("화요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Wednesday")) {
                            buffer.append("수요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Thursday")) {
                            buffer.append("목요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Friday")) {
                            buffer.append("금요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Saturday")) {
                            buffer.append("토요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("Sunday")) {
                            buffer.append("일요일 운영 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("street_nm_addr")) {
                            buffer.append("주소 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("organ_loc")) {
                            buffer.append("교통 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) buffer.append("\n");
                        break;

                }
                eventType = xpp.next();
            }

        } catch (Exception e) {

        }

        buffer.append("끝");
        return buffer.toString();
    }
}
