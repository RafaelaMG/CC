/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Rafaela
 */
public class ThreadServidorTcp extends Thread{
    
    private Socket s;
    
    public ThreadServidorTcp(Socket s){
        this.s=s;
 
    }

   
    
    @Override
 public void run() {
     System.out.println("Espera pelo cliente.");
     
     try{
          PrintWriter o = new PrintWriter(s.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
            while(l!=null){
                System.out.println("Servidor à escuta.");
                System.out.println("Cliente diz: " + l);
                o.flush();
                l=i.readLine();
            }
            System.out.println("Ligação com cliente terminada.");
            s.close();
     } catch(IOException ex){
         System.out.println(ex.getMessage());
     }
 }   
}
