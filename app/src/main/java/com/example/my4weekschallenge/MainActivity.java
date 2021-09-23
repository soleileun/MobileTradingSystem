package com.example.my4weekschallenge;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;

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
        //이런식으로 크기를 결정한다
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        // 전체 배경
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(255, 255, 255));
        setContentView(baseLayout, params);

//        Toolbar toolbar = new Toolbar(getApplicationContext());
//        Toolbar toolbar = new Toolbar(getBaseContext());
//        Toolbar toolbar = new Toolbar(getApplication());
        // 툴바
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("보유종목(국내)");

        toolbar.setTitleTextColor(Color.BLACK);
        this.setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        //actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징

        // 툴바 좌측 아이콘 추가 (버튼으로 구현)
        // 다양한 시도 실패중... 하...
      //  toolbar.inflateMenu(R.menu.cstoolbar); // 서랍 아이콘으로 만들어버리기 ㅡㅡ
        LinearLayout btnlayout = new LinearLayout(this);
//        btnlayout.setLayoutParams(lp);

        Button btn1 = new Button(this);
        btn1.setText("검색");
        btnlayout.setBackgroundColor(Color.rgb(121,222,10));
        btnlayout.addView(btn1);
        //toolbar.addView(btn1);
        Button btn2 = new Button(this);
        btn2.setText("환경설정");
//        btn2.setLayoutParams(lp);
        btnlayout.addView(btn2);
        //toolbar.addView(btn2);
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
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        scrollLayout.setOrientation(LinearLayout.HORIZONTAL);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        horizontalScrollView.setBackgroundColor(Color.rgb(1,23,45));
//        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(1000,160));
        LinearLayout lin = new LinearLayout(this);
        Button btn4 = new Button(this);
        btn4.setText("bsbbasdfbadf");
        Button btn5 = new Button(this);
        btn5.setText("dmfaaaaaaaaaaaaaaafs");
        Button btn3 = new Button(this);
        btn3.setText("tlqkasdfasdfhdi");
        lin.addView(btn4);
        lin.addView(btn5);
        lin.addView(btn3);

        horizontalScrollView.addView(lin);
        scrollLayout.addView(horizontalScrollView);
        //수평 스크롤 옆 X 버튼
        LinearLayout btnXLayout = new LinearLayout(this);

        Button btnX = new Button(this);
        btnX.setText("X");
        btnXLayout.addView(btnX);
        btnX.setHeight(50);
        btnX.setWidth(50);
        btnXLayout.setGravity(Gravity.END);

        scrollLayout.addView(btnXLayout);
        baseLayout.addView(scrollLayout);




//        View card = new View(this);
//        card.setBackgroundColor(Color.CYAN);
        TextView tv = new TextView(this);

        ViewGroup.LayoutParams tp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.CYAN);
        tv.setLayoutParams(tp);
        baseLayout.addView(tv);

        LinearLayout linear = new LinearLayout(this);
        linear.setGravity(Gravity.CENTER);
        linear.setBackgroundColor(Color.RED);
        baseLayout.addView(linear);
        //상세보기
        Button btn = new Button(this);
        btn.setText("상세보기");
//        btn.setSingleLine();

        btn.setBackgroundColor(Color.BLUE);
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

        TextView space = new TextView(this);
        space.setTextSize(20);
        baseLayout.addView(space);

        baseLayout.addView(new Button(this));
    // 셀을 만들어볼것인데 맘 처럼 잘 안됨 ㅠ

        LinearLayout bigLayout = new LinearLayout(this);





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


}