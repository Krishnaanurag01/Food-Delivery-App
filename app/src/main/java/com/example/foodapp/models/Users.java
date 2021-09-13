package com.example.foodapp.models;

public class Users {
    String image , name , phoneNumber , mail , password, UID;

    public Users(String image, String name, String phoneNumber, String mail, String password) {
        this.image = image;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
    }

    public Users(String name, String phoneNumber, String mail, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
    }



    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Users(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

