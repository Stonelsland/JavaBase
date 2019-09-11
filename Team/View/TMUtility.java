package com.lyx.java.Team.View;

import java.util.Scanner;

public class TMUtility {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 界面菜单选择
     */
    public static char menuSelect() {
        char select;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            select = str.charAt(0);
            if (select != '1' && select != '2' &&
                    select != '3' && select != '4' ) {
                System.out.println("选择错误,请重新输入");
            } else break;
        }
        return select;
    }
    /**
     * 提示并等待,直到按回车键返回
     */
    public static void EnterReturn(){
        System.out.println("Press Enter To Continue");
        readKeyBoard(100,true);
    }
    /**
     * 从键盘读取字符,并将其作为方法的返回值
     */
    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }

    /**
     * 从键盘读取一个长度不超过2的整数作为方法的返回值
     */
    public static int readInt() {
        int i;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                i = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.println("数字输入错误,请重新输入");
            }
        }
        return i;
    }

    /**
     * 从键盘读取一个长度不超过limit的字符串作为方法的返回值
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }


    /**
     * 确认用户选择操作,从键盘读取Y或N作为方法的返回值
     */
    public static char comfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.println("选择无效");
            }
        }
        return c;
    }
    /**
     * @param limit
     * @param blankReturn
     * @return
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }
            if (line.length() < 1 || line.length() > limit) {
                System.out.println("输入长度错误,请重新输入(大于" + limit + ")");
                continue;
            }
            break;
        }
        return line;
    }
}
