package com.example.my4weekschallenge.data;

public class Itemlist {
    String name;
    String tradeNum;
    String price;
    String diff;
    String percent;
    String arrow;

    public Itemlist(String name, String tradeNum, String price, String diff, String percent, String arrow) {
        this.name = name;
        this.tradeNum = tradeNum;
        this.price = price;
        this.diff = diff;
        this.percent = percent;
        this.arrow = arrow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getArrow() {
        return arrow;
    }

    public void setArrow(String arrow) {
        this.arrow = arrow;
    }

    @Override
    public String toString() {
        return "Itemlist{" +
                "name='" + name + '\'' +
                ", tradeNum='" + tradeNum + '\'' +
                ", price='" + price + '\'' +
                ", diff='" + diff + '\'' +
                ", percent='" + percent + '\'' +
                ", arrow='" + arrow + '\'' +
                '}';
    }
}

