package com.okayjam.net;


import com.okayjam.net.netty.tcp.Client;
import com.okayjam.net.netty.tcp.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @description: ${description}
 * @author: Chen wei guang <chen2621978@gmail.com>
 * @create: 2018/07/26 09:49
 **/
public class NettyTest {
    int port = 10001;
    String  host = "127.0.0.1";
    @BeforeEach
    public void  setUp(){
        host = "127.0.0.1";
        port = 10001;
    }
    @Test
    @Disabled("requires a manually running TCP server")
    public  void testTCPServer() throws Exception {
        new Server(port).run();
    }
    @Test
    @Disabled("requires a manually running TCP server")
    public  void testTCPClient() throws Exception {
        new Thread(new Client(host,port)).start();
    }
}
