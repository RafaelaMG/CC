/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Rafaela
 */
public class TcpServer {

    private static int con;

    public TcpServer() {
        con = 0;

    }

    public void runTcp() throws Exception {

        con = 0;
        ServerSocket server = new ServerSocket(6666);
        System.out.println("Server created");

        while (true) {

            Socket connection = server.accept();
            con++;

            Thread t = new Thread() {
                public void run() {

                    process(connection);

                }
            };

            t.start();
        }
    }

    private static void process(Socket connection) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String fromClient = in.readLine();
            System.out.println("Received from client: " + fromClient);
            
            
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String fromServer = fromClient.toUpperCase() + '\n';
            out.writeBytes(fromServer);
            con--;
            connection.shutdownOutput();
            connection.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    
     public void connection(String server, Socket con) {
        try {
            try (Socket s = new Socket(server, 6666)) {
                DataOutputStream outToClient = new DataOutputStream(con.getOutputStream());
                DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());
                
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(con.getInputStream()));
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
                
                System.out.println("\nServer chosen:" + server);
                
                String client = inFromClient.readLine();
                
                outToServer.writeBytes(client + '\n');
                
                String serverstr = inFromServer.readLine();
                
                outToClient.writeBytes(serverstr);
                
                System.out.println("Received from client: " + client);
                System.out.println("Returned to Client: " + serverstr);
                s.shutdownInput();
                s.shutdownOutput();
                con.shutdownInput();
                con.shutdownOutput();
            }
            con.close();
            System.out.println("Sent to:" + server +'\n');
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
    }
    
    
    
    public int getConnects() {
        return this.con;
    }

}
