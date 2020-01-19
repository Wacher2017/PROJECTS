package com.reflect.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 接下来取得其他类的全部属性，最后将这些整理在一起，也就是通过class取得一个类的全部框架
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 21:05
 */
public class Demo_0008 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        getProperties(field);
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] field1 = demo.getFields();
        getProperties(field1);
    }

    private static void getProperties(Field[] field) {
        for (int j = 0; j < field.length; j++) {
            // 权限修饰符
            int mo = field[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[j].getType();
            System.out.println(priv + " " + type.getName() + " " + field[j].getName() + ";");
        }
    }

}
