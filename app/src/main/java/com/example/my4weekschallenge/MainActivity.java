package com.example.my4weekschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.my4weekschallenge.data.LinearLayoutData;
import com.example.my4weekschallenge.data.RootData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout baselayout = new LinearLayout(this);
        Button btn = new Button(this);
        btn.setText("Here");
        TextView tv = new TextView(this);

        try {
            Log.d("test1", "START!");
            setContentView(readJsonFile(baselayout));
        } catch (IOException e) {
            Log.e("test1", e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("test1", e.toString());
        }

    }

    private View readJsonFile(LinearLayout view) throws IOException, JSONException {

        AssetManager assetManager = getAssets();
        InputStream is = assetManager.open("test.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        String line = br.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = br.readLine();
        }
        String jsonData = sb.toString();
        //JSON Data & 베이스 레이아웃을 가지고 함수로 들어간다.

        return testFun(jsonData, view);

    }

    public LinearLayout testFun(String jsondata, LinearLayout layout) throws JSONException {
        ///Json객체를 파싱하기 위한 구글 json파싱 라이브러리
        Gson gson = new Gson();

        RootData rootData = gson.fromJson(jsondata, RootData.class);

        layout.setOrientation(Integer.valueOf(rootData.getOrientation()));
        LinearLayout temp = null;
        for ( int i = 0; i < rootData.getArr().size(); i++ ) {
            LinearLayoutData vData = rootData.getArr().get(i);
            Log.d("asdf", vData.getType());
            temp = checkChild(vData, new LinearLayout(this));
            layout.addView(temp);

        }
        return layout;

    }


    public LinearLayout checkChild(LinearLayoutData viewData, LinearLayout layout) {
        if (viewData.getType().equals("toolbar")) {
            Toolbar toolbar = new Toolbar(this);
            toolbar.setTitle(viewData.getText());
            toolbar.setTitleTextColor(Color.BLACK);
            this.setActionBar(toolbar);
            ActionBar actionBar = getActionBar();
            toolbar.setLayoutParams(new LinearLayout.LayoutParams(Integer.valueOf(viewData.getWidth()), Integer.valueOf(viewData.getHeight())));
            actionBar.setDisplayHomeAsUpEnabled(true);
            layout.addView(toolbar);
            LinearLayout temp = new LinearLayout(this);
            for ( int i = 0; i < viewData.getArr().size(); i++ ) {
                Log.d("asdf", "inner ->" + viewData.getArr().get(i).getType());
                temp = checkChild(viewData.getArr().get(i), temp);
            }
            temp.setGravity(Gravity.RIGHT);
            if (temp != null){
                temp.setLayoutParams(new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                ));
                toolbar.addView(temp);
            }


        } else if (viewData.getType().equals("LinearLayout")) {
            LinearLayout tmpLayout = new LinearLayout(this);
            Log.d("asdf", "????" + viewData.getArr().size());
            for ( int i = 0; i < viewData.getArr().size(); i++ ) {
                tmpLayout = checkChild(viewData.getArr().get(i), tmpLayout);
                Log.d("asdf","hey!!! " + tmpLayout.getChildCount());
            }
            if (tmpLayout != null){
//                layout.addView(tmpLayout);
                Log.d("asdf","여기들어가?");
                if(viewData.getGravity() != null)
                    tmpLayout.setGravity(setVGravity(viewData.getGravity()));
                return tmpLayout;
            }

        } else if (viewData.getType().equals("ListView")) {

        } else { // control인 경우로 생각
            Log.d("asdf", "control : " + viewData.getType());
            if (viewData.getType().equals("Button")) {
                Button button = new Button(this);
                button.setText(viewData.getText());
                if (viewData.getColor() != null) {
                    Log.d("asdf", "start");
                    button.setBackgroundColor(setColor(viewData.getColor()));
                }
                layout.addView(button);
                return layout;
            }

        }


        return layout;
    }

    public int setColor(String color) {
        switch (color) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "black":
                return Color.BLACK;
            case "cyan":
                return Color.CYAN;
            case "gray":
                return Color.DKGRAY;
            case "magenta":
                return Color.MAGENTA;
        }
        return Color.rgb(66, 00, 66);
    }

    public int setWH(String s) {
        switch (s) {
            case "WRAP":
                return LinearLayout.LayoutParams.WRAP_CONTENT; // -2
            case "PARENT":
                return LinearLayout.LayoutParams.MATCH_PARENT; // -1
            default:
                return Integer.valueOf(s);
        }
    }

    public int setVGravity(String s){
        switch(s){
            case "right":
                return Gravity.RIGHT;
            case "left" :
                return Gravity.LEFT;
            case "top" :
                return Gravity.TOP;
            case "bottom":
                return Gravity.BOTTOM;
            case "center" :
                return Gravity.CENTER;
        }
        return 0;
    }



    //-------------------- 사용하지 않는 함수 -------------------------------

    public View drawViews(JSONObject jsonObject, LinearLayout layout) throws JSONException {

        //json object  에 names 확인
        //names 에 있는 친구들 값이 json인지 확인
        // json이라면 재귀? ㅠ
        // 아니라면 상위 레이아웃에 추가?

        String s = jsonObject.names().toString();
        JSONArray jsonArray = jsonObject.names();
        Log.d("test1", s);
        Log.d("test1", String.valueOf(jsonArray.length()));
        for ( int i = 0; i < jsonArray.length(); i++ ) {
            JSONObject json = jsonObject.getJSONObject(jsonArray.get(i).toString());
            JSONArray array = json.names();
            String ss = array.toString();
            Log.d("test1", ss + ss.length());
            //items 항목의 값이 json이거나 배열이거나 하면 다시 재귀 걸고
            //아니라면 레이아웃에 넣으세욧!

            for ( int j = 0; j < array.length(); j++ ) {
                String k = array.get(j).toString();
                //  Log.d("test1", array.get(j).toString()+"  : "+ json.get(k).toString());
                CheckView(k, json.get(k));

//                JSONObject innerJson = array.get(j).toString();
                //아니야 여기서 뭐 다 셋 텍스트 해줄거야 ㅠㅠ??
                //뷰를 매개변수로 가지고 속성을 추가해주는 메서드 하나 더 만들어야해
//                JSONArray innerArray = innerJson.names();
//                String key = innerArray.get(j).toString();
//                String val = innerJson.get(key).toString();
//                Log.d("test1", key + ":" + val);
            }
        }
        return layout;
    }

    public void CheckView(String k, Object v) throws JSONException {
        // Log.d("test1", "HERE!");
        if (k.equals("id")) {
            Log.d("test1", "Inner id : " + v.toString());
        } else if (k.equals("location")) {
            Log.d("test1", "Inner location : " + v.toString());
        } else if (k.equals("items")) {

            JSONArray array = (JSONArray) v;
            Log.d("test1", "herheerer" + array.length());

            for ( int i = 0; i < array.length(); i++ ) {
                JSONObject object = (JSONObject) array.get(i);
                JSONArray obArray = object.names();
                for ( int j = 0; j < obArray.length(); j++ ) {
                    LinearLayout ll = new LinearLayout(this);
                    drawViews(object, ll);
                }
            }
        } else {
            Log.d("test1", k);
        }

    }

}
