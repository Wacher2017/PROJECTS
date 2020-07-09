package com.lms.common.utils;

import com.lms.common.BaseTest;
import org.junit.Test;

/**
 * 字符串工具类测试
 */
public class StringUtilsTest extends BaseTest {

    @Test
    public void toUnderScoreCase() throws Exception {
        String s = "helloWorld";
        String r = StringUtils.toUnderScoreCase(s);
        System.out.println(r);
        assert "hello_world".equals(r);
        String s1 = "HelloWorld";
        String r1 = StringUtils.toUnderScoreCase(s1);
        System.out.println(r1);
        assert "hello_world".equals(r1);
        String s2 = "_helloWorld";
        String r2 = StringUtils.toUnderScoreCase(s2);
        System.out.println(r2);
        assert "_hello_world".equals(r2);
        String s3 = "_HelloWorld";
        String r3 = StringUtils.toUnderScoreCase(s3);
        System.out.println(r3);
        assert "__hello_world".equals(r3);
    }

    @Test
    public void convertToCamelCase() throws Exception {
        String s = "HELLO_WORLD";
        String r = StringUtils.convertToCamelCase(s);
        System.out.println(r);
        assert "HelloWorld".equals(r);
        String s1 = "_HELLO_WORLD";
        String r1 = StringUtils.convertToCamelCase(s1);
        System.out.println(r1);
        assert "HelloWorld".equals(r1);
    }

    @Test
    public void toCamelCase() throws Exception {
        String s = "hello_world";
        String r = StringUtils.toCamelCase(s);
        System.out.println(r);
        assert "helloWorld".equals(r);
        String s1 = "_Hello_World";
        String r1 = StringUtils.toCamelCase(s1);
        System.out.println(r1);
        assert "HelloWorld".equals(r1);
    }

}
