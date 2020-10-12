package com.example.diabetescarelink.Models;

public class DrugsModel {
   String id,drug,cost,place;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public DrugsModel(String id, String drug, String cost, String place) {
        this.id = id;
        this.drug = drug;
        this.cost = cost;
        this.place = place;
    }
}
