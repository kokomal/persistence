# persistence
##大杂烩，基本上和持久化及spring boot相关，会持续更新包括网络安全、持久化和web的一些组件和模板化代码  

orm持久化相关demo示范  
aop包下，设置controller指定hello*方法的日志触发条件，和日志的格式  

disruptor包实现了高速并发处理的demo，通过post请求/data,参数为{"deviceData":"xxx"}来触发disruptor的数据消费  
disruptor非常适合处理工作流类型的事件处理  

reading包及facade下的ReadingListController为参考SpringBootInAction教程构建的项目，参考并勘误了原书代码的错误，  
@SpecialThanksTo 原书作者Craig Walls  

鉴于netty5已经被废弃，但由于concurrent包下的netty采用5.0版本，因此在此开辟4.x版本的demo  

dao.mybatis包下实现了MyBatis的简易demo  
resources目录的mybatis_generator.properties和generatorConfig.xml是供（mybatis-generator-maven-plugin）逆向工程使用的配置文件  

dao.jpa包下实现了SpringData Jpa的简易demo  
@SpecialThanksTo Giau Ngo(https://github.com/giaunv, https://github.com/hellokoding 和 https://hellokoding.com/ )的非常educative的教程  