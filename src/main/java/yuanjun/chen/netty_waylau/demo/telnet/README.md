## 简单的telnet示范
服务端和客户端分别启动后，服务端首先发送欢迎消息（在channelActive这个Handler第一次channel激活的时候）  
然后服务端接收客户端的所有input的消息并加以反馈  