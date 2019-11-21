package example1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author xiachao
 * @date 2019/11/21
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        final ByteBuf time = ctx.alloc().buffer(4);
//        int num = (int) (System.currentTimeMillis() / 1000L + 2208988800L);
//        System.out.println(num);
//        time.writeInt(num);
//
//        final ChannelFuture f = ctx.writeAndFlush(time);
//        f.addListener((ChannelFutureListener) future -> {
//            assert f == future;
//            ctx.close();
//        });
//
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());

        f.addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * discard message
         */
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            System.out.println(in.toString(CharsetUtil.UTF_8));
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }

        /**
         * echo
         */
//        ctx.write(msg);
//        ctx.flush();
        super.channelRead(ctx, msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
