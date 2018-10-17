/**
 * @Title: WebConfig.java
 * @Package: yuanjun.chen.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年10月15日 下午6:00:21
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yuanjun.chen.dao.jpa.reading.ReaderHandlerMethodArgumentResolver;

/**
 * @ClassName: WebConfig
 * @Description: 以前写SpringMVC的时候，如果需要访问一个页面，必须要写Controller类，
 *               然后再写一个方法跳转到页面，感觉好麻烦，其实重写WebMvcConfigurer中的， addViewControllers方法即可达到效果了
 * @author: 陈元俊
 * @date: 2018年10月15日 下午6:00:21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/readingList"); // /直接重定向到readingList，如果注释掉那么访问localhost:9922/会404
        registry.addViewController("/login").setViewName("login"); // 省略controller的login冗余代码
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver()); // 非常重要的参数解析器，这样Reader就可以从model里面取出来解析成需要的pojo
    }
}
