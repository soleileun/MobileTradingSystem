package com.example.my4weekschallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my4weekschallenge.adapter.ScrollViewAdapter;
import com.example.my4weekschallenge.data.Itemlist;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private GridView gridview = null;
    private GridViewAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new GridViewAdapter();

        //Adapter 안에 아이템의 정보 담기
        adapter.addItem(new Itemlist("naver", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("kakao", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("TI", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));
        adapter.addItem(new Itemlist("tlqkf", "13,232,111", "22,222","3,000","4.45%","red","up"));

        gridview.setAdapter(adapter);

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


    class GridViewAdapter extends BaseAdapter {
        ArrayList<Itemlist> items = new ArrayList<>();
        Context context;

        public void addItem(Itemlist item){
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int idx) {
            return items.get(idx);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            String TAG = MainActivity.class.getSimpleName();
            final Context context = viewGroup.getContext();
            final Itemlist item = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gridcell, viewGroup, false);

                TextView name = (TextView) convertView.findViewById(R.id.name);
                TextView tradeNum = (TextView) convertView.findViewById(R.id.tradeNum);

                tradeNum.setText(item.getTradeNum());
                name.setText(item.getName());
                Log.d(TAG, "getView() - [ "+position+" ] "+item.getName());

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, item.getTradeNum()+" 번 - "+item.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }
}