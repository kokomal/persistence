/**
 * @Title: StaticProxyMain.java
 * @Package: yuanjun.chen.proxy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2019年1月8日 下午1:40:49
 * @version V1.0
 * @Copyright: 2019 All rights reserved.
 */
package yuanjun.chen.proxy;

/**
 * @ClassName: StaticProxyMain
 * @Description: 静态代理采用硬编码的方式将原始类Station包裹起来
 * @author: 陈元俊
 * @date: 2019年1月8日 下午1:40:49
 */
public class StaticProxyMain {
    public static void main(String[] args) {
        StationProxy proxy = new StationProxy(new Station());
        proxy.inquire();
        
        proxy.sellTicket();
        
        proxy.withdraw();
    }
}
