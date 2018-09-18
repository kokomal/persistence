## simplechat简单聊天室
支持多客户端连接和发送消息，服务端收到消息后，遍历channelGroup，进行广播  
服务端采用简单的StringEncoder/Decoder和DelimiterBasedFrameDecoder进行编解码  
