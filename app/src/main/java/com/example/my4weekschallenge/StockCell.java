package com.example.my4weekschallenge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.my4weekschallenge.data.Itemlist;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StockCell extends LinearLayout {

    private Context context = getContext();
    private TextView stName;
    private TextView stNum;
    private TextView stPrice;
    private TextView stArrow;
    private TextView stPer;
    private TextView stDiff;

    public TextView getStPer() {
        return stPer;
    }

    public void setStPer(TextView stPer) {
        this.stPer = stPer;
    }

    public TextView getStDiff() {
        return stDiff;
    }

    public void setStDiff(TextView stDiff) {
        this.stDiff = stDiff;
    }

    private Itemlist item;

    public TextView getStName() {
        return stName;
    }

    public void setStName(TextView stName) {
        this.stName = stName;
    }

    public TextView getStNum() {
        return stNum;
    }

    public void setStNum(TextView stNum) {
        this.stNum = stNum;
    }

    public TextView getStPrice() {
        return stPrice;
    }

    public void setStPrice(TextView stPrice) {
        this.stPrice = stPrice;
    }

    public TextView getStArrow() {
        return stArrow;
    }

    public void setStArrow(TextView stArrow) {
        this.stArrow = stArrow;
    }

    public Itemlist getItem() {
        return item;
    }

    public void setItem(Itemlist item) {
        this.item = item;
    }

    public StockCell(Context context, Itemlist item) {
        super(context);
        this.item = item;
        init();
        setControl(item);

    }

    public void setControl(Itemlist item){
        Log.d("euneun" , item.toString());

        stName.setText(item.getName());
        stNum.setText(item.getTradeNum());
        stPrice.setText(item.getPrice());
        stArrow.setText(item.getArrow());
        stPer.setText(item.getPercent() + "%");
        stDiff.setText(item.getDiff());

        if(item.getArrow().toLowerCase().equals("up")){
            stArrow.setText("▲");
            stDiff.setTextColor(Color.RED);
            stArrow.setTextColor(Color.RED);
            stPrice.setTextColor(Color.RED);
            stPer.setTextColor(Color.RED);

        }else if(item.getArrow().toLowerCase().equals("down")){
            stArrow.setText("▼");
            stDiff.setTextColor(Color.BLUE);
            stArrow.setTextColor(Color.BLUE);
            stPrice.setTextColor(Color.BLUE);
            stPer.setTextColor(Color.BLUE);

        }

    }


   public StockCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    private void initCell(Context context) {

    }

    public void init(){
        //셀을 생성하는 코드

        LinearLayout tl = new LinearLayout(context);
        LinearLayout b1 = new LinearLayout(context);
        tl.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        stName = new TextView(context);
        stName.setTextSize(24);
        stNum = new TextView(context);
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


         stPrice = new TextView(context);
        //stPrice.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        stPrice.setTextSize(30);
        stPrice.setLayoutParams(plp);
        stPrice.setGravity(Gravity.END);
        b2.setGravity(Gravity.END
        );
        b2.addView(stPrice);
        LinearLayout.LayoutParams nlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);



         stArrow = new TextView(context);
        //stArrow.setBackgroundColor(Color.BLUE);
        stArrow.setPadding(10,0,10,0);


        stArrow.setGravity(Gravity.CENTER);
        stArrow.setLayoutParams(nlp);

        tl.addView(b2);

        LinearLayout b3 = new LinearLayout(context);
        //   b3.setBackgroundColor(Color.CYAN);
        b3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        b3.addView(stArrow);
        tl.addView(b3);

        stDiff = new TextView(context);
        stDiff.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        stDiff.setGravity(Gravity.CENTER_VERTICAL);
        //stDiff.setBackgroundColor(Color.DKGRAY);
        stPer = new TextView(context);
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


        addView(tl);
    }


}

