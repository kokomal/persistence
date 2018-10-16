package yuanjun.chen.netty_waylau.demo.websocketchat;

import java.util.concurrent.TimeUnit;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 服务端 ChannelInitializer
 * 
 * @author waylau.com
 * @date 2015-3-13
 */
public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> { // 1
    /** 读超时. */
    private static final int READ_IDEL_TIME_OUT = 5;
    /** 写超时. */
    private static final int WRITE_IDEL_TIME_OUT = 5;
    /** 所有超时. */
    private static final int ALL_IDEL_TIME_OUT = 5;
    
    //public TextWebSocketFrameHandler tt = new TextWebSocketFrameHandler();
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {// 2
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        
        /*这里模仿心跳对时间进行.*/
        pipeline.addLast(
                new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS));
        
        pipeline.addLast("vv", new TextWebSocketFrameHandler());

    }
}
