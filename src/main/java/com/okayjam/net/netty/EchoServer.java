package com.okayjam.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 * • 配置服务器功能，如线程、端口 • 实现服务器处理程序，它包含业务逻辑，决定当有一个请求连接或接收数据时该做什么
 *
 * @author wilson
 *
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //连接池处理数据
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //server端引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //装配bootstrap
                serverBootstrap.group(eventLoopGroup)
                    //指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    //设置InetSocketAddress让服务器监听某个端口已等待客户端连接。
                    .localAddress(new InetSocketAddress(port))
                    //设置childHandler执行所有的连接请求
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch)   {
                            //注册handler
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            // 最后绑定服务器等待直到绑定完成，调用sync()方法会阻塞直到服务器完成绑定,然后服务器等待通道关闭，
            // 因为使用sync()，所以关闭操作也会被阻塞。
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(20000).start();
    }
}