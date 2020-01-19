package com.reflect.demo;

import java.lang.reflect.Array;

/**
 * 通过反射修改数组大小
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 10:46
 */
public class Demo_0013 {

    public static void main(String[] args) {
        int[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] newTemp = (int[]) arrayInc(temp, 15);
        print(newTemp);
        System.out.println();
        System.out.println("==================================");
        String[] atr = {"a", "b", "c"};
        String[] str1 = (String[]) arrayInc(atr, 8);
        print(str1);
    }

    /**
     * 修改数组大小
     */
    public static Object arrayInc(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }

    /**
     * 打印
     */
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if(!c.isArray()) {
            return;
        }
        System.out.println("数组的长度为：" + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
    }

}
