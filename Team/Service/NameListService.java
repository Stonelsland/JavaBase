package com.lyx.java.Team.Service;

import com.lyx.java.Team.Domain.*;

import java.security.Key;

import static com.lyx.java.Team.Service.Data.*;

/**
 * 负责将Data中的数据封装到Employee[]中,同时提供相关操作方法
 */
public class NameListService {

    private Employee[] employees;

    /**
     * employees及数组元素初始化
     */
    public NameListService() {
        //根据项目提供的Data类构建相应大小的employees数组
        //再根据Data类中的数据构建不同的对象并存放在数组中
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < employees.length; i++) {
            int type = Integer.parseInt(EMPLOYEES[i][0]);
            //获取Employee的基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;


            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHTITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus,stock);
                    break;
            }
        }
    }

    //获取指定index上的员工设备
    private Equipment createEquipment(int index) {
        int key = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];

        switch (key) {
            case PC://21
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName, display);
            case NOTEBOOK://22
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new LapTop(modelOrName, price);
            case PRINTER://23
                String type = EQUIPMENTS[index][2];
                return new Printer(modelOrName, type);
        }
        return null;
    }

    /*
    获取当前所有员工
     */
    public Employee[] getEmployees() {
        return employees;
    }
    /*
    获取指定id员工
     */
    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId()==id){
                return employees[i];
            }
        }
        throw new TeamException("未找到指定员工");
    }
}
