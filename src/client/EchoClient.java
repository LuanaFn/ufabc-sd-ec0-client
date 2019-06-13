package client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;


public class EchoClient {
	private DatagramSocket socket;
    private InetAddress address;
    Logger log;
 
    private byte[] buf;
 
    public EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }
 
    public String sendEcho(String msg) {
        buf = msg.getBytes();
        DatagramPacket packet 
          = new DatagramPacket(buf, buf.length, address, 4445);
        String received = "";
        
        try{
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        received = new String(
          packet.getData(), 0, packet.getLength());
        }
        catch(Exception ex){
        	log.logException(ex, Level.SEVERE);
        }
        return received;
    }
 
    public void close() {
        socket.close();
    }
}
