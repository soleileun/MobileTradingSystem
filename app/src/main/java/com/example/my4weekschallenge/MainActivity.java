package com.example.my4weekschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my4weekschallenge.data.RootData;
import com.example.my4weekschallenge.data.ViewData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout baselayout = new LinearLayout(this);
        LinearLayout layout = null;
        Button btn = new Button(this);
        btn.setText("Here");
        TextView tv = new TextView(this);

        try {
            Log.d("test1","START!");
            setContentView(readJsonFile(baselayout));
        } catch (IOException e) {
            Log.e("test1",e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("test1",e.toString());
        }

    }

    private View readJsonFile(LinearLayout view) throws IOException, JSONException {

        AssetManager assetManager = getAssets();
        InputStream is = assetManager.open("test.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        String line = br.readLine();
        while(line !=null){
            sb.append(line + "\n");
            line = br.readLine();
        }
        String jsonData = sb.toString();
        return testFun(jsonData,view);
       // JSONObject jsonObject = new JSONObject(jsonData);
        //JSON과 베이스 레이아웃을 가지고 함수로 들어간다.

    }

    public View testFun(String jsondata, LinearLayout layout) throws JSONException {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(jsondata);
        Log.d("asdf",jsonObject.toString());
        RootData rootData = gson.fromJson(jsondata,RootData.class);

        for(int i=0;i<rootData.getArr().size();i++){
            ViewData vData = rootData.getArr().get(i);
            Log.d("asdf",vData.getType());
        }

        return layout;
    }

    public View drawViews(JSONObject jsonObject, LinearLayout layout) throws JSONException {

        //json object  에 names 확인
        //names 에 있는 친구들 값이 json인지 확인
        // json이라면 재귀? ㅠ
        // 아니라면 상위 레이아웃에 추가?

        String s = jsonObject.names().toString();
        JSONArray jsonArray = jsonObject.names();
        Log.d("test1",s );
        Log.d("test1", String.valueOf(jsonArray.length()));
        for(int i=0;i<jsonArray.length();i++){
            JSONObject json = jsonObject.getJSONObject(jsonArray.get(i).toString());
            JSONArray array = json.names();
            String ss = array.toString();
            Log.d("test1",ss + ss.length());
            //items 항목의 값이 json이거나 배열이거나 하면 다시 재귀 걸고
            //아니라면 레이아웃에 넣으세욧!

            for(int j = 0; j<array.length(); j++) {
                String k = array.get(j).toString();
                //  Log.d("test1", array.get(j).toString()+"  : "+ json.get(k).toString());
                CheckView(k,json.get(k));

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
        if(k.equals("id")){
            Log.d("test1", "Inner id : " + v.toString());
        }else if(k.equals("location")){
            Log.d("test1", "Inner location : " + v.toString());
        }else if(k.equals("items")){
//           JSONObject array = (JSONObject) v;
//           JSONArray s = array.names();
//           LinearLayout layout = new LinearLayout(this);
//           layout = (LinearLayout) drawViews(array,layout);

            JSONArray array = (JSONArray) v;
            Log.d("test1","herheerer" + array.length()); // 5


//            Log.d("test1", i + " : " + obj.names());
            for(int i=0;i< array.length();i++){
                JSONObject object = (JSONObject) array.get(i);
                JSONArray obArray = object.names();
                for(int j=0;j < obArray.length();j++){
                    LinearLayout ll = new LinearLayout(this);
                    drawViews(object,ll);
                }

            }
//            Log.d("test1",array.toString());

        }else{
            Log.d("test1", k);
        }

    }


}