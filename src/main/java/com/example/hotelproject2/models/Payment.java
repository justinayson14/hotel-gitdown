package com.example.hotelproject2.models;

public class Payment {
    private String name;
    private String cardNum;
    private String cardCVC;
    private String phoneNum;
    private String cardExp;

    public Payment () {}
    public Payment (String name, String cardNum, String cardCVC, String cardExp, String phoneNum) {
        this.name = name;
        this.cardNum = cardNum;
        this.cardCVC = cardCVC;
        this.cardExp = cardExp;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCardNum() {
        return cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardCVC() {
        return cardCVC;
    }
    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }

    public String getCardExp() {
        return cardExp;
    }
    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
