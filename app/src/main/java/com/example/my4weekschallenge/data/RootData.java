package com.example.my4weekschallenge.data;

import java.util.ArrayList;

public class RootData
{
    public String name ;
    public ArrayList<ViewData> arr;

    public class ViewData {
        String name;
        ArrayList<ViewData> chArr;
    }



}
