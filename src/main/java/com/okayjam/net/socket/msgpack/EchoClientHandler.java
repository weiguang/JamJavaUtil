package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:34
 **/
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger log = LoggerFactory.getLogger(EchoClientHandler.class);
    private final int sendNum;

    public EchoClientHandler(int sendNum) {
        this.sendNum = sendNum;
    }

    /**
     * 连接服务端成功后开始发送消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < sendNum; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCmd("GetIpinfo");
            userInfo.setIp("10.56.44." + (120 + i));
            ctx.write(userInfo);
        }
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Client receive the msgpack message: " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
