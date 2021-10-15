package com.example.my4weekschallenge.data;

import android.util.Log;

import java.util.ArrayList;

public class ViewData {

    protected String type;
    protected String width;
    protected String height;
    protected String weight;
    protected String gravity;
    protected String padding;
    protected String text;
    protected String res;
    protected String color;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }


    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ViewData{" +
                "type='" + type + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", gravity='" + gravity + '\'' +
                ", padding='" + padding + '\'' +
                ", text='" + text + '\'' +

                '}';
    }
}