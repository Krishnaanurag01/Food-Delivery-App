package com.example.foodapp.models;

public class Foods {
    String  food_name  , food_category ;
    String food_price;
    int food_img ;
    int food_id;
    int Quantity=1 ;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public Foods(int food_img, String food_name, String food_category, String food_price) {
        this.food_img = food_img;
        this.food_name = food_name;
        this.food_category = food_category;
        this.food_price = food_price;
    }

    public Foods(String food_name, String food_category, String food_price){
        this.food_name = food_name;
        this.food_category = food_category;
        this.food_price = food_price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Foods(){

    }

    public int getFood_img() {
        return food_img;
    }

    public void setFood_img(int food_img) {
        this.food_img = food_img;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }
}
