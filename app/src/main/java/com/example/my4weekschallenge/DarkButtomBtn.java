package com.example.my4weekschallenge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DarkButtomBtn extends androidx.appcompat.widget.AppCompatButton {

    final Context context = getContext();
    String text;

    public DarkButtomBtn(@NonNull Context context , String text) {
        super(context);
        this.text = text;
        init();
    }

    public DarkButtomBtn(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        setText(text);
        setBackgroundResource(R.drawable.bottom_button);
        setTextColor(Color.LTGRAY);
    }

}
