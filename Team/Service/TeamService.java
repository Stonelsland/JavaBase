package com.lyx.java.Team.Service;

import com.lyx.java.Team.Domain.Architect;
import com.lyx.java.Team.Domain.Designer;
import com.lyx.java.Team.Domain.Employee;
import com.lyx.java.Team.Domain.Programmer;

/**
 * 开发团队的成员管理,添加,删除等操作
 */
public class TeamService {
    private static int counter = 1;//给memberId赋值
    private final int MAX_MEMBER = 5;//限制开发团队人数

    private Programmer[] team = new Programmer[MAX_MEMBER];//保存开发团队成员
    private int total;//开发团队中实际人数

    public TeamService() {

    }

    //获取开发团队所有成员信息
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    //将指定员工添加到开发团队中
    public void addMember(Employee employee) throws TeamException {

        //成员已满,无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满,无法添加");
        }
        //成员不是开发人员,无法添加
        if (!(employee instanceof Programmer)) {
            throw new TeamException("改成员不是开发人员,无法添加");
        }
        //成员已在开发团队中,不能重复添加
        if (isExit(employee)) {
            throw new TeamException("成员已在开发团队中,不能重复添加");
        }
        //该员工已是其他团队成员,无法添加
        Programmer programmer = (Programmer) employee;//一定不会出现ClassCastException
        if ("BUSY".equalsIgnoreCase(programmer.getStatus().getNAME())) {
            throw new TeamException("该员工已是其他团队成员,无法添加");
            //该员工正在休假,无法添加
        } else if ("VOCATION".equalsIgnoreCase(programmer.getStatus().getNAME())) {
            throw new TeamException("该员工正在休假,无法添加");
        }
        //开发团队中最多有一位架构师,两位设计师,三位工程师
        //获取团队中已有架构师,设计师,工程师人数
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < team.length; i++) {
            if (team[i] instanceof Architect) {
                numOfArch++;
            } else if (team[i] instanceof Designer) {
                numOfDes++;
            } else if (team[i] instanceof Programmer) {
                numOfPro++;
            }
        }

        if (programmer instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中最多只能有一位架构师");
            }
        } else if (programmer instanceof Designer) {
            if (numOfDes >= 2) {
                throw new TeamException("团队中最多只能有两位设计师");
            }
        } else if (programmer instanceof Programmer) {
            if (numOfPro >= 3) {
                throw new TeamException("团队中最多只能有三位工程师");
            }
        }

        //将programmer(或e)添加到现有的team中
        team[total++] = programmer;
        //p的属性赋值
        programmer.setStatus(Status.BUSY);
        programmer.setMemberId(counter++);


    }

    //判断指定员工是否已经存在于当前开发团队中
    private boolean isExit(Employee employee) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == employee.getId()) {
                return true;
            }
        }
        return false;
    }

    //从开发团队中删除成员
    public void deleteMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < total; i++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //未找到指定memberId
        if (i==total){
            throw new TeamException("未找到指定memberId的成员");
        }
        //后一个元素覆盖前一个元素
        for (int j = i+1; j < total; j++) {
            team[j-1] = team[j];
        }
        team[--total] = null;

    }
}
