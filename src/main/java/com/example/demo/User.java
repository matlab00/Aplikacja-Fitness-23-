package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private String user_name;
    private Integer UserID;

    public User(String user_name, Integer UserID) {
        this.user_name = user_name;
        this.UserID = UserID;
    }

    public String getUser_name() {
        return user_name;
    }


    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer id) {
        this.UserID = id;
    }

    public User (String user_name) {
        this.user_name = user_name;
    }
}
