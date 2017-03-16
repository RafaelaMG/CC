/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Rafaela
 */
public class ServidorTCP extends Thread {
     public static void main(String[] args) throws Exception
    {   
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Servidor criado!");
        ThreadServidorTcp servidorTCP;
        
        while(true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                servidorTCP = new ThreadServidorTcp(socket);
                servidorTCP.start();
            }
            catch(Exception e) { e.printStackTrace(); }
        }
    }
}
