package com.okayjam.net.netty.tcp;

/**
 * @description: ${description}
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/26 09:43
 **/

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.UnsupportedEncodingException;

public class ClientHandler extends SimpleChannelInboundHandler {
    ChannelHandlerContext ctx;
    /**
     * tcp链路简历成功后调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }
    public boolean sendMsg(String msg){
        System.out.println("客户端发送消息："+msg);
        byte[] req = msg.getBytes();
        ByteBuf m = Unpooled.buffer(req.length);
        m.writeBytes(req);
        ctx.writeAndFlush(m);
        return msg.equals("q")?false:true;
    }
    /**
     * 收到服务器消息后调用
     * @throws UnsupportedEncodingException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"utf-8");
        System.out.println("服务器消息："+body);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("0000");
    }

    /**
     * 发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
