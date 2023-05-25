package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bieganie {

    private SimpleIntegerProperty dystans;
    private SimpleIntegerProperty kalorie;

    public int getMet() {
        return met.get();
    }

    public SimpleIntegerProperty metProperty() {
        return met;
    }

    public void setMet(int met) {
        this.met.set(met);
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

    public void setDystans(int dystans) {
        this.dystans.set(dystans);
    }

    private SimpleIntegerProperty met;
    private SimpleIntegerProperty czas;
    private SimpleStringProperty data;
    private Integer Id;

    public int getCzas() {
        return czas.get();
    }

    public SimpleIntegerProperty czasProperty() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas.set(czas);
    }

    public Bieganie(Integer dystans, Integer kalorie, Integer czas, String data) {

        this.dystans = new SimpleIntegerProperty(dystans);
        this.kalorie = new SimpleIntegerProperty(kalorie);
        this.data = new SimpleStringProperty(data);
        this.czas = new SimpleIntegerProperty(czas);
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

    public Integer getKalorie() {
        return kalorie.get();
    }

    public SimpleIntegerProperty kalorieProperty() {
        return kalorie;
    }

    public void setKalorie(Integer kalorie) {
        this.kalorie.set(kalorie);
    }

    public Integer getDystans() {
        return dystans.get();
    }

    public SimpleIntegerProperty dystansProperty() {
        return dystans;
    }

    public void setDystans(Integer dystans) {
        this.dystans.set(dystans);
    }
}
