package com.reflect.demo;

import com.reflect.demo.entity.Demo;

/**
 * 通过一个对象获得完整的包名和类名
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 16:48
 */
public class Demo_0001 {

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.getClass().getName());
    }

}
