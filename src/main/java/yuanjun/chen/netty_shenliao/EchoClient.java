package yuanjun.chen.netty_shenliao;

import java.net.InetSocketAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // #1 创建 bootstrap 客户端
            b.group(group) // #2 这里指定 NioEventLoopGroup 处理客户端事件
                    .channel(NioSocketChannel.class) // #3 指定通道类型
                    .remoteAddress(new InetSocketAddress(host, port)) // #4 设置绑定地址和端口
                    .handler(new ChannelInitializer<SocketChannel>() { // #5 使用ChannelInitializer，指定通道处理器
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());// #6 将EchoClientHandler加入到管道
                        }
                    });
            ChannelFuture f = b.connect().sync(); // #7 连接到服务端
            f.channel().closeFuture().sync(); // #8 阻塞直到客户端通道关闭
        } finally {
            group.shutdownGracefully().sync(); // #9 关闭线程池释放资源
        }
    }


    public static void main(String[] args) throws Exception {
        new EchoClient("127.0.0.1", 8989).start();
    }
}
