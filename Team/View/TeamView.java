package com.lyx.java.Team.View;

import com.lyx.java.Team.Domain.Employee;
import com.lyx.java.Team.Domain.Programmer;
import com.lyx.java.Team.Service.TeamException;
import com.lyx.java.Team.View.TMUtility;
import com.lyx.java.Team.Service.NameListService;
import com.lyx.java.Team.Service.TeamService;

public class TeamView {
    private NameListService listService = new NameListService();
    private TeamService teamService = new TeamService();
    boolean loopFlag = true;
    char menu = 0;

    public void enterMainMenu() {
        loopFlag = true;
        while (loopFlag) {
            if (menu != 1) {
                listAllEmployees();
            }
            System.out.println("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
            menu = TMUtility.menuSelect();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    exit();
                    break;
            }
        }
    }

    private void listAllEmployees() {
        System.out.println("-----------------------------开发团队调度系统-----------------------------\n");
        Employee[] employees = listService.getEmployees();
        if (employees == null || employees.length == 0) {
            System.out.println("目前没有在职员工");
        } else {
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t设备");
            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }
        }
        System.out.println("------------------------------------------------------------------------\n");

    }

    //查看开发团队情况
    private void getTeam() {
        System.out.println("-----------------------------开发团队成员列表-----------------------------\n");
        Programmer[] team = teamService.getTeam();
        if (team == null || team.length == 0) {
            System.out.println("开发团队目前没有成员");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\t");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("------------------------------------------------------------------------\n");
    }

    private void addMember() {
        System.out.println("--------------------------------添加成员---------------------------------\n");
        System.out.println("请输入要添加员工的ID:");
        int id = TMUtility.readInt();
        try {
            Employee employee = listService.getEmployee(id);
            teamService.addMember(employee);
            System.out.println("添加成功");
            TMUtility.EnterReturn();
        } catch (TeamException e) {
            System.out.println("添加失败:"+e.getMessage());
        }

    }

    private void deleteMember() {
        System.out.println("--------------------------------删除成员---------------------------------\n");
        System.out.println("请输入要删除员工的TID:");
        int memberId = TMUtility.readInt();
        System.out.println("确认是否删除(Y/N):");
        char isDelete = TMUtility.comfirmSelection();
        if (isDelete=='N'){
            return;
        }

        try {
            teamService.deleteMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败:"+e.getMessage());        }
        TMUtility.EnterReturn();

    }

    //退出
    private void exit() {
        System.out.println("确认是否退出(Y/N):");
        char exit = TMUtility.comfirmSelection();
        if (exit == 'Y') {
            loopFlag = false;
        }
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
