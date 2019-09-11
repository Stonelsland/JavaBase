package com.lyx.java.Team.Domain;

public class LapTop implements Equipment {

    private String model;//型号
    private double price;//价格
    @Override
    public String getDescription() {
        return model+"("+price+")";
    }

    public LapTop() {
    }

    public LapTop(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
