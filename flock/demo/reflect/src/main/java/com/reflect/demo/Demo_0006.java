package com.reflect.demo;

/**
 * 取得其他类中的父类
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:46
 */
public class Demo_0006 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //取得父类
        Class<?> temp = demo.getSuperclass();
        System.out.println("继承的父类：" + temp.getName());
    }

}
