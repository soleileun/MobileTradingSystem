package com.example.my4weekschallenge.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.my4weekschallenge.MainActivity;
import com.example.my4weekschallenge.R;
import com.example.my4weekschallenge.data.Itemlist;

import java.util.ArrayList;

public class ScrollViewAdapter extends BaseAdapter {
    ArrayList<Itemlist> items = new ArrayList<>();
    Context context;

    public void addItem(Itemlist item){
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int idx) {
        return items.get(idx);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        String TAG = MainActivity.class.getSimpleName();
        final Context context = viewGroup.getContext();
        final Itemlist item = items.get(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridcell, viewGroup, false);

            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView tradeNum = (TextView) convertView.findViewById(R.id.tradeNum);

            tradeNum.setText(item.getTradeNum());
            name.setText(item.getName());
            Log.d(TAG, "getView() - [ "+position+" ] "+item.getName());

        } else {
            View view = new View(context);
            view = (View) convertView;
        }

        //각 아이템 선택 event
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, item.getTradeNum()+" 번 - "+item.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;  //뷰 객체 반환
    }
}
