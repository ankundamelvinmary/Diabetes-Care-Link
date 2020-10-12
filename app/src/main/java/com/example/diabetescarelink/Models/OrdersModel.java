package com.example.diabetescarelink.Models;

public class OrdersModel {
    String id,drugname,quantity,cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public OrdersModel(String id, String drugname, String quantity, String cost) {
        this.id = id;
        this.drugname = drugname;
        this.quantity = quantity;
        this.cost = cost;
    }
}
