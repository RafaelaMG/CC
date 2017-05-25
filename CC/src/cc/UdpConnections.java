/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Rafaela
 */
public class UdpConnections extends Thread {

    private DataTable servers;
    private int resetAll;

    public UdpConnections(DataTable serv) {
        servers = serv;
        resetAll = 0;
    }

    //Asks for update
    public void connection(String connection) {
        try {
            try (DatagramSocket dsocket = new DatagramSocket()) {
                String str = "Update:";
                
                InetAddress ip = InetAddress.getByName(connection);
                
                DatagramPacket dpacket = new DatagramPacket(str.getBytes(), str.length(), ip, 5556);
                dsocket.send(dpacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   public class ThreadServ extends Thread implements Runnable {
    private int resetAll=0; 
    private UdpConnections u;
    private DataTable dt;
    
    @Override
        public void run() {
          
            while (true) {
                try {
                    resetAll++;
                    //For each to get server list from DataTable and update time
                 servers.getServers().stream().forEach((s) -> {
                        long time = System.nanoTime();
                        connection(s);
                        servers.updateTime(s, time);
                    });
                    if (resetAll <= 8) {
                    } else {
                        dt.clean();
                    }
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }

            }
        }
}


    public void run() {

        Thread sv = new Thread(new ThreadServ());

        sv.start();

        while (true) {
            try {
                try (DatagramSocket dsocket = new DatagramSocket(5555)) {
                    byte[] buf = new byte[2048];
                    DatagramPacket dpacket = new DatagramPacket(buf, 2048);
                    dsocket.receive(dpacket);
                    String str = new String(dpacket.getData(), 0, dpacket.getLength());
                    String[] split = str.split(":");
                    switch (split[0]) {
                        case "Nothing":
                            String[] split2 = split[1].split("/");
                            System.out.println(split2[1]);
                            servers.addToServer(split2[1]);
                            break;
                        case "Update":
                            long b = System.nanoTime();
                            String[] split3 = split[1].split("/");
                            System.out.println(split3[1]);
                            System.out.println(split[2]);
                            long a = servers.getTime(split3[1]);
                            long rtt = b - a;
                            System.out.println("Round Trip Time: " + rtt);
                            servers.updateList(rtt, Integer.parseInt(split[2]), split3[1]);
                            break;
                        
                    }
                }
            } catch (IOException | NullPointerException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public DataTable getServersd() {
        return servers;
    }

    public void setServersd(DataTable servers) {
        this.servers = servers;
    }

    public int getResetAll() {
        return resetAll;
    }

    public void setResetAll(int resetAll) {
        this.resetAll = resetAll;
    }
    
    

}
