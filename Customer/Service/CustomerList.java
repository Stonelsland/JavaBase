package com.lyx.java.Customer.Service;

import com.lyx.java.Customer.Bean.Customer;

public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    /*
    初始化数组
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];

    }

    /**
     * 将指定用户添加到数组中
     * @param customer
     * @return true添加成功 false 添加失败
     */
    public boolean addCustomer(Customer customer) {
        if (total>=customers.length){
            return false;
        }
        customers[total++] = customer;
        return true;
    }

    /**
     * 修改指定位置的用户信息
     * @param index
     * @param customer
     * @return
     */
    public boolean replaceCustomer(int index,Customer customer){
        if (index<0||index>=total){
            return false;
        }
        customers[index] = customer;
        return true;

    }

    /**
     * 删除指定位置上的用户信息
     * @param index
     * @return
     */
    public boolean deleteCustomer(int index){
        if (index<0||index>=total){
            return false;
        }
        for (int i = index; i < total - 1; i++) {
            customers[i]= customers[i+1];
        }
        //最后一个有数据的元素置空
        customers[--total] = null;
        return true;
    }

    /**
     * 获取所有用户信息
     * @return
     */
    public Customer[] getCustomers(){
        Customer[] custs = new Customer[total];
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }

    /**
     * 获取指定用户信息
     * @param index
     * @return
     */
    public Customer getCustomer(int index){
        if (index<0||index>=total){
            return null;
        }
        return customers[index];
    }

    /**
     * 获取存储用户数量
     * @return
     */
    public int getTotal(){
        return total;
    }
}
