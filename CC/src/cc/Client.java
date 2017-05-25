/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Rafaela
 */
public class Client{

   public static void main(String[] args) throws IOException, InterruptedException {      
             try ( 
            Socket s = new Socket("localhost", 80)) {
            
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String st = "input from client" + '\n';
            out.writeBytes(st);
            BufferedReader inFromServer= new BufferedReader(new InputStreamReader(s.getInputStream()));
            String rt = inFromServer.readLine();
            System.out.println(rt);
             
            
            s.shutdownInput();
            s.shutdownOutput();
            s.close();
        }
 } 
    
}
