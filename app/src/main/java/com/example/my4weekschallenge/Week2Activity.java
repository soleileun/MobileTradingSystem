package com.example.my4weekschallenge;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my4weekschallenge.adapter.CellAdapter;
import com.example.my4weekschallenge.data.Itemlist;
import com.example.my4weekschallenge.manager.LayoutManager;

import java.io.InputStream;
import java.util.ArrayList;

public class Week2Activity extends AppCompatActivity {

//    @SuppressLint("ResourceType")
//    @RequiresApi(api = Build.VERSION_CODES.N) //N -> Nougat버전의
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_main);
        // activity_main.xml로 화면을 표시한다 하지만 안할거니까!
        //레이아웃 파라미터 종류는 위젯이 아니라 소속되어 있는 부모 레이아웃에 따라 달라진다.
        LayoutManager.getInstance();
        //레이아웃 파라미터 객체 생성
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

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
        //toolbar.inflateMenu(R.menu.cstoolbar); // 서랍 아이콘으로 하는 방법

        LinearLayout btnlayout = new LinearLayout(this);
        //이렇게 해야 좌측으로 배치가능 나를 누구 기준으로 배치할거냐 생각을 해보면 됨
        btnlayout.setLayoutParams(new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                ));

//        Button search = new Button(this);
//        search.setText("검색");
//        btnlayout.addView(search);
//        Button setting = new Button(this);
//        setting.setText("환경설정");
//        btnlayout.addView(setting);
//        toolbar.addView(btnlayout);


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
//        horizontalScrollView.setBackgroundColor(Color.WHITE);
//        horizontalScrollView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //기본값 ~
        horizontalScrollView.setHorizontalScrollBarEnabled(false); //스크롤바 없애는 옵션
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));
        horizontalScrollView.setPadding(20,0,0,0);
        LinearLayout hscrollBtnLayout = new LinearLayout(this);
        hscrollBtnLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,140));
        hscrollBtnLayout.setOrientation(LinearLayout.HORIZONTAL);
        RoundButton scrollBtn1 = new RoundButton(this,"최근조회종목");
        RoundButton scrollBtn2 = new RoundButton(this,"보유종목(국내)");
        RoundButton scrollBtn3 = new RoundButton(this,"보유종목(해외)");
        RoundButton scrollBtn4 = new RoundButton(this,"특징종목");
        RoundButton scrollBtn5 = new RoundButton(this,"기본그룹");

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
                160,
                LinearLayout.LayoutParams.MATCH_PARENT));
        Button btnX = new Button(this);
        btnXLayout.setGravity(Gravity.END);
        btnX.setText("X");
        btnXLayout.addView(btnX);
        btnX.setBackgroundColor(Color.WHITE);
        scrollLayout.addView(btnXLayout);
        baseLayout.addView(scrollLayout);

        // 상세보기 부분 구현
        LinearLayout accLayout = new LinearLayout(this);
        accLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams cardlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardlp.setMargins(30,40,30,80);
        accLayout.setLayoutParams(cardlp);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        tp.topMargin = (int) dpToPx(10.f); // 여기에 넣으니까 되네
        tp.setMargins(20,30,20,20);

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
        pw.setBackgroundResource(R.drawable.button);
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
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(50,30,50,30);
        ll.setGravity(Gravity.CENTER);
        ll.setLayoutParams(llp);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(sp);
        ll.addView(pw);

        //잔고 카드
        LinearLayout detail = new LinearLayout(this);
        detail.setOrientation(LinearLayout.VERTICAL);
        LinearLayout jango = new LinearLayout(this);
        LinearLayout.LayoutParams ss = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(0,0));

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
        tx1.setText("손익금액"); line1.addView(tx1);
        tx2.setText("매입총액"); line1.addView(tx2);
        tx3.setText("유가총액");line1.addView(tx3);
        tx1.setLayoutParams(txlp); tx1.setGravity(Gravity.CENTER);
        tx2.setLayoutParams(txlp); tx2.setGravity(Gravity.CENTER);
        tx3.setLayoutParams(txlp); tx3.setGravity(Gravity.CENTER);
        LinearLayout line2 = new LinearLayout(this);
        TextView val1 = new TextView(this);
        TextView val2 = new TextView(this);
        TextView val3 = new TextView(this);
        val1.setText("55,378,655"); line2.addView(val1);
        val2.setText("562,268,295"); line2.addView(val2);
        val3.setText("617,646,950"); line2.addView(val3);
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


        //라인과

        TextView line = new TextView(this);
        line.setHeight((int) dpToPx(1));
        line.setBackgroundColor(Color.DKGRAY);
        accLayout.addView(line);


        //상세보기
        TextView btn = new TextView(this);
        btn.setText("상세보기");
        btn.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams dell = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dell.setMargins(10,10,10,10);
        btn.setPadding(10,10,10,10);
        dell.gravity = Gravity.CENTER;
        btn.setLayoutParams(dell);

        accLayout.addView(btn);
        btn.setGravity(Gravity.CENTER);
        accLayout.setBackgroundResource(R.drawable.cardview);
        accLayout.setTop(200);
        baseLayout.addView(accLayout);
        verll.setVisibility(View.GONE);

        btn.setClickable(false);
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
                btn.setClickable(false);
                // 텍스트 내용이 비어있다면...
                if (searchData.length() <4) {

                    // 토스트 메세지를 띄우고, 창 내용을 비운다
                    Toast.makeText(Week2Activity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    textView.clearFocus();
                    textView.setFocusable(false);
                    textView.setFocusableInTouchMode(true);
                    //textView.setFocusable(true);
                    btn.setClickable(false);
                    return true;
                }else if (searchData.length() == 4){
                    btn.setClickable(true);
                    return true;
                }


                // 내용 비우고 다시 이벤트 할수있게 선택
                textView.clearFocus();
                textView.setFocusable(false);
                textView.setText("");
                textView.setFocusableInTouchMode(true);
                textView.setFocusable(true);
                btn.setClickable(true);
                return true;


            }
        });

