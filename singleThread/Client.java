import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        toSocket.println("Hello from client");

        String line = fromSocket.readLine();
        System.out.println("Response from server: " + line);
        
        toSocket.close();
        fromSocket.close();
        socket.close(); // Always close the socket
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run(); // <-- You forgot to call run()
        } catch (Exception ex) {
            ex.printStackTrace(); // Print the exception if something goes wrong
        }
    }
}
