package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private String user_name;
    private Integer id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User (String user_name) {
        this.user_name = user_name;
    }
}
