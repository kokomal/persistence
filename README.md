# persistence
orm持久化相关demo示范  
aop包下，设置controller指定hello*方法的日志触发条件，和日志的格式  

disruptor包实现了高速并发处理的demo，通过post请求/data,参数为{"deviceData":"xxx"}来触发disruptor的数据消费  
disruptor非常适合处理工作流类型的事件处理  


鉴于netty5已经被废弃，但由于concurrent包下的netty采用5.0版本，因此在此开辟4.x版本的demo  