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
        adapter.addItem(new Itemlist("SK하이닉스", "1,437,371", "106,000","1,500","1.45%","red","up"));
        adapter.addItem(new Itemlist("현대차", "478,404", "205,000","3,200","4.45%","blue","down"));
        adapter.addItem(new Itemlist("하나금융지주", "1,111", "46,000","15,000","4.60%","red","up"));
        adapter.addItem(new Itemlist("NAVER", "2,321", "405,000","2,000","1.25%","blue","down"));
        adapter.addItem(new Itemlist("POSCO", "132,811", "373,000","13,000","2,25%","red","up"));
        adapter.addItem(new Itemlist("CJ제일제당", "8,011", "22,200","7,100","3.40%","red","up"));
        adapter.addItem(new Itemlist("LG화학", "2,327", "23,100","1,150","1,90%","red","up"));
        adapter.addItem(new Itemlist("기아", "11", "123,700","3,000","0.45%","blue","down"));
        adapter.addItem(new Itemlist("현대모비스", "3,232,111", "60,000","13,600","17.45%","blue","down"));
        adapter.addItem(new Itemlist("삼성생명", "20,665", "78,000","3,000","9.50%","red","up"));
        adapter.addItem(new Itemlist("BYC", "45,467", "12,800","400","11.00%","red","up"));
        adapter.addItem(new Itemlist("대한항공", "1,112", "7,310","950","8.45%","blue","down"));
        adapter.addItem(new Itemlist("삼성전자", "7,423", "10,100","2,400","4.00%","red","up"));
        adapter.addItem(new Itemlist("판타지오", "1,764", "103","2","0.15%","red","up"));
        adapter.addItem(new Itemlist("신한금융지주 ", "3,632,188", "79,000","5,000","4.45%","red","up"));

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
                TextView prc = convertView.findViewById(R.id.stockprc);
                TextView arrow  = convertView.findViewById(R.id.updown);
                TextView diff  = convertView.findViewById(R.id.diff);
                TextView prcnt  = convertView.findViewById(R.id.prcnt);


                tradeNum.setText(item.getTradeNum());
                name.setText(item.getName());
                prc.setText(item.getPrice());
                arrow.setText(item.getArrow());
                diff.setText(item.getDiff());
                prcnt.setText(item.getPercent());
                Log.d(TAG, "getView() - [ "+position+" ] "+item.getName());

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, item.getPrice()+"원 - "+item.getName()+"! ", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }
}