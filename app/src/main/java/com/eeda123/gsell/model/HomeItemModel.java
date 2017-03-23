package com.eeda123.gsell.model;

/**
 * Created by a13570610691 on 2017/3/22.
 */

public class HomeItemModel {
    private String strPlatform;
    private String strShopName;
    private int intOrderCount;
    private int intNoPay;
    private int intNoShip;

    public HomeItemModel(String strPlatform, String strShopName, int intOrderCount, int intNoPay, int intNoShip){
        this.strPlatform = strPlatform;
        this.strShopName = strShopName;
        this.intOrderCount = intOrderCount;
        this.intNoPay = intNoPay;
        this.intNoShip = intNoShip;
    }

    public String getStrShopName() {
        return strShopName;
    }

    public void setStrShopName(String strShopName) {
        this.strShopName = strShopName;
    }

    public int getIntOrderCount() {
        return intOrderCount;
    }

    public void setIntOrderCount(int intOrderCount) {
        this.intOrderCount = intOrderCount;
    }

    public int getIntNoPay() {
        return intNoPay;
    }

    public void setIntNoPay(int intNoPay) {
        this.intNoPay = intNoPay;
    }

    public int getIntNoShip() {
        return intNoShip;
    }

    public void setIntNoShip(int intNoShip) {
        this.intNoShip = intNoShip;
    }



    public String getStrPlatform() {
        return strPlatform;
    }

    public void setStrPlatform(String strPlatform) {
        this.strPlatform = strPlatform;
    }



}
