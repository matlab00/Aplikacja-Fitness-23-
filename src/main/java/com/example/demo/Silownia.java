package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Silownia {
    private SimpleDoubleProperty met;
    private SimpleIntegerProperty kalorie;
    private SimpleDoubleProperty czas;
    private SimpleStringProperty data;
    private SimpleStringProperty cwiczenie;
    private Integer Id;

    public Silownia(String cwiczenie, Integer kalorie, Double czas, String data, Double met, Integer Id ) {

        this.cwiczenie = new SimpleStringProperty(cwiczenie);
        this.kalorie = new SimpleIntegerProperty(kalorie);
        this.data = new SimpleStringProperty(data);
        this.czas = new SimpleDoubleProperty(czas);
        this.met = new SimpleDoubleProperty(met);
        this.Id = Id;
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

    public int getKalorie() {
        return kalorie.get();
    }

    public SimpleIntegerProperty kalorieProperty() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie.set(kalorie);
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

    public String getCwiczenie() {
        return cwiczenie.get();
    }

    public SimpleStringProperty cwiczenieProperty() {
        return cwiczenie;
    }

    public void setCwiczenie(String cwiczenie) {
        this.cwiczenie.set(cwiczenie);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
