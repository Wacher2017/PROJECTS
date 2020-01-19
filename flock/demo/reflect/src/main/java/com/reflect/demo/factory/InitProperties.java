package com.reflect.demo.factory;

import java.io.*;
import java.util.Properties;

/**
 * 操作属性文件类
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-14 16:22
 */
public class InitProperties {

    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        File file = new File("fruit.properties");
        if(file.exists()) {
            pro.load(new FileInputStream(file));
        } else {
            System.out.println("文件不存在");
            pro.setProperty("apple","com.reflect.demo.factory.Apple");
            pro.setProperty("orange","com.reflect.demo.factory.Orange");
            pro.store(new FileOutputStream(file), "FRUIT CLASS");
        }
        return pro;
    }

}
