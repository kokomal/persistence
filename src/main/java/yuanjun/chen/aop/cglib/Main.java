package yuanjun.chen.aop.cglib;

/*
 * CGLIB的优点是可以在纯Java Class的方法上进行切面的操作
 * 覆写MethodInterceptor接口的intercept方法
 * */
public class Main {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        // 通过生成子类的方式创建代理类
        SayHello proxyImp = (SayHello) proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}
