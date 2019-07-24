package com.okayjam.net.socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @description: ${description}
 * @author: Chen wei guang
 * @create: 2018/08/06 14:55
 **/
public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    String UDPHost = "localhost";
    int UDPort = 4445;

    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(UDPHost);
    }

    public void connect() {
        try {
            //socket = new DatagramSocket();
            //address = InetAddress.getByName(UDPHost);
            new UDPSendThread().start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    class UDPSendThread extends Thread {
        private byte[] msgbuf;
        @Override
        public void run() {
            /** 反复将输入发送到服务端 */
            while (true){
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 包装控制台输入流
                try {
                    String msg = in.readLine();
                    msgbuf = msg.getBytes();
                    DatagramPacket packet
                            = new DatagramPacket(msgbuf, msgbuf.length, address, UDPort);
                    socket.send(packet);
                   //接收
                    byte[] buf = new byte[2048];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    System.out.println(new String(packet.getData(), 0, packet.getLength()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String sendEcho(String msg) throws IOException {
        byte[] buf;
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, UDPort);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        System.out.println(received);
        return received;
    }


    public static void main(String[] args) throws IOException {
        new UDPClient().connect();
    }
}