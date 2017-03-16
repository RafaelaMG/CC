/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Rafaela
 */
public class Thread_ClienteTCP implements Runnable {
     private BufferedReader inputServer;

    public Thread_ClienteTCP(BufferedReader in) {
        this.inputServer = in;
    }

    public void run() {
        try {
            //recebe mensagens do servidor e imprime
            String linha;
            while ((linha = inputServer.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
