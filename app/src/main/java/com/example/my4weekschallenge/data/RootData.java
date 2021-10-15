package com.example.my4weekschallenge.data;

import java.util.ArrayList;

public class RootData extends ViewData
{
//    protected String type;
//    protected String width;
//    protected String height;
//    protected String weight;
//    protected String gravity;
//    protected String padding;
//    protected String text;
    private String margin ;
    private String orientation;
    private ArrayList<LinearLayoutData> arr;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<LinearLayoutData> getArr() {
        return arr;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setArr(ArrayList<LinearLayoutData> arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                sb.append("index").append(i).append(" : ").append(arr.get(i).getType()).append("\n");
            }
        }
        return "RootData{" +
                "name='" + type + '\'' +sb.toString()+'}';
    }
}
