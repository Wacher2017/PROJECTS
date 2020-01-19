package com.reflect.demo;

import java.lang.reflect.Field;

/**
 * 通过反射操作属性
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 10:27
 */
public class Demo_0011 {

    public static void main(String[] args) throws Exception {
        Class<?> demo = null;
        Object obj = null;

        demo = Class.forName("com.reflect.demo.entity.China");
        obj = demo.newInstance();

        Field field = demo.getDeclaredField("sex");
        field.setAccessible(true);
        field.set(obj, "男");
        System.out.println(field.get(obj));
    }

}
