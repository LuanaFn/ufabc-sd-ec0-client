package main.java.app;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import main.java.client.EchoClient;

public class ChamaServer {
	// static Logger log = Logger.getLogger(ChamaServer.class);
	private static String prefixo = "!////MENSAGEM COMECA AQUI////!";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final EchoClient client = new EchoClient();
			String msg1 = "hello server";

			// testa o server
			String echo = client.sendEcho("1" + prefixo + msg1);
			if (msg1.equals(echo)) {
				echo = client.sendEcho("2" + prefixo + "server recebeu o primeiro hello");

				System.out.println("Primeiro teste ok. ");
			}

			if (msg1.equals(echo)) {
				System.out.println("Falhou no segundo teste.");
			} else {
				System.out.println("Passou no segundo teste.");
			}

			echo = "";
			// testando como funciona o envio sequencial
			for (int i = 3; i < 15; i++) {
				
				//cria um numero atomico no valor de i
				final AtomicInteger ordem = new AtomicInteger(i);
				
				// atraves de threads para enviar ao mesmo tempo
				new Thread(new Runnable() {
					public void run() {
						client.sendEcho(ordem + prefixo + "mensagem "+ordem);
					}
				}).start();
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
