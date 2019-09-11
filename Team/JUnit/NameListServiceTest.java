package com.lyx.java.Team.JUnit;

import com.lyx.java.Team.Domain.Employee;
import com.lyx.java.Team.Service.NameListService;
import com.lyx.java.Team.Service.TeamException;
import org.junit.Test;


/**
 * 对NameListService类进行测试
 */
public class NameListServiceTest {

    @Test
    public void testGetEmployees(){
        NameListService nameListService = new NameListService();
        Employee[] employees = nameListService.getEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService nameListService = new NameListService();
        int id = 1;
        try {
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }

    }
}
