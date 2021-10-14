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
    private ArrayList<ViewData> arr;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ViewData> getArr() {
        return arr;
    }

    public void setArr(ArrayList<ViewData> arr) {
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
