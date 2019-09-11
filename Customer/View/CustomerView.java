package com.lyx.java.Customer.View;

import com.lyx.java.Customer.Bean.Customer;
import com.lyx.java.Customer.Service.CustomerList;
import com.lyx.java.Customer.Util.CMUtility;

public class CustomerView {
    public boolean isFlag;
    private CustomerList customerList = new CustomerList(10);


    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.enterMainMenu();
    }

    /**
     * 主菜单
     */
    public void enterMainMenu() {
        isFlag = true;
        while (isFlag) {
            System.out.println("\n========用户信息管理系统========");
            System.out.println("           1:添加用户");
            System.out.println("           2:修改用户");
            System.out.println("           3:删除用户");
            System.out.println("           4:用户列表");
            System.out.println("           5:退  出\n");
            System.out.print("选择操作(1-5)");

            char menu = CMUtility.menuSelect();
            switch (menu) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    exit();
            }
        }
    }

    /**
     * 添加用户信息
     */
    private void addNewCustomer() {

        System.out.println("========== 添加用户 ==========");
        System.out.print("姓名:");
        String name = CMUtility.readString(10);
        System.out.print("性别:");
        char sex = CMUtility.readChar();
        System.out.print("年龄:");
        int age = CMUtility.readInt();
        System.out.print("电话:");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱:");
        String email = CMUtility.readString(30);
        //将上述数据封装到一个对象中
        Customer customer = new Customer(name, sex, age, phone, email);
        boolean isSuccess = customerList.addCustomer(customer);
        if (isSuccess) {
            System.out.println("========== 添加完成 ==========");
        } else {
            System.out.println("========== 添加失败 ==========");
        }


    }

    /**
     * 修改用户信息
     */
    private void modifyCustomer() {
        System.out.println("========== 修改用户 ==========");
        Customer customer;
        int number;
        for (;;){
            System.out.print("请选择待修改信息编号(-1退出):");
            number = CMUtility.readInt();
            if (number==-1){
                return;
            }
            customer = customerList.getCustomer(number-1);
            if (customer==null){
                System.out.println("未找到匹配用户!");
            }else {//找到对应用户
                break;
            }
        }
        //修改用户信息
        System.out.print("姓名("+customer.getName()+"):");
        String name = CMUtility.readString(10, customer.getName());
        System.out.print("性别("+customer.getSex()+"):");
        char sex = CMUtility.readChar(customer.getSex());
        System.out.print("年龄("+customer.getAge()+"):");
        int age = CMUtility.readInt(customer.getAge());
        System.out.print("手机("+customer.getPhone()+"):");
        String phone = CMUtility.readString(13, customer.getPhone());
        System.out.print("邮箱("+customer.getEmail()+"):");
        String email = CMUtility.readString(30, customer.getEmail());

        Customer newCustomer = new Customer(name,sex,age,phone,email);
        boolean isRepalced=customerList.replaceCustomer(number-1,newCustomer);
        if (isRepalced){
            System.out.println("========== 修改完成 ==========");
        } else {
            System.out.println("========== 修改失败 ==========");
        }

    }

    /**
     * 删除用户信息
     */
    private void deleteCustomer() {
        System.out.println("========== 删除用户 ==========");
        int number;
        for(;;){
            System.out.print("请选择待删除信息编号(-1退出):");
            number = CMUtility.readInt();
            if (number==-1){
                return;
            }
            Customer customer = customerList.getCustomer(number - 1);
            if (customer==null){
                System.out.println("未找到待删除用户!");
            }else {
                break;
            }
        }
        System.out.println("确认是否删除(Y/N):");
        char isDelete = CMUtility.comfirmSelection();
        if (isDelete=='Y'){
            boolean deleteSuccess = customerList.deleteCustomer(number-1);
            if (deleteSuccess) {
                System.out.println("========== 删除成功 ==========");
            }else {
                System.out.println("========== 删除失败 ==========");
            }
        }else {
            return;
        }

    }

    /**
     * 显示用户信息列表
     */
    private void listAllCustomers() {
        System.out.println("\n========== 用户列表 ==========");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("没有记录!");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
            Customer[] customers = customerList.getCustomers();
            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                System.out.println((i + 1) + "\t    " + customer.getName() + "\t    " + customer.getSex() + "\t    " +
                        customer.getAge() + "\t    " + customer.getPhone() + "\t            " + customer.getEmail());
            }
        }
        System.out.println("\n=============================");

    }

    /**
     * 退出
     */
    private void exit() {
        System.out.println("确认是否退出(Y/N):");
        char exit = CMUtility.comfirmSelection();
        if (exit == 'Y') {
            isFlag = false;
        }
    }
}
