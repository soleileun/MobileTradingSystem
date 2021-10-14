//package com.example.my4weekschallenge;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.ActionBar;
//import android.content.pm.PackageManager;
//import android.content.res.AssetManager;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.InputType;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.HorizontalScrollView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.SpinnerAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.Toolbar;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//
//import com.example.my4weekschallenge.adapter.CellAdapter;
//import com.example.my4weekschallenge.data.Itemlist;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.w3c.dom.Text;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.logging.Level;
//
//public class Week3Activity extends AppCompatActivity {
//
//    @SuppressLint("ResourceType")
//    @RequiresApi(api = Build.VERSION_CODES.N) //N -> Nougat버전의
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        LinearLayout ll = new LinearLayout(this);
//
//        Button btn = new Button(this);
//        btn.setText("Here");
//        TextView tv = new TextView(this);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    clickBtn(tv);
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        ll.setOrientation(LinearLayout.VERTICAL);
//        ll.addView(btn);
//        ll.addView(tv);
//
//        setContentView(ll);
//
//
//
//    }
//
//    private void clickBtn(TextView view) throws IOException, JSONException {
//
//        AssetManager assetManager = getAssets();
//        InputStream is = assetManager.open("test.json");
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//
//        StringBuffer sb = new StringBuffer();
//        String line = br.readLine();
//        while (line != null) {
//            sb.append(line + "\n");
//            line = br.readLine();
//        }
//        String jsonData = sb.toString();
//
//        JSONObject jsonObject = new JSONObject(jsonData);
//
////        String id = jsonObject.getString("id");
////        String value = jsonObject.getString("value");
//        String menu = jsonObject.getString("Button");
//        Log.d("test1", jsonObject.names().toString()); // 가장 최상단을 ["Button"] 이런 형태로 가져올수있게됨.
//        String tt = jsonObject.names().toString(); // array로 반환된다고
//        Log.d("test1", "sang," + jsonObject.names().length());
//        for (int i = 0; i < jsonObject.names().length(); i++) {
////            JSONArray str = jsonObject.getString(jsonObject.names().get(i));
//            String now = jsonObject.names().get(i).toString();
//            JSONObject array = jsonObject.getJSONObject(now);
//            Log.d("test1", String.valueOf(array.length()));
//            for (int j = 0; j < array.names().length(); j++) {
//                String s = array.names().get(j).toString();
////                Boolean flag = array.has(s);
////                String type = array.get(s).getClass().toString();//  classㄲㅏ지 하면 return type object 인데
//                Log.d("test1", "INNER : " + s );
////                Log.d("test1", type);
//                if(array.get(s) instanceof JSONObject){ //  값이 제이슨 객체인지 확인 가능 ~ 약간 재귀형식?을 확인해줘야하나
//                    Log.d("test1" , "this is jsonObject");
//                    JSONObject inner = (JSONObject) array.get(s);
//                    Log.d("test1",inner.names().toString());
////                    for(int k=0;k<inner.length();k++){
////                        String string  = inner.get(k).toString();
////                       Log.d("test1",string);
////
////
////
////                    }
//                }
//
//
////                if (array.getClass().getSimpleName())
//
//
//            }
//            Log.d("test1", i + array.toString());
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(MainActivity.this, item.getItemId() , Toast.LENGTH_SHORT).show();
//
//        switch (item.getItemId()){
//
//            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
//                Toast.makeText(MainActivity.this, "back" , Toast.LENGTH_SHORT).show();
//
//                finish();
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void menuSelected(String item){
//        switch (item){
//            case "BACK":{
//                Toast.makeText(MainActivity.this, "back" , Toast.LENGTH_SHORT).show();
//                // finish();
//                return;}
//            case "SEARCH":{
//                Toast.makeText(MainActivity.this, "search" , Toast.LENGTH_SHORT).show();
//
//                return;
//            }
//            case "SETTING":{
//                Toast.makeText(MainActivity.this, "setting" , Toast.LENGTH_SHORT).show();
//
//                return;
//            }
//        }
//    }
//    public float dpToPx(float dp){
//        float px =  TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, dp,
//                this.getResources().getDisplayMetrics());
//        return px;
//    }
//    private ArrayList<Itemlist> loadItemsFromFile() {
//
//        AssetManager am = getAssets();
////        AssetManager am = getResources().getAssets() ;
//        ArrayList<Itemlist> items = new ArrayList<>();
//        InputStream is = null;
//        byte buf[] = new byte[1024];
//        String text = "";
//
//        try {
//            is = am.open("stock.txt");
//
//            if (is.read(buf) > 0) {
//                text = new String(buf);
//            }
//            String[] str = text.split("\n");
//            for(int i=0;i<str.length;i++){
//                String[] s = str[i].split("\\|");
//                items.add(new Itemlist(s[0],s[1],s[2],s[3],s[4],s[5]));
////                Log.d("plz",s[0] + "OK");
//            }
//
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (is != null) {
//            try {
//                is.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return items;
//    }
//
//
//}