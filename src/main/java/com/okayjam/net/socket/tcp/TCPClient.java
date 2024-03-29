package com.okayjam.net.socket.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2018/6/13 21:05.
 */
public class TCPClient {
    public static void main (String[] args) throws IOException {
        int PORT = 10001;
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        try{
            // 连接方式1
            socket = new Socket("127.0.0.1",PORT);
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);
            // 连接方式2
/*            InetSocketAddress addr = new InetSocketAddress("127.0.0.1",PORT);
            socket = new Socket();
            socket.connect(addr);*/


            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            while(true) {
                String msg = scanner.nextLine();
                output.writeUTF(msg);
                output.flush();
                String re = input.readUTF();
                System.out.println(re);
                if(msg.equals(TCPServer.ExitMessage)) {break;}
            }
        }finally {
            input.close();
            output.close();
            socket.close();
        }
    }

}
