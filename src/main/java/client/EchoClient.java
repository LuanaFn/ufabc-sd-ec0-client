package main.java.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import main.java.app.ChamaServer;

public class EchoClient {
	private DatagramSocket socket;
	private InetAddress address;
	//Logger log = Logger.getLogger(EchoClient.class);

	private byte[] buf;

	public EchoClient() throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
	}

	public String sendEcho(String msg) {
		buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		String received = "";

		try {
			socket.send(packet);
			System.out.println("Mensagem enviada ao server: " + msg);

			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			received = new String(packet.getData(), 0, packet.getLength());

			System.out.println("Mensagem recebida do server: " + received);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return received;
	}

	public void close() {
		socket.close();
	}
}
