package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bieganie {

    private SimpleDoubleProperty dystans;
    private SimpleIntegerProperty kalorie;
    private SimpleDoubleProperty met;
    private SimpleDoubleProperty czas;
    private SimpleStringProperty data;
    private Integer Id;

    public Bieganie(Double dystans, Integer kalorie, Double czas, String data, Double met, Integer Id ) {

        this.dystans = new SimpleDoubleProperty(dystans);
        this.kalorie = new SimpleIntegerProperty(kalorie);
        this.data = new SimpleStringProperty(data);
        this.czas = new SimpleDoubleProperty(czas);
        this.met = new SimpleDoubleProperty(czas);
        this.Id = Id;
    }

    //region Getters and Setters

    public double getDystans() {
        return dystans.get();
    }

    public SimpleDoubleProperty dystansProperty() {
        return dystans;
    }

    public void setDystans(double dystans) {
        this.dystans.set(dystans);
    }

    public int getKalorie() {
        return kalorie.get();
    }

    public SimpleIntegerProperty kalorieProperty() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie.set(kalorie);
    }

    public double getMet() {
        return met.get();
    }

    public SimpleDoubleProperty metProperty() {
        return met;
    }

    public void setMet(double met) {
        this.met.set(met);
    }

    public double getCzas() {
        return czas.get();
    }

    public SimpleDoubleProperty czasProperty() {
        return czas;
    }

    public void setCzas(double czas) {
        this.czas.set(czas);
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    //endregion
}
