package com.example.my4weekschallenge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RoundButton extends androidx.appcompat.widget.AppCompatButton {


    final Context context = getContext();
    private String text = "";

    public RoundButton(Context context, String text) {
        super(context);
        this.text = text;
        init();
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){

        setBackgroundResource(R.drawable.button);
        setText(text);
        setPadding(20,0,20,0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0,20,0);
        setLayoutParams(lp);
    }
}
