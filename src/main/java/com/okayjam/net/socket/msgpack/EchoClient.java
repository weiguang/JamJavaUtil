package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:33
 **/
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.TimeUnit;

/**
 * netty的客户端
 */

public class EchoClient {

    private final String host;

    private final int port;

    private final int sendNum;

    SocketChannel channel;

    private EchoClient(String host, int port, int sendNum) {
        this.host = host;
        this.port = port;
        this.sendNum = sendNum;
    }

    public void run() {

        //NioEventLoopGroup是一个线程组，它包含了一组NIO线程
        EventLoopGroup group = new NioEventLoopGroup();



        try {
            //客户端辅助启动类
            Bootstrap b = new Bootstrap();

            //设置线程组
            b.group(group)
                    .channel(NioSocketChannel.class)//设置Channel
                    .option(ChannelOption.TCP_NODELAY, true)//设置TCP的参数
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {//匿名内部类设置handler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //解决拆包、粘包读写问题
                            //在解码器之前增加LengthFieldBasedFrameDecoder，用于处理半包消息，这样接受到的永远是整包消息
                            //个人觉得和分隔符的意义差不多
                            socketChannel.pipeline().addLast("frameDecode",
                                    new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            //解码
                            socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                            //在编码器之前增加2个消息的消息长度字段
                            socketChannel.pipeline().addLast("frameEncode", new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                            socketChannel.pipeline().addLast(new EchoClientHandler(sendNum));
                        }
                    });
            //异步连接客户端，同步阻塞直到连接成功
            ChannelFuture f = b.connect(host, port).sync();
            //阻塞，等待客户端链路关闭后main函数才退出
            f.channel().closeFuture().sync();
             channel = (SocketChannel)f.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendMeg(String s) {
        ChannelFuture channelFuture = channel.writeAndFlush(s);
      //  Void unused = channelFuture.get(8, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        String host = "10.56.44.120";
        int port = 63000;
        new EchoClient(host, port, 10).run();
    }
}