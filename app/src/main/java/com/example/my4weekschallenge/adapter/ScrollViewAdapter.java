package com.example.my4weekschallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


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
    public View getView(int idx, View view, ViewGroup parent) {
        context = parent.getContext();
        Itemlist itemlist = items.get(idx);

        if(view ==  null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell,parent,false);
        }

        TextView nameText = view.findViewById(R.id.name);
        TextView tradeNum = view.findViewById(R.id.tradeNum);

        nameText.setText(itemlist.getName());
        tradeNum.setText(itemlist.getTradeNum());

        return view;
    }
}
