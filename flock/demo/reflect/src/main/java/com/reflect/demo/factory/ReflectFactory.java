package com.reflect.demo.factory;

/**
 * 构造工厂类
 *
 *   用反射的工厂模式,就算我们添加任意多个子类的时候，工厂类就不需要修改
 *
 *   虽然可以通过反射取得接口的实例，但是需要传入完整的包和类名。
 *   而且用户也无法知道一个接口有多少个可以使用的子类，所以我们通过属性文件的形式配置所需要的子类
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 16:13
 */
public class ReflectFactory {

    public static Fruit getInstance(String className) {
        Fruit fruit = null;
        try {
            fruit = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }

}
