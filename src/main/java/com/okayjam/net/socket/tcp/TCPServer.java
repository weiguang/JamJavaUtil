package com.okayjam.net.socket.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2018/6/13 21:10.
 */
public class TCPServer {
    private int MaxClient = 2;
    static  String  ExitMessage = "88";
    static int PORT  = 10001;
    public static void main(String[] args) throws IOException {
        new TCPServer().StartListen();
    }

    public void StartListen() throws IOException {

        ThreadPoolExecutor pool  =new ThreadPoolExecutor(0, MaxClient,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        Socket task = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("监听开始");

            int clientNumber = 0;
            while (true) {
                task = serverSocket.accept();
                try {
                    pool.execute(new ServerTask(task));
                    clientNumber++;
                    System.out.println("Client : " + clientNumber + ", " + task.getRemoteSocketAddress());
                }catch (RejectedExecutionException e) {
                    e.printStackTrace();
                    if (task != null) {
                        task.close();
                    }
                }
            }
        }
    }
}

class ServerTask implements Runnable {
    private Socket task = null;

    public ServerTask(Socket task) {
        this.task = task;
    }
    @Override
    public void run() {
        //System.out.println("处理开始");
        try (
                ObjectOutputStream output = new ObjectOutputStream(task.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(task.getInputStream());
        ) {
            while (true) {
                String msg = input.readUTF();
                Date date = new Date();
                System.out.println( task.getRemoteSocketAddress()+" 发来消息(" + date.getTime() + "):" + msg);
                output.writeUTF("server已经接收到(" + date + "):" + msg);
                output.flush();
                if (msg.equals(TCPServer.ExitMessage)) {break;}
            }
        } catch (IOException e) {
            System.out.println("已经断开连接");
           // e.printStackTrace();
        }
    }
}



