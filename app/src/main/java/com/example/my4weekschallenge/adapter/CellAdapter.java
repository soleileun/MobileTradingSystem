package com.example.my4weekschallenge.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my4weekschallenge.R;
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
//        LinearLayout layout = (LinearLayout) view;
         final StockCell stockCell ;
        if(view ==  null) {
            stockCell = new StockCell(viewGroup.getContext(), arrayList.get(i));
        }else {
            stockCell = (StockCell) view;
            if (stockCell != null) {
                // Log.d("euneun" , arrayList.get(i).toString());
                stockCell.setControl(arrayList.get(i));
            }
        }
        return stockCell;

    }
}
