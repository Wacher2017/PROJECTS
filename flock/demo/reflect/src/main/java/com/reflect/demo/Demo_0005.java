package com.reflect.demo;

/**
 * 返回一个类实现的接口
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:34
 */
public class Demo_0005 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存所有接口
        Class<?> intes[] = demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口：" + intes[i].getName());
        }
    }

}
