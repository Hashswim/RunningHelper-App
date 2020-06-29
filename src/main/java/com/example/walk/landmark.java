package com.example.walk;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class landmark extends Fragment {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    TextView txtText;
    TextView progress;
    walking walking = null;
    ImageView imgImg;
    Bundle bundle;
    Button closeButton;
    String region = "", prog;
    TextToSpeech tts;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        walking = (walking) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        walking = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.landmark, container, false);

        txtText = (TextView) rootview.findViewById(R.id.txtText);
        progress = (TextView) rootview.findViewById(R.id.progressPopup);
        imgImg = (ImageView) rootview.findViewById(R.id.landImage);

        bundle = getArguments();
        region = bundle.getString("region");
        prog = region.substring(0,region.length()-1);

        progress.setText(prog);
        closeButton = (Button) rootview.findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walking.onFragmentChanged(0);
            }
        });

        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //사용할 언어를 설정
                    int result = tts.setLanguage(Locale.KOREA);
                    //언어 데이터가 없거나 혹은 언어가 지원하지 않으면...
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getContext(), "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        //음성 톤
                        tts.setPitch(0.7f);
                        //읽는 속도
                        tts.setSpeechRate(1.2f);
                    }
                }
            }
        });


        if (region.equals("seoul1")) {
            txtText.setText("사적 제117호. 도성의 북쪽에 있" +
                    "다고 하여 북궐(北闕)이라고도 불리었다. 조선" +
                    "왕조의 건립에 따라 창건되어 초기에 정궁으로 사용되" +
                    "었으나 임진왜란 때 전소된 후 오랫동안 폐허로 남아 있다" +
                    "가 조선 말기 고종 때 중건되어 잠시 궁궐로 이용되었다");
            imgImg.setImageResource(R.drawable.seoul1);
        } else if (region.equals("seoul2")) {
            txtText.setText("대한민국 수도 서울의 공간적 중심이자 상징" +
                    "적인 랜드마크로서의 역할을 하고 있다.\n" +
                    "2005년 남산서울타워의 타워층을 N서울타워" +
                    "로 개칭하며 개보수하여 새로운 개념의 문화공간으" +
                    "로 재탄생하였다. 현재 남산서울타워는 소유자인 YTN이 " +
                    "사용하는 ‘서울타워플라자’와 임대자인 CJ가 사용하는 ‘N서울타" +
                    "워’로 구분되어 있다. 남산의 자연과 21세기 첨단기술이 만들어내는 절" +
                    "묘한 조화로움을 제공하는 휴식공간이자 문화복합공간으로 이용되고 있다");
            imgImg.setImageResource(R.drawable.seoul2);
        } else if (region.equals("newyork1")) {
            txtText.setText("세계 5대 도서관 중 하나이자 뉴욕을 대표하는" +
                    " 도서관이다. 미국에서는 워싱턴 D.C.의 국회 도서관 다" +
                    "음의 규모를 자랑한다.\n" +
                    "도서관 내에는 3천 800만 점이 넘는 도서와 소장품들이" +
                    " 무려 120km에 달하는 책꽂이에 진열되어 있다. 셰익스" +
                    "피어의 첫 작품집, 제퍼슨의 독립 선언문 자필 원고 등 희귀" +
                    "본도 다수 소장하고 있으며 희소가치가 있는 컬렉션은 정기적" +
                    "으로 공개하고 있다");
            imgImg.setImageResource(R.drawable.newyork1);
        } else if (region.equals("newyork2")) {
            txtText.setText("미국 뉴욕항으로 들어오는 허드슨" +
                    " 입구의 리버티섬(Liberty Island)에 세워진 " +
                    "조각상으로, 프랑스가 1886년에 미국 독립 100주년" +
                    "을 기념하여 선물한 것이다. 횃불을 치켜든 거대한 " +
                    "여신상으로 정식 명칭은 ‘세계를 비치는 자유(Li" +
                    "berty Enlightening the World)’이지만 통상 자유" +
                    "의 여신상으로 알려져 있다. 1875년에 만들기 시작하여" +
                    "1884년에 완성되었고, 잠시 프랑스 파리에 서 있다가 1885년 " +
                    "배를 통해 미국으로 이송되어 1886년에 현재의 위치에 세워졌다");
            imgImg.setImageResource(R.drawable.newyork2);
        } else if (region.equals("paris1")) {
            txtText.setText("세계 3대 박물관으로 꼽힌다. 1" +
                    "190년 지어졌을 당시에는 요새에 불과했지만 1" +
                    "6세기 중반 왕궁으로 재건축되면서 그 규모가 커" +
                    "졌다. 1793년 궁전 일부가 중앙 미술관으로 사용되" +
                    "면서 루브르는 궁전의 틀을 벗고 박물관으로 탈바꿈하기" +
                    " 시작했다. 이후 5세기 동안 유럽 외 다양한 지역에서 수집" +
                    "한 회화, 조각 등 수많은 예술품은 오늘날 30만 점가량에 이른다");
            imgImg.setImageResource(R.drawable.paris1);
        } else if (region.equals("paris2")) {
            txtText.setText("에펠탑은 센 강 서쪽 강" +
                    "변에 드넓게 펼쳐진 샹 드 마르스 공원" +
                    "(Champ de Mars) 끄트머리에 있다. 1889" +
                    "년 프랑스 혁명 100주년을 기념해 개최된 파리" +
                    " 만국박람회 때 구스타브 에펠의 설계로 세워진 탑" +
                    "이다. 높이 301m는 당시로서는 세계 최고였다. 총 무" +
                    "게는 9700t으로, 철기둥을 잇는 리벳을 약 250만 개나 " +
                    "사용했다고 한다");
            imgImg.setImageResource(R.drawable.paris2);
        } else if (region.equals("bali1")) {
            txtText.setText("발리의 대표적인 해변이다. " +
                    "전방에 어떤 장애물도 없이 바다만 시원하" +
                    "게 펼쳐 있으며, 거친 파도와 길고 넓은 해" +
                    "안은 서핑과 산책을 즐기기에 좋다. 저녁 무" +
                    "렵에는 아름다운 일몰을 감상할 수 있다");
            imgImg.setImageResource(R.drawable.bali1);
        } else if (region.equals("bali2")) {
            txtText.setText("우붓의 마지막 왕이 살았던 " +
                    "옛 궁전이다. 규모는 작지" +
                    "만 고풍스럽고 운치 있다. 매일 " +
                    "밤 레공 댄스, 바롱 댄스 등 전통 무" +
                    "용 공연이 열린다. 현재도 왕의 후손들이 거주한다");
            imgImg.setImageResource(R.drawable.bali2);
        }
        Speech();

        return rootview;
    }

    private void Speech() {
        String text = txtText.getText().toString();
        // QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
        // QUEUE_ADD: 현재 Queue에 값을 추가하는 옵션이다.
        // API 21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            // API 20
        else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

/*
    public void insert(String name, int distance) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("distance", distance);
        db.insert("landmark", null, values);
    }
    public void update (String name, int distance) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("distance", distance);
        db.update("landmark", values, "name=?", new String[]{name});
    }
    public void delete (String name) {
        db = helper.getWritableDatabase();
        db.delete("landmark", "name=?", new String[]{name});
        Log.i("db1", name + "정상적으로 삭제 되었습니다.");
    }
    public void select() {
        db = helper.getReadableDatabase();
        Cursor c = db.query("landmark", null, null, null, null, null, null);
        /* query (String table, String[] columns, String selection, String[]
         * selectionArgs, String groupBy, String having, String orderBy)

        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int distance = c.getInt(c.getColumnIndex("distance"));

            Log.i("db1", "id: " + _id + ", name : " + name + ", distance : " + distance);
        }
    }
    */

}
