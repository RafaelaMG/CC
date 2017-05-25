/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Rafaela
 */
public class UdpMonitor {

    private String connections;
    private String udp;
    private TcpServer tcp;

    public UdpMonitor(String c) {
        this.connections = c;
        try {
            this.udp = InetAddress.getLocalHost().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void register(TcpServer t) {
        tcp = t;

        try {
            try (DatagramSocket dsocket = new DatagramSocket()) {
                String str = "Nothing:" + udp;
                
                
                // Sends IP
                InetAddress ip = InetAddress.getByName(connections);
                
                DatagramPacket dpacket = new DatagramPacket(str.getBytes(), str.length(), ip, 5555);
                dsocket.send(dpacket);
            }
            Thread tr = new Thread() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(120);
                        register(t);
                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                }
            };

            tr.start();
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }

    public void broadcast() {
        try {
            try (DatagramSocket dsocket = new DatagramSocket()) {
                String str = "Update:" + udp + ":" + tcp.getConnects();
                
                InetAddress ip = InetAddress.getByName(connections);
                
                DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 5555);
                dsocket.send(dp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void receive() {

        try {
            try (DatagramSocket dsocket = new DatagramSocket(5556)) {
                byte[] buf = new byte[2048];
                DatagramPacket dpacket = new DatagramPacket(buf, 2048);
                dsocket.receive(dpacket);
                String str = new String(dpacket.getData(), 0, dpacket.getLength());
                
                System.out.println(str);
            }
            this.broadcast();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
