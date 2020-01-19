package com.reflect.demo;

import java.lang.reflect.Method;

/**
 * 通过反射调用其他类中的方法
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 09:52
 */
public class Demo_0009 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //调用China类中的sayChina方法
            Method method = demo.getMethod("sayChina");
            method.invoke(demo.newInstance());
            //调用China类中的sayHello方法
            method = demo.getMethod("sayHello", String.class, int.class);
            method.invoke(demo.newInstance(),"张三",20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
