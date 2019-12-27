package com.reflect.demo.entity;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:39
 */
public class China implements IChina {

    private String sex;

    public China() {}

    public China(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void sayChina() {
        System.out.println("hello, china");
    }

    @Override
    public void sayHello(String name, int age) {
        System.out.println(name + "   " + age);
    }
}
