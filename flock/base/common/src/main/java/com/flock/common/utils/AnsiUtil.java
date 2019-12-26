package com.flock.common.utils;

import org.fusesource.jansi.Ansi;
import org.springframework.core.env.Environment;

/**
 * Created by Chunming_Wang on 2019/12/23.
 */
public class AnsiUtil {

    private static boolean enableAnsi;

    static {
        Boolean value = false;
        try {
            Environment environment = SpringContextUtil.getBean(Environment.class);
            value = environment.getProperty("flock.enable-ansi",boolean.class);
            value = value == null ? false : value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        enableAnsi = value;
    }

    public static String getAnsi(Ansi.Color color, String text){

        if (enableAnsi){
            return Ansi.ansi().eraseScreen().fg(color).a(text).reset().toString();
        }
        return text;
    }

    public static String getAnsi(Ansi.Color color,String text,boolean flag){
        if (flag){
            return Ansi.ansi().eraseScreen().fg(color).a(text).reset().toString();
        }
        return text;
    }

}
