package yuanjun.chen.netty_waylau.demo.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bf = (ByteBuf) msg;
        boolean shoulddisp = (bf).readBoolean();
        if (shoulddisp) {
            final ByteBuf time = ctx.alloc().buffer(4); // (2)
            time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
            ctx.writeAndFlush(time); // (3)
        } else {
            final ByteBuf time = ctx.alloc().buffer(4); // (2)
            time.writeInt(0);
            ctx.writeAndFlush(time); // (3)
        }
        bf.release();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time);
        // (3)
        /*
         * f.addListener(new ChannelFutureListener() {
         * 
         * @Override public void operationComplete(ChannelFuture future) { assert f == future; ctx.close();
         * } }); // (4)
         */ }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
