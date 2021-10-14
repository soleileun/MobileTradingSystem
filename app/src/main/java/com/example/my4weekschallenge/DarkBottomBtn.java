package com.example.my4weekschallenge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DarkBottomBtn extends androidx.appcompat.widget.AppCompatButton {

    final Context context = getContext();
    String text;

    public DarkBottomBtn(@NonNull Context context , String text) {
        super(context);
        this.text = text;
        init();
    }

    public DarkBottomBtn(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ResourceType")
    public void init(){
        setText(text);
        setBackgroundResource(Color.TRANSPARENT);
        setTextColor(Color.LTGRAY);
        setHeight(160);

    }

}
