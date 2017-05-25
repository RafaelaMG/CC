/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafaela
 */
public class Server {

    private UdpMonitor u;
    private TcpServer tcp;
    private String connect;

    public Server(String c) {
        this.connect = c;
        u = new UdpMonitor(connect);
        tcp = new TcpServer();
        u.register(tcp);

    }

      public static void main(String argv[]) {
        Server main = new Server("10.1.1.1");
        main.run();
    }
    
    public void run() {

        Thread t = new Thread() {
            public void run() {

                try {
                    tcp.runTcp();
                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }

            }
        };

        t.start();
        while (true) {
            u.receive();
        }

    }

  
}
