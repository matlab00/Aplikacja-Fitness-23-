package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Year;

public class Data {

    private SimpleStringProperty sex;
    private SimpleIntegerProperty Masa;
    private SimpleIntegerProperty wzrost;

    private Integer Id;
    private SimpleIntegerProperty age;
    private SimpleStringProperty data;
    private SimpleIntegerProperty BMI;
    static SimpleDoubleProperty BMR;

    public Data(Integer Masa, Integer wzrost, String data, Double BMR, Integer Id) {
        this.Masa = new SimpleIntegerProperty(Masa);
        this.wzrost = new SimpleIntegerProperty(wzrost);
        this.data = new SimpleStringProperty(data);
        this.BMR = new SimpleDoubleProperty(BMR);
        this.Id = Id;
    }


    // region Getters and Setters
    public Integer getId() {
        return Id;
    }
    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public int getMasa() {
        return Masa.get();
    }

    public SimpleIntegerProperty masaProperty() {
        return Masa;
    }

    public void setMasa(int masa) {
        this.Masa.set(masa);
    }

    public int getWzrost() {
        return wzrost.get();
    }

    public SimpleIntegerProperty wzrostProperty() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost.set(wzrost);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public int getBMI() {
        return BMI.get();
    }

    public SimpleIntegerProperty BMIProperty() {
        return BMI;
    }

    public void setBMI(int BMI) {
        this.BMI.set(BMI);
    }

    public double getBMR() {
        return BMR.get();
    }

    public SimpleDoubleProperty BMRProperty() {
        return BMR;
    }

    public void setBMR(int BMR) {
        this.BMR.set(BMR);
    }
    // endregion
}
