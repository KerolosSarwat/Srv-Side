import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int SERVER_PORT = 3456;


	//https://stackoverflow.com/questions/2004531/what-is-the-difference-between-socket-and-serversocket#:~:text=A%20socket%20is%20an%20endpoint%20for%20communication%20between%20two%20machines.&text=ServerSocket%20is%20again%20a%20Socket,an%20incoming%20connection%20etc...&text=ServerSocket%20is%20created%20to%20bind,a%20connect%20from%20a%20client.
	//https://codethat.files.wordpress.com/2010/01/scheme.png
	public Server() throws IOException {
		//server socket to hold a port in the OS for the App 
		ServerSocket server_socket = new ServerSocket(SERVER_PORT); //opening a new port 2020
		System.out.println("port 3456 is open");
		
		Socket socket = server_socket.accept(); //blocking method
		System.out.println("client : " + socket.getInetAddress() + " connected");
		
//		//IO Buffer
//		BufferedReader in_socket = new BufferedReader(
//				new InputStreamReader(socket.getInputStream())
//				);
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                
		
		// out_socket.println("Welcome to the the server\n");
        // out_socket.flush(); // Add this line to flush the output stream

		String clientMsg = "";
		while (true) {
		clientMsg = in.readUTF();
		System.out.println("Client msg : " + clientMsg);			
		}
		// 
		// socket.close();
		// System.out.println("socket closed");
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
