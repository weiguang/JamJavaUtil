package com.okayjam.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 客户端连接服务器后被调用
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        // • 从服务器接收到数据后调用
        System.out.println("client 读取server数据..");
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // • 发生异常时被调用
        System.out.println("client exceptionCaught..");
        cause.printStackTrace();
        // 释放资源
        ctx.close();
    }
}