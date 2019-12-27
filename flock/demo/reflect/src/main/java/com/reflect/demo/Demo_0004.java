package com.reflect.demo;

import com.reflect.demo.entity.Person;

import java.lang.reflect.Constructor;

/**
 * 通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:22
 */
public class Demo_0004 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person person1 = null;
        Person person2 = null;
        Person person3 = null;
        Person person4 = null;
        Constructor<?> cons[] = demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println(cons[i]);
        }
        try {
            person4 = (Person) cons[0].newInstance("赵六", 20);
            person3 = (Person) cons[1].newInstance(18);
            person2 = (Person) cons[2].newInstance("王五");
            person1 = (Person) cons[3].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
        System.out.println(person4);
    }

}
