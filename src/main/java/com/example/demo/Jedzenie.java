package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Jedzenie {

    private SimpleStringProperty posiłek;
    private SimpleStringProperty rodzaj;
    private SimpleIntegerProperty kalorie;
    private Integer Id;
    private SimpleStringProperty data;

    public Jedzenie(String posilek, String rodzaj, Integer kalorie, String data, Integer Id){
        this.posiłek = new SimpleStringProperty(posilek);
        this.rodzaj = new SimpleStringProperty(rodzaj);
        this.kalorie = new SimpleIntegerProperty(kalorie);
        this.data = new SimpleStringProperty(data);
        this.Id = Id;
    }

    public String getPosilek() {
        return posiłek.get();
    }

    public SimpleStringProperty posilekProperty() {
        return posiłek;
    }

    public void setPosilek(String posilek) {
        this.posiłek.set(posilek);
    }

    public String getRodzaj() {
        return rodzaj.get();
    }

    public SimpleStringProperty rodzajProperty() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj.set(rodzaj);
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
}
