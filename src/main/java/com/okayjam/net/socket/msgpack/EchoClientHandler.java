package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:34
 **/
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);

    private final int sendNum;

    public EchoClientHandler(int sendNum){
        this.sendNum = sendNum;
    }


    /**
     * 连接服务端成功后开始发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        UserInfo userInfo = new UserInfo();
        userInfo.setCmd("GetIpinfo");
        userInfo.setIp("10.56.44.120");
        ctx.writeAndFlush(userInfo);


    }



    /**
     * 读取客户端的返回消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack messag:" + msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        final byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        //mp的read方法将其反序列化为object对象
        MessagePack mp = new MessagePack();
//        mp.register(UserInfo.class);
        Map re = mp.read(array, Templates.tMap(Templates.TString, Templates.TString));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 发生异常时关闭ctx
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}

