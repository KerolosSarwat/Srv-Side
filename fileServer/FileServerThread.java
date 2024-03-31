
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kirollos
 */

class FileServerThread implements Runnable {

    private final Socket clientSocket;

    public FileServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            String fileName = in.readUTF();
            long fileSize = in.readLong();

            System.out.println("Receiving file: " + fileName + ", Size: " + fileSize);

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            
            out.writeUTF("OK");     //for acknolgement
            out.flush();
            
            
            FileOutputStream fileOutputStream = new FileOutputStream(fileName); //read file data

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();
            in.close();
            //clientSocket.close();*/

            System.out.println("File received successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
