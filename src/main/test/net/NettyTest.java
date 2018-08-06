package net;


import com.okayjam.net.netty.tcp.Client;
import com.okayjam.net.netty.tcp.Server;
import org.junit.Before;
import org.junit.Ignore;

/**
 * @description: ${description}
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/26 09:49
 **/
public class NettyTest {
    int port = 10001;
    String  host = "127.0.0.1";
    @Before
    public void  setUp(){
        host = "127.0.0.1";
        port = 10001;
    }
    @Ignore
    public  void testTCPServer() throws Exception {
        new Server(port).run();
    }
    @Ignore
    public  void testTCPClient() throws Exception {
        new Thread(new Client(host,port)).start();
    }
}
