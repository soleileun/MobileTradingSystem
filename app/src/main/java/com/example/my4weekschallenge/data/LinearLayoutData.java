package com.example.my4weekschallenge.data;

import org.json.JSONArray;

import java.util.ArrayList;

public class LinearLayoutData extends ViewData{

    private String margin ;
    private String orientation;
    private ArrayList<LinearLayoutData> arr;

    public ArrayList<LinearLayoutData> getArr() {
        return arr;
    }

    public void setArr(ArrayList<LinearLayoutData> childArr) {
        this.arr = childArr;
    }

}
