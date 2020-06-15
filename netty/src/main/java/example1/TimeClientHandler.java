package example1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;

/**
 * @author xiachao
 * @date 2019/11/21
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        buf.writeBytes(byteBuf);
        byteBuf.release();

        if (buf.readableBytes() >= 4) {
            long currentTimesMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimesMillis));
            ctx.close();
        }

//        try {
//
//            long currentTimesMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimesMillis));
//            ctx.close();
//
//        } finally {
//            byteBuf.release();
//        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
