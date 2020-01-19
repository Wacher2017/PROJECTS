package com.reflect.demo;

import java.lang.reflect.Method;

/**
 * 调用其他类的set和get方法
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 10:17
 */
public class Demo_0010 {

    public static void main(String[] args) {
        Class<?> demo = null;
        Object obj = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            obj = demo.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setter(obj, "Sex", "男", String.class);
        getter(obj, "Sex");
    }

    /**
     *
     * @param obj 操作的对象
     * @param att 操作的属性
     */
    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param: obj 操作的对象
     * @param: att 操作的属性
     * @param: value 设置的值
     * @param: type 参数的属性
     */
    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
