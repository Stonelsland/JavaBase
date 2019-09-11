package com.lyx.java.Team.Domain;

import com.lyx.java.Team.Service.Status;

public class Architect extends Designer {

    private int stock;//股票

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getDetails() +
                "\t架构师\t" +
                getStatus().toString() + "\t" +
                getBonus() + "\t" +
                stock + "\t" +
                getEquipment().getDescription();
    }
    public String getDetailsForTeam(){
        return getTeamDetails()+"\t架构师\t"+getBonus()+"\t"+getStock();
    }
}
