package app;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import client.EchoClient;

public class ChamaServer {
	static Logger log;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EchoClient client = new EchoClient();
			String msg1 = "hello server";
			
			// testa o server
			String echo = client.sendEcho(msg1);
	        if(msg1.equals(echo)){
		        echo = client.sendEcho("server recebeu o primeiro hello");
		        
		        System.out.println("Primeiro teste ok. ");
		    }
	        
	        if(msg1.equals(echo)){
	        	System.out.println("Falhou no segundo teste.");
	        }
	        else
	        {
	        	System.out.println("Passou no segundo teste.");
	        }
			
		} catch (Exception e) {
			
			log.logException(e, Level.SEVERE);
			
		}

	}

}
