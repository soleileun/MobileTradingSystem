package com.example.my4weekschallenge.manager;

public class LayoutManager {

    public static LayoutManager layoutManager = null;
    private LayoutManager() {

    }

    public static LayoutManager getInstance(){
        if(layoutManager == null){
            layoutManager = new LayoutManager();
        }
        return  layoutManager;
    }

}
