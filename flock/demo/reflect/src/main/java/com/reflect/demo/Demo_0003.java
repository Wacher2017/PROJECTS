package com.reflect.demo;

import com.reflect.demo.entity.Demo;

/**
 * 通过Class实例化其他类的对象
 * 通过无参构造实例化对象
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:05
 */
public class Demo_0003 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.Demo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Demo demo1 = null;
        try {
            demo1 = (Demo) demo.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        demo1.setName("王五");
        demo1.setAge(18);
        System.out.println(demo1);
    }

}

/**
 * 注意：当把Person中的默认的无参构造函数取消的时候，自定义一个有参数的构造函数如下：
 *
 *     public Person(String name, int age) {
 *         this.name = name;
 *         this.age = age;
 *     }
 *
 *  会出现错误：
 *   java.lang.InstantiationException: Reflect.Person
 *
 *     at java.lang.Class.newInstance0(Class.java:340)
 *
 *     at java.lang.Class.newInstance(Class.java:308)
 *
 *     at Reflect.hello.main(hello.java:39)
 *
 *   Exception in thread "main" java.lang.NullPointerException
 *
 *     at Reflect.hello.main(hello.java:47)
 *
 * 所以再编写使用Class实例化其他类的对象的时候，一定要自己定义无参的构造函数
 *
 */