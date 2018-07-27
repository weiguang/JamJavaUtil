package com.okayjam.net.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 这个类使用 Netty 实现了简单的Http服务器，可以处理get和post请求，并打印请求信息
 *                  ref.https://blog.csdn.net/huangshanchun/article/details/78302602
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/24 15:17
 **/
public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    private String content = "hello world";
    private final static String LOC = "302";
    private final static String NOT_FOND = "404";
    private final static String BAD_REQUEST = "400";
    private final static String INTERNAL_SERVER_ERROR = "500";
    private static Map<String, HttpResponseStatus> mapStatus = new HashMap<String, HttpResponseStatus>();

    static {
        mapStatus.put(LOC, HttpResponseStatus.FOUND);
        mapStatus.put(NOT_FOND, HttpResponseStatus.NOT_FOUND);
        mapStatus.put(BAD_REQUEST, HttpResponseStatus.BAD_REQUEST);
        mapStatus.put(INTERNAL_SERVER_ERROR, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            boolean keepaLive = HttpUtil.isKeepAlive(request);
            System.out.println("method" + request.method());
            System.out.println("uri" + request.uri());

            String uri = request.uri().replace("/", "").trim();
            if (mapStatus.get(uri) != null) {
                httpResponse.setStatus(mapStatus.get(uri));
                httpResponse.content().writeBytes(mapStatus.get(uri).toString().getBytes());
            } else {
                httpResponse.content().writeBytes(content.getBytes());
            }
            //设置返回内容
            ByteBuf content = Unpooled.copiedBuffer("Hello World!!!\n", CharsetUtil.UTF_8);
            //创建响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);

        }else if (msg instanceof HttpContent) {
            LastHttpContent httpContent = (LastHttpContent) msg;
            ByteBuf byteData = httpContent.content();
            if (byteData instanceof EmptyByteBuf) {
                System.out.println("Content：无数据");
            } else {
                byte[] byteArray = new byte[byteData.capacity()];
                byteData.readBytes(byteArray);
                String content = new String(byteArray);
                System.out.println("Content:" + content);
            }
            //httpResponse.content().writeBytes("Hello".getBytes());
           // ctx.writeAndFlush(httpResponse);
        }
//            //重定向处理
//            if (httpResponse.status().equals(HttpResponseStatus.FOUND)) {
//                httpResponse.headers().set(HttpHeaderNames.LOCATION, "https://www.baidu.com/");
//            }
//            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
//            httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());

    }

}