# 反射基础详解
## 一、通过一个完整的对象获得完整的包名和类名
方法：`class.getClass().getName()`
> View Code: com.reflect.demo.Demo_0001

## 二、实例化 `Class` 对象
三种方法： 
1. 推荐此种方式进行构建类：`Class.forName("className")`；
2. java的任何一个java对象都有 `getClass` 方法；
3. 每个类都有class属性：`className.class`。
> View Code: com.reflect.demo.Demo_0002

## 三、通过 `Class` 实例化其他类的对象
通过无参构造实例化对象：
```
Class cls = Class.forName("className");
Object object = (Object) cls.newInstance();
```
> View Code: com.reflect.demo.Demo_0003

注意：当我们把Person中的默认的无参构造函数取消的时候，比如自己定义只定义一个有参数的构造函数之后，会出现错误：
```
public Person(String name) {
    this.name=name;
}
```
然后继续运行上面的程序就会报下面错误：
```
   java.lang.InstantiationException: Reflect.Person
     at java.lang.Class.newInstance0(Class.java:340)
     at java.lang.Class.newInstance(Class.java:308)
     at Reflect.hello.main(hello.java:39)
   Exception in thread "main" java.lang.NullPointerException
     at Reflect.hello.main(hello.java:47)
```
因此在编写使用Class实例化其他类的对象的时候，一定要自己定义无参的构造函数。

## 四、通过 `Class` 调用其他类中的构造函数
也可以通过这种方式通过Class创建其他类的对象
```
Class cls = Class.forName("className");
Constructor<?> cons[]=demo.getConstructors();

//无参构造函数
Object object0 = (Object)cons[0].newInstance();
Object object1 = (Object)cons[1].newInstance(parameter);
//多参构造函数
Object object2 = (Object)cons[2].newInstance(parameter1,parameter);
```
> View Code: com.reflect.demo.Demo_0004

注意：构造函数取出来是有顺序的，`Constructor<?> cons[]=cls.getConstructors()` 的cons[]数组下标对应的对象类里面的构造函数顺序相反，
如Demo_0004中：最后一个构造方法Person(String name, int age)对应于cons[0]，第一个构造方法Person()对应于cons[3]；
顺序不对会造成参数不正确的异常：
```
java.lang.IllegalArgumentException: wrong number of arguments
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
    at java.lang.reflect.Constructor.newInstance(Unknown Source)
    at temp.reflect.GetConstructors.main(GetConstructors.java:23)
```

## 五、返回一个类实现的接口
```
Class cls = Class.forName("className");
Class<?> intes[]=cls.getInterfaces();
```
> View Code: com.reflect.demo.Demo_0005

## 六、取得其他类中的父类
```
Class cls = Class.forName("className");
Class<?> temp = cls.getSuperclass();
```
>View Code: com.reflect.demo.Demo_0006

## 七、获得其他类中的全部构造函数
```
//需要在程序开头添加import java.lang.reflect.*;
Class demo = Class.forName("className");
Constructor<?>cons[]=demo.getConstructors();
for (int i = 0; i < cons.length; i++) {
     System.out.println("构造方法：  "+cons[i]);
}
```
运行结果：
```
构造方法：public com.reflect.demo.entity.China()
构造方法：public com.reflect.demo.entity.China(java.lang.String)
//注意：上述构造函数是没有public 或者private这一类的修饰符的
```
>View Code: com.reflect.demo.Demo_0007  查看`getConstructor()`方法

## 八、获取包含修饰符以及参数的构造函数
```
Class demo=Class.forName("className");
Constructor<?>cons[]=demo.getConstructors();
//获取参数类型
Class<?> p[]=cons[i].getParameterTypes();
for(int j=0;j<p.length;++j){
    System.out.print("参数i类型名："+p[j].getName()+" arg"+i);
}
//获取修饰符
int mo=cons[i].getModifiers();
String modifier = Modifier.toString(mo);
```
运行结果：
```
构造方法：  public com.reflect.demo.entity.China(){}
构造方法：  public com.reflect.demo.entity.China(java.lang.String arg1){}
```
>View Code: com.reflect.demo.Demo_0007  查看`getConstructor1()`方法

## 九、通过反射获取包含所有修饰符、参数以及异常的类的Method
```
//通过反射获取所有的方法；返回的是一个方法数组
Method method[]=Class.forName("className").getMethods();
//获取方法的返回类型；构造方法是没有返回值的。
Class<?> returnType=method[i].getReturnType();
System.out.print(returnType.getName());
//获取方法的返回的异常；返回的是异常数组
Class<?> exce[]=method[i].getExceptionTypes();
System.out.print(exce[i].getName());
```
运行结果：
```
public java.lang.String  getSex ()
public void  setSex (java.lang.String arg0)
public void  sayChina ()
public void  sayHello (java.lang.String arg0,int arg1)
public final void  wait () throws java.lang.InterruptedException 
public final void  wait (long arg0,int arg1) throws java.lang.InterruptedException 
public final native void  wait (long arg0) throws java.lang.InterruptedException 
public boolean  equals (java.lang.Object arg0)
public java.lang.String  toString ()
public native int  hashCode ()
public final native java.lang.Class  getClass ()
public final native void  notify ()
public final native void  notifyAll ()
```
>View Code: com.reflect.demo.Demo_0007  查看`getConstructor2()`方法

