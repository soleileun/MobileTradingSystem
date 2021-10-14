package com.example.my4weekschallenge.data;

import org.json.JSONArray;

import java.util.ArrayList;

public class LinearLayoutData extends ViewData{
    // 상속생각하면 protected
    // 보안 api를 만들때필요없는 정보가 너무 많을 필요는 없다.
    //json 하드코딩 ㄴㄴ 자유롭게 변형 가능하게
    private String margin ;
    private String orientation;
    private ArrayList<ViewData> childArr;

    public ArrayList<ViewData> getChildArr() {
        return childArr;
    }

    public void setChildArr(ArrayList<ViewData> childArr) {
        this.childArr = childArr;
    }
}
