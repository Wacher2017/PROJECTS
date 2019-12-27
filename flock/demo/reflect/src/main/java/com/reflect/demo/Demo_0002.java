package com.reflect.demo;

import com.reflect.demo.entity.Demo;

/**
 * 实例化Class类对象
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 16:50
 */
public class Demo_0002 {

    public static void main(String[] args) {
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        try {
            //一般尽量采用这种方式
            demo1 = Class.forName("com.reflect.demo.entity.Demo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        demo2 = new Demo().getClass();
        demo3 = Demo.class;

        System.out.println("类名称：" + demo1.getName());
        System.out.println("类名称：" + demo2.getName());
        System.out.println("类名称：" + demo3.getName());
    }

}