//        baseLayout.addView(new StockCell(this));

        ArrayList<Itemlist> list = loadItemsFromFile();

        CellAdapter cellAdapter = new CellAdapter(list);
        ListView listView = new ListView(this);
        listView.setBackgroundResource(R.drawable.line);
        listView.setAdapter(cellAdapter);
        baseLayout.addView(listView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,1));
        listView.getOnItemClickListener();

        //하단 메뉴 바
        LinearLayout btmlayout = new LinearLayout(this);
        DarkBottomBtn btmMenu = new DarkBottomBtn(this,"메뉴");
        btmMenu.setGravity(Gravity.CENTER);
//        bm.setHeight(60);
        HorizontalScrollView bhview = new HorizontalScrollView(this);
        DarkBottomBtn bbtn1 = new DarkBottomBtn(this,"홈");
        DarkBottomBtn bbtn2 = new DarkBottomBtn(this,"현재가");
        DarkBottomBtn bbtn3 = new DarkBottomBtn(this,"주식주문");
        DarkBottomBtn bbtn4 = new DarkBottomBtn(this,"주식잔고");
        DarkBottomBtn bbtn5 = new DarkBottomBtn(this,"지수종합");

        LinearLayout BtmBtns = new LinearLayout(this);
        BtmBtns.addView(bbtn1);
        BtmBtns.addView(bbtn2);
        BtmBtns.addView(bbtn3);
        BtmBtns.addView(bbtn4);
        BtmBtns.addView(bbtn5);
        bhview.addView(BtmBtns);
        btmlayout.addView(btmMenu);
        btmlayout.addView(bhview);
//        bl.setGravity(Gravity.BOTTOM);
        LinearLayout.LayoutParams lpppp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        lpppp.gravity = Gravity.BOTTOM | Gravity.END ;
        btmlayout.setLayoutParams(lpppp);
        btmlayout.setBackgroundColor(Color.DKGRAY
        );
        baseLayout.addView(btmlayout);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(Week2Activity.this, item.getItemId() , Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){

            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Toast.makeText(Week2Activity.this, "back" , Toast.LENGTH_SHORT).show();

                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void menuSelected(String item){
        switch (item){
            case "BACK":{
                Toast.makeText(Week2Activity.this, "back" , Toast.LENGTH_SHORT).show();
               // finish();
                return;}
            case "SEARCH":{
                Toast.makeText(Week2Activity.this, "search" , Toast.LENGTH_SHORT).show();

                return;
            }
            case "SETTING":{
                Toast.makeText(Week2Activity.this, "setting" , Toast.LENGTH_SHORT).show();

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
    private ArrayList<Itemlist> loadItemsFromFile() {

        AssetManager am = getAssets();
//        AssetManager am = getResources().getAssets() ;
        ArrayList<Itemlist> items = new ArrayList<>();
        InputStream is = null;
        byte buf[] = new byte[1024];
        String text = "";

        try {
            is = am.open("stock.txt");

            if (is.read(buf) > 0) {
                text = new String(buf);
            }
            String[] str = text.split("\n");
            for(int i=0;i<str.length;i++){
                String[] s = str[i].split("\\|");
                items.add(new Itemlist(s[0],s[1],s[2],s[3],s[4],s[5]));
//                Log.d("plz",s[0] + "OK");
            }

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return items;
    }


}