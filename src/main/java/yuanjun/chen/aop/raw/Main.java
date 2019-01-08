package yuanjun.chen.aop.raw;

import java.lang.reflect.Proxy;

/*
 * 采用原生的JDK的aop方式，需要：
 * 1.方法需要在interface里面
 * 2.这个interface需要一个impl
 * 3.AOPHandler用于实现InvocationHandler接口
 * 4.Override这个接口的invoke方法
 * 5.用动态代理获得接口实例
 * 6.调用接口实例的方法
 * 问题在于，首先需要添加切面的类一定要由interface实现而来
 * 怎么实现环绕呢？
 * */
public class Main {
    public static void main(String[] args) {
        ManSayHelloWorld sayHelloWorld = new ManSayHelloWorld();
        AOPHandle handle = new AOPHandle(sayHelloWorld);
        ISayHelloWorld i = (ISayHelloWorld) Proxy.newProxyInstance(ManSayHelloWorld.class.getClassLoader(),
                new Class[] {ISayHelloWorld.class}, handle);
        i.say();
    }
}
