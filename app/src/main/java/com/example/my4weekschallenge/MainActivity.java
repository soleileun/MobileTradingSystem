package com.example.my4weekschallenge;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my4weekschallenge.adapter.CellAdapter;
import com.example.my4weekschallenge.data.Itemlist;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;

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
        horizontalScrollView.setBackgroundColor(Color.WHITE);
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
        LinearLayout.LayoutParams cardlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardlp.setMargins(30,40,30,80);
        accLayout.setLayoutParams(cardlp);
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        tp.topMargin = (int) dpToPx(10.f); // 여기에 넣으니까 되네
        tv.setPadding(80,40,80,0); //
        tp.setMargins(20,30,20,20);
//        tv.setBackgroundResource(R.drawable.cardview); //리소스를 어떻게 적용하는지
        tv.setBackgroundColor(Color.WHITE);
        tv.setPadding(10,10,10,10);
        tv.setGravity(Gravity.CENTER);
        tv.setText("ddddddd");

        //스피너
        Spinner sp = new Spinner(this);
        String[] item = {"39002406-010","39002406-011","39002509-010"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = sp;
        sItems.setAdapter(adapter);


        //?? 키보드 제어하는 부분... 내가 키패드 내리고싶어서 어디서 주워온거임 ㅠㅠ
        //키보드 부분도 나중에 정리
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);


        //패스워드입력
        EditText pw = new EditText(this);
        pw.setGravity(Gravity.CENTER);
        pw.setFocusable(true);
        pw.setEms(3);
        pw.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        pw.addTextChangedListener(new TextWatcher() {
            String s;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if(s.length() >4){
                  //  pw.setBackgroundColor(Color.RED);
                   // pw.clearFocus();
                    pw.setText(charSequence.toString().subSequence(0,4));
                }else if(s.length() == 4){
                    //pw.clearFocus();
                   // imm.hideSoftInputFromWindow(pw.getWindowToken(),0);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.6f));
        pw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.3f));
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);
        llp.setMargins(50,30,50,30);
        ll.setGravity(Gravity.CENTER);
        ll.setLayoutParams(llp);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(sp);
        ll.addView(pw);

        tv.setLayoutParams(tp);

        //잔고 카드
        LinearLayout detail = new LinearLayout(this);
        detail.setOrientation(LinearLayout.VERTICAL);
        LinearLayout jango = new LinearLayout(this);
        LinearLayout.LayoutParams ss = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(0,0));

        //물론이져 함수?
        //wrapper class viewgroup??

        TextView money = new TextView(this);
        TextView won = new TextView(this);
        money.setText("9,434,896,489");
        money.setTextSize(30);
        money.setPadding(30,20,30,20);
        won.setText("원");
        won.setTextSize(15);
        jango.addView(money);
        jango.addView(won);
        LinearLayout revPer = new LinearLayout(this);
        revPer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView profit = new TextView(this);
        TextView pro =  new TextView(this);
        revPer.setGravity(Gravity.BOTTOM);
        revPer.setPadding(40,0,20,30);
        profit.setText("24.80");
        profit.setTextSize(15);
        if (Float.parseFloat(profit.getText().toString()) >0){
            profit.setTextColor(Color.RED);
        }else{
            profit.setTextColor(Color.BLUE);
        }
        profit.setText(profit.getText().toString() + "%");
        pro.setText("수익률 ");
        pro.setTextSize(15);
        revPer.addView(pro);
        revPer.addView(profit);

        LinearLayout verll = new LinearLayout(this);
        LinearLayout dll = new LinearLayout(this);
        dll.setOrientation(LinearLayout.HORIZONTAL);
        dll.addView(jango);
        dll.addView(revPer);
        verll.setOrientation(LinearLayout.VERTICAL);
        //verll.addView(ll);
        detail.addView(ll);
        verll.addView(dll);


        LinearLayout.LayoutParams delp = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
        LinearLayout.LayoutParams txlp = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.33f));
        delp.setMargins(10,10,10,20);
        txlp.setMargins(5,5,5,15);
        // 각종 디테일
        LinearLayout line1 = new LinearLayout(this);
        line1.setGravity(Gravity.CENTER | View.TEXT_ALIGNMENT_CENTER);
        TextView tx1 = new TextView(this);
        TextView tx2 = new TextView(this);
        TextView tx3 = new TextView(this);
        tx1.setText("손익금액");
        tx2.setText("매입총액");
        tx3.setText("유가총액");
        line1.addView(tx1);
        line1.addView(tx2);
        line1.addView(tx3);
        tx1.setLayoutParams(txlp); tx1.setGravity(Gravity.CENTER);
        tx2.setLayoutParams(txlp); tx2.setGravity(Gravity.CENTER);
        tx3.setLayoutParams(txlp); tx3.setGravity(Gravity.CENTER);
        LinearLayout line2 = new LinearLayout(this);
        TextView val1 = new TextView(this);
        TextView val2 = new TextView(this);
        TextView val3 = new TextView(this);
        val1.setText("55,378,655");
        val2.setText("562,268,295");
        val3.setText("617,646,950");
        line2.addView(val1);
        line2.addView(val2);
        line2.addView(val3);
        val1.setLayoutParams(txlp); val1.setGravity(Gravity.CENTER);
        val2.setLayoutParams(txlp); val2.setGravity(Gravity.CENTER);
        val3.setLayoutParams(txlp); val3.setGravity(Gravity.CENTER);
        LinearLayout line3 = new LinearLayout(this);
        TextView tx4 = new TextView(this);
        TextView tx5 = new TextView(this);
        TextView tx6 = new TextView(this);
        tx4.setText("전일대비평가");
        tx5.setText("당일실현손익");
        tx6.setText("예수금");
        tx4.setLayoutParams(txlp); tx4.setGravity(Gravity.CENTER);
        tx5.setLayoutParams(txlp); tx5.setGravity(Gravity.CENTER);
        tx6.setLayoutParams(txlp); tx6.setGravity(Gravity.CENTER);
        line3.addView(tx4);line3.addView(tx5);line3.addView(tx6);
        LinearLayout line4 = new LinearLayout(this);
        TextView val4 = new TextView(this);
        TextView val5 = new TextView(this);
        TextView val6 = new TextView(this);
        val4.setText("-8,311,056");
        val5.setText("0");
        val6.setText("8,817,249,539");
        val4.setLayoutParams(txlp); val4.setGravity(Gravity.CENTER);
        val5.setLayoutParams(txlp); val5.setGravity(Gravity.CENTER);
        val6.setLayoutParams(txlp); val6.setGravity(Gravity.CENTER);
        line4.addView(val4); line4.addView(val5); line4.addView(val6);

        verll.addView(line1);
        verll.addView(line2);
        verll.addView(line3);
        verll.addView(line4);
        detail.addView(verll);


        accLayout.addView(detail);


        //baseLayout.addView(accLayout);



        //라인과 버튼

        TextView line = new TextView(this);

        line.setHeight((int) dpToPx(1));
        line.setBackgroundColor(Color.DKGRAY);
        accLayout.addView(line);


        //상세보기
        TextView btn = new TextView(this);
        btn.setText("상세보기");
        btn.setClickable(false);
        btn.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams dell = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dell.setMargins(10,10,10,10);
        btn.setPadding(10,10,10,10);
        btn.setLayoutParams(dell);
      //  baseLayout.addView(btn);
        accLayout.addView(btn);
        btn.setGravity(Gravity.CENTER);
        //addView를 해서 paramtext
        accLayout.setBackgroundResource(R.drawable.cardview);
        accLayout.setTop(200);
        baseLayout.addView(accLayout);
        verll.setVisibility(View.GONE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = verll.getVisibility();
                if(flag == 0){ // 보이는 경우에는
                    verll.setVisibility(View.GONE);
                    btn.setText("상세보기");
                }else if(flag == 8){ // 안보이는 경우에는
                    verll.setVisibility(View.VISIBLE);
                    btn.setText("접기");
                }
            }
        });

        pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // 텍스트 내용을 가져온다.
                String searchData = textView.getText().toString();

                // 텍스트 내용이 비어있다면...
                if (searchData.length() <4) {

                    // 토스트 메세지를 띄우고, 창 내용을 비운다
                    Toast.makeText(MainActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    textView.clearFocus();
                    textView.setFocusable(false);
                    textView.setFocusableInTouchMode(true);
                    textView.setFocusable(true);
                    btn.setClickable(false);
                    return true;
                }

                switch (i) {

                    // Search 버튼일경우
                    case EditorInfo.IME_ACTION_SEARCH:

                        break;

                    // Enter 버튼일경우
                    default:

                        btn.setClickable(true);

                        return false;
                }

                // 내용 비우고 다시 이벤트 할수있게 선택
                textView.clearFocus();
                textView.setFocusable(false);
                textView.setText("");
                textView.setFocusableInTouchMode(true);
                textView.setFocusable(true);

                return true;


            }
        });

//        baseLayout.addView(new StockCell(this));

        ArrayList<Itemlist> ai = new ArrayList<>();
        ai.add(new Itemlist("dd","ff","4040","aa","ff","zz","up"));
        ai.add(new Itemlist("dd0092u72","ff","4040","aa","ff","zz","down"));

        CellAdapter cellAdapter = new CellAdapter(ai);
        ListView lv = new ListView(this);
        lv.setAdapter(cellAdapter);
        baseLayout.addView(lv);





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