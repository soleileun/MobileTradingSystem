package com.example.my4weekschallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.my4weekschallenge.adapter.ScrollViewAdapter;
import com.example.my4weekschallenge.data.Itemlist;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        ListView scrollView = (ListView) findViewById(R.id.scrollView);
        ScrollViewAdapter adapter = new ScrollViewAdapter();
        ListView.setAdapter(adapter);
        ArrayList<Itemlist> arr = new ArrayList<>();

        adapter.addItem(new Itemlist("haha","122,222", "233,222","2,000","1.38%","#656565",">"));
        adapter.addItem(new Itemlist("NAVER","122,222", "233,222","2,000","1.38%","#656565","<"));
        adapter.addItem(new Itemlist("KAKAO","122,222", "233,222","2,000","1.38%","#656565",">"));


        
        findViewById(R.id.button).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
            case R.id.rightButton: { // 오른쪽 상단 버튼 눌렀을 때
                Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show();
            }
            case R.id.nextRightButton: { // 오른쪽 상단 버튼 눌렀을 때
                Toast.makeText(this, "환경설정", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }


}