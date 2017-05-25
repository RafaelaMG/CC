package cc;

import java.io.*;
import java.net.*;

public class ReverseProxy {

	
	private final DataTable servidores;
	private final TcpServer TCP;
	private final UdpConnections UDP;
	
	public ReverseProxy(){
		this.servidores = new DataTable();
		this.TCP = new TcpServer();
		this.UDP = new UdpConnections(servidores);
		this.UDP.start();
		
	}
	
        
        private void process(Socket connectionSocket){
	
		String IPx = servidores.selection();
		TCP.connection(IPx , connectionSocket);

}
        
	 public static void main(String argv[]) throws Exception {
		 
		  ServerSocket server = new ServerSocket(80);
		  ReverseProxy rp = new ReverseProxy();
		
		  while (true) {  
			  
			  Socket connectionSocket = server.accept();
	  	  
	  		   
	  		   Thread t = new Thread() {
			    public void run() {
			    	 
                                        // chooses new client
			    		rp.process(connectionSocket);
			  		
			  		  
			  		  
			    }  
			};

			t.start();
			
			
	 }
		 }
	 

}
