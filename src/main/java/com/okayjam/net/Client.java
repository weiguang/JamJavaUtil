package com.okayjam.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2018/6/13 21:05.
 */
public class Client {
    public static void main (String[] args) throws IOException {
        System.out.println("Hello world!");
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",10001);
        try{
            socket = new Socket();
            socket.connect(addr);

            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF("客户端发送第一个数据");
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            String re = input.readUTF();
            System.out.println(re);

        }finally {
            input.close();
            output.close();
            socket.close();
        }
    }

}
