package com.reflect.demo;

import com.reflect.demo.factory.Fruit;
import com.reflect.demo.factory.InitProperties;
import com.reflect.demo.factory.NormalFactory;
import com.reflect.demo.factory.ReflectFactory;

import java.util.Properties;

/**
 * 将反射用于工厂模式
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 14:24
 */
public class Demo_0015 {

    public static void main(String[] args) {
        System.out.println("=============传统工厂模式=============");
        /**
         * 传统工厂模式
         */
        Fruit fruit = NormalFactory.getInstance("Orange");
        fruit.eat();

        System.out.println("=============用反射的工厂模式=============");
        /**
         * 用反射的工厂模式
         */
        Fruit fruit1 = ReflectFactory.getInstance("com.reflect.demo.factory.Apple");
        if(fruit1 != null) {
            fruit1.eat();
        }

        System.out.println("=============用反射结合属性文件的工厂模式=============");
        /**
         * 结合属性文件的工厂模式
         */
        try {
            Properties properties = InitProperties.getPro();
            Fruit fruit2 = ReflectFactory.getInstance(properties.getProperty("apple"));
            if(fruit2 != null) {
                fruit2.eat();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