## 十、取得其他类的全部属性
```
//获取本类属性
// 取得本类的全部属性
Field[] field = demo.getDeclaredFields();
// 权限修饰符
int mo = field[i].getModifiers();
String priv = Modifier.toString(mo);
// 属性类型
Class<?> type = field[i].getType();
String typeName = type.getName();
//属性名
String fieldName =  field[i].getName();
//获取实现的接口或者父类的属性（注意和demo.getDeclaredFields()区别）
Field[] filed1 = demo.getFields();
```
运行结果：
```
===============本类属性========================
private java.lang.String sex;
===============实现的接口或者父类的属性========================
public static final java.lang.String name;
public static final int age;
```
>View Code: com.reflect.demo.Demo_0008

## 十一、通过反射调用其他类中的方法
```
//无参方法
Method method=demo.getMethod("methodName");
//无参方法调用：只需要类
method.invoke(demo.newInstance());    //不需要返回值
Object obj = method.invoke(demo.newInstance());    //需要返回值
//有参方法：方法名+参数1类型+参数2类型+ ... ...
method=demo.getMethod("methodName", String.class,int.class);
//有参方法调用invoke：类+参数1值+参数2值+ ... ...
//不需要返回值
method.invoke(demo.newInstance(),"test",20);
//需要返回值
Object obj = method.invoke(demo.newInstance(),"test",20);
```
>View Code: com.reflect.demo.Demo_0009
>View Code: com.reflect.demo.Demo_0010

## 十二、通过反射操作属性
```
Field field = class.getDeclaredField("sex");
field.setAccessible(true);
field.set(obj, "value");
```
>View Code: com.reflect.demo.Demo_0011

## 十三、通过反射操作数组
```
int[] array={1,2,3,4,5};
Class<?>demo=array.getClass().getComponentType();    //得到数组里的类型。
String typeName = demo.getName()     //类型名。
String length = Array.getLength(temp)    //数组长度
Object obj = Array.get(temp, index)    //获取数组元素index代表数组下表
Array.set(temp, index, "value");    //设置数组元素
```
运行结果：
```
数组类型：int
数组长度：5
数组的第一个元素：1
修改之后数组的第一个元素为：100
```
>View Code: com.reflect.demo.Demo_0012

通过反射修改数组大小：
```
Class<?>arr=obj.getClass().getComponentType();
Object newArr=Array.newInstance(arr, len);
int co=Array.getLength(obj);
System.arraycopy(obj, 0, newArr, 0, co);
```
>View Code: com.reflect.demo.Demo_0013

## 十四、获得类加载器
```
Test test = new Test();
System.out.println("类加载器:"+test.getClass().getClassLoader().getClass().getName());
```
其实在java中有三种类类加载器。
1. Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
2. Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
3. AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。

如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。 

动态代理示例代码：
>View Code: com.reflect.demo.Demo_0014

类的生命周期

在一个类编译完成之后，下一步就需要开始使用类，如果要使用一个类，肯定离不开JVM。在程序执行中JVM通过装载，链接，初始化这3个步骤完成。
类的装载是通过类加载器完成的，加载器将.class文件的二进制文件装入JVM的方法区，并且在堆区创建描述这个类的java.lang.Class对象。用来封装数据。但是同一个类只会被类装载器装载一次。

链接就是把二进制数据组装为可以运行的状态。
链接分为校验，准备，解析这3个阶段:
校验一般用来确认此二进制文件是否适合当前的JVM（版本），
准备就是为静态成员分配内存空间，并设置默认值
解析指的是转换常量池中的代码作为直接引用的过程，直到所有的符号引用都可以被运行程序使用（建立完整的对应关系）

完成之后，类型也就完成了初始化，初始化之后类的对象就可以正常使用了，直到一个对象不再使用之后，将被垃圾回收。释放空间。
当没有任何引用指向Class对象时就会被卸载，结束类的生命周期.

## 十五、将反射运用于工厂模式
常规的工厂模式：
当我们在添加一个子类的时候，就需要修改工厂类了。如果我们添加太多的子类的时候，改的就会很多。

利用反射机制的工厂模式：
添加任意多个子类的时候，工厂类就不需要修改。

虽然可以通过反射取得接口的实例，但是需要传入完整的包和类名。
而且用户也无法知道一个接口有多少个可以使用的子类，所以我们通过属性文件的形式配置所需要的子类。

结合属性文件的工厂模式：
需要创建一个资源文件

>View Code: com.reflect.demo.Demo_0015