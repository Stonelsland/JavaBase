package com.lyx.java.Team.Domain;

import com.lyx.java.Team.Service.Status;
public class Programmer extends Employee {
    private int memberId;//开发团队中id
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetails() +
                "\t工程师\t" + status.toString() +"\t"+
                "\t\t\t"+"\t" + equipment.getDescription() ;
    }

    public String getTeamDetails(){
        return memberId+"/"+getId()+"\t"+getName()+"\t"+getAge()+"\t"+getSalary();
    }
    public String getDetailsForTeam(){
        return getTeamDetails()+"\t工程师";
    }
}
