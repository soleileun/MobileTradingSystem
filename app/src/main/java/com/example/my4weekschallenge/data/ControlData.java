package com.example.my4weekschallenge.data;

public class ControlData extends ViewData{

    private String subType; // 스피너나 에디트 텍스트 넣으려구 그냥 텍스트로 가능
    private String InputType;
    private String InputValue; // 계좌 선택
    private String Info; // 스피너같은거 계좌 정보

    public String getInputType() {
        return InputType;
    }

    public void setInputType(String inputType) {
        InputType = inputType;
    }

    public String getInputValue() {
        return InputValue;
    }

    public void setInputValue(String inputValue) {
        InputValue = inputValue;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
