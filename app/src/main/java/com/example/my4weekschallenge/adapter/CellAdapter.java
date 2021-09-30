package com.example.my4weekschallenge.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.my4weekschallenge.StockCell;
import com.example.my4weekschallenge.data.Itemlist;

import java.util.ArrayList;

public class CellAdapter extends BaseAdapter {

    ArrayList<Itemlist> arrayList = new ArrayList<>();

    public CellAdapter(ArrayList<Itemlist> array) {
        super();
        this.arrayList = array;
    }

    public void addItem(Itemlist item){ arrayList.add(item); }

    @Override
    public int getCount() {
        Log.d("euneun", String.valueOf(arrayList.size()));
        return arrayList.size();
    }

    @Override
    public Itemlist getItem(int i) {
        Log.d("euneun", arrayList.get(i).toString());
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = (LinearLayout) view;
        if(layout== null)
        {
            layout = new LinearLayout(viewGroup.getContext());
            layout.setOrientation( LinearLayout.VERTICAL );
            Log.d("deun",arrayList.get(i).getName());
            layout.addView(new StockCell(layout.getContext(), arrayList.get(i)));
        }
        return layout;
    }
}
