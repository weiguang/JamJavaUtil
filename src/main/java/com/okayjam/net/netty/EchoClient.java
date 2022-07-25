package com.okayjam.net.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * • 连接服务器 • 写数据到服务器 • 等待接受服务器返回相同的数据 • 关闭连接
 *
 * @author wilson
 *
 */
public class EchoClient {

    private final String host;
    private final int port;


    SocketChannel channel;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        // EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try {
            // 客户端引导类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(nioEventLoopGroup)
                    //指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
                    .channel(NioSocketChannel.class)
                    //地址
                    .remoteAddress(new InetSocketAddress(host, port))
                    //业务处理类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 链接服务器
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channel = (SocketChannel) channelFuture.channel();
        } finally {
            //nioEventLoopGroup.shutdownGracefully().sync();
        }
    }

    public void sendMeg(String s) throws InterruptedException, ExecutionException, TimeoutException {
        ChannelFuture channelFuture = channel.writeAndFlush(Unpooled.copiedBuffer(s, CharsetUtil.UTF_8)).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println(channelFuture.isSuccess());
                Void unused = channelFuture.get();
                System.out.println();

            }
        });
          Void unused = channelFuture.get(8, TimeUnit.SECONDS);
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        EchoClient client = new EchoClient("localhost", 20000);
        client.start();
        client.sendMeg("11111111111111");
    }
}