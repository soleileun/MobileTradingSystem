package com.example.my4weekschallenge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.my4weekschallenge.data.Itemlist;

import java.util.ArrayList;

public class StockCell extends LinearLayout {

    final Context context = getContext();

    Itemlist item;


    public StockCell(Context context,Itemlist item) {
        super(context);
        this.item = item;
        init();
    }

    public StockCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        //셀을 생성하는 코드

        LinearLayout tl = new LinearLayout(context);
        LinearLayout b1 = new LinearLayout(context);
        tl.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        TextView stName = new TextView(context);
        stName.setText(this.item.getName());
        stName.setTextSize(24);
        TextView stNum = new TextView(context);
        stNum.setText(item.getTradeNum());
        TextView stMemo = new TextView(context);
        //stMemo.setText("지연시세");
        //stMemo.setBackgroundColor(Color.GREEN);
        stMemo.setGravity(Gravity.END);
        stMemo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout fl1 = new LinearLayout(context);
        LinearLayout fl2 = new LinearLayout(context);
        b1.setOrientation(LinearLayout.VERTICAL);
        fl2.addView(stNum);
        fl2.addView(stMemo);
        b1.addView(stName);
        b1.addView(fl2);
        b1.setPadding(50,0,0,0);
        b1.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1.2f));
        tl.addView(b1);


        LinearLayout b2 = new LinearLayout(context);
        //b2.setBackgroundColor(Color.RED);
        b2.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1.0f));


        TextView stPrice = new TextView(context);
        //stPrice.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        stPrice.setText(item.getPrice());

        stPrice.setTextSize(30);
        stPrice.setLayoutParams(plp);
        stPrice.setGravity(Gravity.END);
        b2.setGravity(Gravity.END
        );
        b2.addView(stPrice);
        LinearLayout.LayoutParams nlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);



        TextView stArrow = new TextView(context);
        //stArrow.setBackgroundColor(Color.BLUE);
        stArrow.setText(item.getArrow());
        stArrow.setPadding(10,0,10,0);


        stArrow.setGravity(Gravity.CENTER);
        stArrow.setLayoutParams(nlp);

        tl.addView(b2);

        LinearLayout b3 = new LinearLayout(context);
        //   b3.setBackgroundColor(Color.CYAN);
        b3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        b3.addView(stArrow);
        tl.addView(b3);

        TextView stDiff = new TextView(context);
        stDiff.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        stDiff.setGravity(Gravity.CENTER_VERTICAL);
        //stDiff.setBackgroundColor(Color.DKGRAY);
        stDiff.setText("3,700");
        TextView stPer = new TextView(context);
        stPer.setText("3.26%");
        stPer.setGravity(Gravity.CENTER_VERTICAL);
        stPer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        LinearLayout sl2 = new LinearLayout(context);
        sl2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        sl2.setOrientation(LinearLayout.VERTICAL);
        sl2.setGravity(Gravity.CENTER_VERTICAL);
        sl2.addView(stDiff);
        sl2.addView(stPer);
        b3.setGravity(Gravity.CENTER_VERTICAL);
        b3.addView(sl2);
//
        TextView stBong = new TextView(context);
        stBong.setGravity(Gravity.CENTER);
        stBong.setText(" | ");
        stBong.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        stBong.setPadding(10,0,50,0);
        b3.addView(stBong);

        tl.setPadding(0,10,0,10);
//        tl.setBackgroundResource(R.drawable.line);


        if(item.getArrow().toLowerCase().equals("up")){
            stArrow.setText("▲");
          //  stName.setTextColor(Color.RED);
          //  stNum.setTextColor(Color.RED);
            stDiff.setTextColor(Color.RED);
            stArrow.setTextColor(Color.RED);
            stPrice.setTextColor(Color.RED);
            stPer.setTextColor(Color.RED);

        }else if(item.getArrow().toLowerCase().equals("down")){
            stArrow.setText("▼");
          //  stName.setTextColor(Color.BLUE);
          //  stNum.setTextColor(Color.BLUE);
            stDiff.setTextColor(Color.BLUE);
            stArrow.setTextColor(Color.BLUE);
            stPrice.setTextColor(Color.BLUE);
            stPer.setTextColor(Color.BLUE);

        }



        addView(tl);

    }

    public void setColor(){

    }





}

