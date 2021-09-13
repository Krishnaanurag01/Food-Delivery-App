package com.example.foodapp.models;

public class Address {

    String name , colony , landmark , building , number;

    public Address() {
    }

    public Address(String name, String colony, String landmark, String building, String number) {
        this.name = name;
        this.colony = colony;
        this.landmark = landmark;
        this.building = building;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
