package com.lyx.java.Team.Service;

/**
 * 自定义异常类
 */
public class TeamException extends Exception {

    static final long serialVersionUID = -12635436643636L;

    public TeamException() {
    }

    public TeamException(String message) {
        super(message);
    }
}
