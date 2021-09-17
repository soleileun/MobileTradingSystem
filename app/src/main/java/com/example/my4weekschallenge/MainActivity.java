package com.example.my4weekschallenge;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(255, 255, 255));
        setContentView(baseLayout, params);


        //        toolbar = new Toolbar(getApplicationContext());
//        Toolbar toolbar = new Toolbar(getBaseContext());
//        Toolbar toolbar = new Toolbar(getApplication());
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("보유종목(국내)");
        toolbar.setTitleTextColor(Color.BLACK);
        this.setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionbar.
        baseLayout.addView(toolbar);

        // this가 뭔가요?

        Button btn = new Button(this);
        btn.setText("버튼입니다");
        btn.setId(Integer.parseInt("11"));
        btn.setBackgroundColor(Color.BLUE);
        baseLayout.addView(btn);
        //addView를 해서 paramtext

        TextView text = new TextView(this);
        text.setText("내가 했다구! ");
        text.setTextColor(Color.RED);
        text.setTextSize(18);
        baseLayout.addView(text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "코드로 생성한 버튼입니다." +btn.getId(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}