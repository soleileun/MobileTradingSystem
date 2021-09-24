package com.example.my4weekschallenge;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


/*
*
* this가 뭔가요?
*
*
* */

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // activity_main.xml로 화면을 표시한다 하지만 안할거니까!
        //반면, 레이아웃 파라미터 종류는 위젯이 아니라 소속되어 있는 부모 레이아웃에 따라 달라진다.

        //레이아웃 파라미터 객체 생성
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //이런식으로 를 결정한다 -> 이게 아니고 이걸 어떻게 쓰는건지를 알아야 제대로 쓸 수 있다는 사실
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 전체 배경
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(255, 255, 255));
        setContentView(baseLayout, params);


        // 툴바
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("보유종목(국내)");

        toolbar.setTitleTextColor(Color.BLACK);
        this.setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        //actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
        //  toolbar.inflateMenu(R.menu.cstoolbar); // 서랍 아이콘으로 하는 방법

        LinearLayout btnlayout = new LinearLayout(this);
        //이렇게 해야 좌측으로 배치가능 나를 누구 기준으로 배치할거냐 생각을 해보면 됨
        btnlayout.setLayoutParams(new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                ));
        Button btn1 = new Button(this);
        btn1.setText("검색");
        btnlayout.addView(btn1);
        Button btn2 = new Button(this);
        btn2.setText("환경설정");
//        btn2.setLayoutParams(lp);
        //toolbar.addView(btn2);
        // btnlayout.setGravity(Gravity.END|Gravity.RIGHT);
        btnlayout.addView(btn2);
        toolbar.addView(btnlayout);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuSelected("BACK");
              }
        });
        baseLayout.addView(toolbar);


        //수평스크롤
        LinearLayout scrollLayout = new LinearLayout(this);
        scrollLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,140));

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        horizontalScrollView.setBackgroundColor(Color.LTGRAY);
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));

        LinearLayout hscrollBtnLayout = new LinearLayout(this);
        hscrollBtnLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,140));
        hscrollBtnLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button scrollBtn1 = new Button(this);
        scrollBtn1.setText("최근조회종목");
        scrollBtn1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        Button scrollBtn2 = new Button(this);
        scrollBtn2.setText("보유종목(국내)");
        Button scrollBtn3 = new Button(this);
        scrollBtn3.setText("보유종목(해외)");
        Button scrollBtn4 = new Button(this);
        scrollBtn4.setText("특징종목");
        Button scrollBtn5 = new Button(this);
        scrollBtn5.setText("기본그룹");

        hscrollBtnLayout.addView(scrollBtn1);
        hscrollBtnLayout.addView(scrollBtn2);
        hscrollBtnLayout.addView(scrollBtn3);
        hscrollBtnLayout.addView(scrollBtn4);
        hscrollBtnLayout.addView(scrollBtn5);

        horizontalScrollView.addView(hscrollBtnLayout);
        scrollLayout.addView(horizontalScrollView);

        //수평 스크롤 옆 X 버튼
        LinearLayout btnXLayout = new LinearLayout(this);
        btnXLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        Button btnX = new Button(this);
        btnXLayout.setGravity(Gravity.END);
        btnX.setText("X");
        btnXLayout.addView(btnX);

        scrollLayout.addView(btnXLayout);
        baseLayout.addView(scrollLayout);

//        View card = new View(this);
//        card.setBackgroundColor(Color.CYAN);

        // 상세보기 부분 구현
        LinearLayout accLayout = new LinearLayout(this);
        accLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(this);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        tp.topMargin = (int) dpToPx(10.f); // 여기에 넣으니까 되네
        tv.setPadding(80,0,80,0); //
        tp.setMargins(20,0,20,0);
        tv.setBackgroundResource(R.drawable.cardview);
//        tv.setBackgroundColor(Color.GREEN);
        tv.setGravity(Gravity.CENTER);
        tv.setText("ddddddd");
//        tv.setBackgroundColor(Color.WHITE);
        tv.setLayoutParams(tp);
        accLayout.addView(tv);


        TextView line = new TextView(this);
        line.setHeight((int) dpToPx(1));
        line.setBackgroundColor(Color.DKGRAY);
        accLayout.addView(line);
        baseLayout.addView(accLayout);

        //상세보기
        Button btn = new Button(this);
        btn.setText("상세보기");
        btn.setBackgroundColor(Color.WHITE);
        baseLayout.addView(btn);
        //addView를 해서 paramtext

        TextView text = new TextView(this);
        text.setText("  ");
        text.setTextColor(Color.RED);
        text.setTextSize(18);
        baseLayout.addView(text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "상세보기", Toast.LENGTH_SHORT).show();
            }
        });

        baseLayout.addView(new Button(this));
    // 셀을 만들어볼것인데 맘 처럼 잘 안됨 ㅠ

        LinearLayout bigLayout = new LinearLayout(this);
        bigLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout modLayout = new LinearLayout(this);
        LinearLayout smLayout = new LinearLayout(this);
        LinearLayout smLayout2 = new LinearLayout(this);
        LinearLayout firLayout = new LinearLayout(this);
        Button stName = new Button(this);
        stName.setBackgroundColor(Color.BLACK);
        TextView stNum = new TextView(this);
        stNum.setText("155,234");
        stNum.setBackgroundColor(Color.YELLOW);
        TextView stMemo = new TextView(this);
        stMemo.setText("시세 ");
        stMemo.setBackgroundColor(Color.GREEN);

        Button stPrice = new Button(this);
        stPrice.setBackgroundColor(Color.RED);
        stPrice.setHeight(260);

        Button stArrow = new Button(this);
        stArrow.setBackgroundColor(Color.BLUE);
        stArrow.setText("UP");
        stArrow.setHeight(250);



        Button stDiff = new Button(this);
        stDiff.setText("stDiff");
        Button stPer = new Button(this);
        stPer.setText("%");
        smLayout2.setOrientation(LinearLayout.VERTICAL);
        smLayout2.addView(stDiff);
        smLayout2.addView(stPer);

        Button stBong = new Button(this);
        stBong.setBackgroundColor(Color.GREEN);
        stBong.setText("df");



        smLayout.addView(stNum);
        smLayout.addView(stMemo);
        firLayout.setOrientation(LinearLayout.VERTICAL);
        firLayout.addView(stName);
        firLayout.addView(smLayout);

        modLayout.addView(firLayout);
        modLayout.addView(stPrice);
        modLayout.addView(stArrow);
        modLayout.addView(smLayout2);
        modLayout.addView(stBong);
        bigLayout.addView(modLayout);
        baseLayout.addView(bigLayout);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(MainActivity.this, item.getItemId() , Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){

            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Toast.makeText(MainActivity.this, "back" , Toast.LENGTH_SHORT).show();

                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void menuSelected(String item){
        switch (item){
            case "BACK":{
                Toast.makeText(MainActivity.this, "back" , Toast.LENGTH_SHORT).show();
               // finish();
                return;}
            case "SEARCH":{
                Toast.makeText(MainActivity.this, "search" , Toast.LENGTH_SHORT).show();

                return;
            }
            case "SETTING":{
                Toast.makeText(MainActivity.this, "setting" , Toast.LENGTH_SHORT).show();

                return;
            }
        }
    }




    public float dpToPx(float dp){
        float px =  TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                this.getResources().getDisplayMetrics());
        return px;
    }





}