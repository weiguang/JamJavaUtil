package com.okayjam.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2018/6/13 21:10.
 */
public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(10001));
        System.out.println("监听开始");
        Socket task = serverSocket.accept();
        new Thread(new ServerTask(task)).start();
    }

    private static class ServerTask  implements Runnable {
        private Socket task = null;
        public ServerTask (Socket task){
            this.task = task;
        }

        @Override
        public void run() {
            System.out.println("处理开始");
            try(
                ObjectOutputStream output = new ObjectOutputStream(task.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(task.getInputStream());
                ) {

                String userName = input.readUTF();
                System.out.println(userName);
                output.writeUTF("server已经接收到");
                output.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



