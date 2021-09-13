package com.example.foodapp.models;

public class Orders {
    String phone_number , full_name , building , colony , landmark ;
    String food_name ;
    int total_price ;


    public Orders(String phone_number, String full_name, String building, String colony, String landmark, String food_name, int total_price) {
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.building = building;
        this.colony = colony;
        this.landmark = landmark;
        this.food_name = food_name;
        this.total_price = total_price;

    }

    public Orders(){

    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }


}
