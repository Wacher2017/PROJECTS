package com.reflect.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 获得其他类中的全部构造函数
 *
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-13 20:48
 */
public class Demo_0007 {

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.demo.entity.China");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getConstructor(demo);
        System.out.println("==================================================");
        getConstructor1(demo);
        System.out.println("==================================================");
        getConstructor2(demo);
    }

    /**
     * 获得其他类中的全部构造函数
     * @param demo
     */
    private static void getConstructor(Class<?> demo) {
        Constructor<?> cons[] = demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：" + cons[i]);
        }
    }

    /**
     * 获得其他类中的全部构造函数,获取修饰符
     * @param demo
     */
    private static void getConstructor1(Class<?> demo) {
        Constructor<?>cons[]=demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            Class<?> p[]=cons[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo=cons[i].getModifiers();
            System.out.print(Modifier.toString(mo)+" ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for(int j=0;j<p.length;++j){
                System.out.print(p[j].getName()+" arg"+i);
                if(j<p.length-1){
                    System.out.print(",");
                }
            }
            System.out.println("){}");
        }
    }

    /**
     * 获得其他类中的全部方法，如果方法有异常，也可以获取
     * @param demo
     */
    private static void getConstructor2(Class<?> demo) {
        Method method[]=demo.getMethods();
        for(int i=0;i<method.length;++i){
            Class<?> returnType=method[i].getReturnType();
            Class<?> para[]=method[i].getParameterTypes();
            int temp=method[i].getModifiers();
            System.out.print(Modifier.toString(temp)+" ");
            System.out.print(returnType.getName()+"  ");
            System.out.print(method[i].getName()+" ");
            System.out.print("(");
            for(int j=0;j<para.length;++j){
                System.out.print(para[j].getName()+" "+"arg"+j);
                if(j<para.length-1){
                    System.out.print(",");
                }
            }
            Class<?> exce[]=method[i].getExceptionTypes();
            if(exce.length>0){
                System.out.print(") throws ");
                for(int k=0;k<exce.length;++k){
                    System.out.print(exce[k].getName()+" ");
                    if(k<exce.length-1){
                        System.out.print(",");
                    }
                }
            }else{
                System.out.print(")");
            }
            System.out.println();
        }
    }

}
