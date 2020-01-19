package com.reflect.demo.factory;

/**
 * 构造工厂类
 *
 *   传统工厂模式（不用反射的工厂模式）
 *   也就是说以后如果我们在添加其他的实例的时候只需要修改工厂类就行了
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 16:10
 */
public class NormalFactory {

    public static Fruit getInstance(String fruitName) {
        Fruit fruit = null;
        if("Apple".equals(fruitName)) {
            fruit = new Apple();
        } else if("Orange".equals(fruitName)) {
            fruit = new Orange();
        }
        return fruit;
    }
}
