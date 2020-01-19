package com.reflect.demo.proxy;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 14:13
 */
public class SubjectReal implements ISubject {

    @Override
    public String say(String name, int age) {
        return name + "  " + age;
    }

}
