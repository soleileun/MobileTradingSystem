package com.example.my4weekschallenge;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
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

//        Toolbar toolbar = new Toolbar(getApplicationContext());
//        Toolbar toolbar = new Toolbar(getBaseContext());
//        Toolbar toolbar = new Toolbar(getApplication());
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("보유종목(국내)");
        toolbar.setTitleTextColor(Color.BLACK);
        this.setActionBar(toolbar);
        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
      //  toolbar.inflateMenu(R.menu.cstoolbar); // 서랍 아이콘으로 만들어버리기 ㅡㅡ
        Button btn1 = new Button(this);
        btn1.setText("검색");
        toolbar.addView(btn1);
        Button btn2 = new Button(this);
        btn2.setText("환경설정");
        toolbar.addView(btn2);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuSelected("BACK");
              }
        });
        baseLayout.addView(toolbar);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout lin = new LinearLayout(this);
        Button btn4 = new Button(this);
        btn4.setText("bsbbsdbsbdabsdbfbasdbfsabdf");
        Button btn5 = new Button(this);
        btn5.setText("dmswjdmsdfajsdfasdfs");
        Button btn3 = new Button(this);
        btn3.setText("tlqkf......dlrpahdi");
        lin.addView(btn4);
        lin.addView(btn5);
        lin.addView(btn3);

        horizontalScrollView.addView(lin);
        baseLayout.addView(horizontalScrollView);
        Button btnX = new Button(this);
        btnX.setText(" X ");
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnX.setLayoutParams(lp);

        baseLayout.addView(btnX);

        //baseLayout.addView(btnX,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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