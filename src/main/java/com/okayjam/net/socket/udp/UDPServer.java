package com.okayjam.net.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @description: UDP接收
 * @author: Chen wei guang
 * @create: 2018/08/06 14:54
 **/
public class UDPServer extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[2048];
    int UDPort = 4445;

    public UDPServer() throws SocketException {
        socket = new DatagramSocket(UDPort);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                String received
                        = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);

                if (received.equals("end")) {
                    running = false;
                    continue;
                }

                //返回
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String returnMsg = "server received:" + received;
                packet = new DatagramPacket( returnMsg.getBytes(),  returnMsg.getBytes().length, address, port);
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        socket.close();
    }

    public static void main(String[] args) throws SocketException {
       new UDPServer().start();
    }
}