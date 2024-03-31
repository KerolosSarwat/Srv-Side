import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
public class Receiver {

    private static final int PORT_NUM = 4567; 
    public Receiver() throws SocketException,IOException{
        DatagramSocket socket = new DatagramSocket(PORT_NUM);
        
        System.out.println("Receiver is open at port " + PORT_NUM);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {            
            byte[] bufferFromClient = new byte[1500]; //MTU =1500 byte max transmition unit, limit of how much data can send in a single packet.
            DatagramPacket packetFromClient = new DatagramPacket(bufferFromClient,bufferFromClient.length);// ,1500 byte
            socket.receive(packetFromClient);
            String msgFromClient = new String(bufferFromClient).trim();//.trim();
            System.out.println("Received From Client: " + msgFromClient);
            //end of receiveing part
            
            
            // //send response:        
            // //finding out source from packet received
            // InetAddress senders_address = packetFromClient.getAddress();
            // int senders_port = packetFromClient.getPort();
            // //
            // // System.out.println("Enter your msg: ");
            // String msgToClient = scanner.nextLine(); //payload from server
            // byte[] bufferToClient = new byte[1500]; //MTU =1500 byte max transmition unit, limit of how much data can send in a single packet.
            // bufferToClient = msgToClient.getBytes(); //System.out.println("bytes: " + bufferToClient);
            // DatagramPacket packetToClient = new DatagramPacket(bufferToClient,bufferToClient.length,senders_address,senders_port);
            // socket.send(packetToClient);
            // System.out.println("msgToClient : " + msgToClient + " is sent");
        }
    }

    public static void main(String[] args) {
        try {
            new Receiver();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
