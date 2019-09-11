package com.lyx.java.Singleton;

public class SingletonTest {
    public static void main(String[] args) {
        Single single = Single.getIntance();
        Single single1 = Single.getIntance();
        System.out.println(single==single1);
    }
}

//饿汉式实现单例模式
class Singleton{
    //1.私有化类的构造器
    private Singleton(){

    }

    //2.内部创建类的对象且声明为静态
    private static  Singleton instance = new Singleton();

    //3.提供公共的静态方法返回类的对象
    public static Singleton getInstance(){
        return instance;
    }
}
//懒汉式实现单例模式
class Single{

    //1.私有化类的构造器
    private Single(){

    }

    //2.声明当前类对象,并不初始化
    private static Single intance = null;

    //3.声明public，static的返回当前类的方法
    public static Single getIntance(){
        if (intance ==null){
            intance = new Single();
        }
        return intance;
    }
}