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