import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(1000);

        System.out.println("Server is listening on port " + port);

        while (true) {
            try {
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("Hello from the server");
                toClient.close();;
                fromClient.close();

                acceptedConnection.close();
                // Optionally: Read something from the client
                // String clientMessage = fromClient.readLine();
                // System.out.println("Client says: " + clientMessage);

                // Close after communication (optional)
            } catch (SocketTimeoutException ex) {
                // Timeout after 1 second if no client connects, continue listening
                System.out.println("Socket timed out, waiting again...");
            } catch (IOException ex) {
                ex.printStackTrace();
                break; // Exit loop on IOException
            }
        }
        // socket.close(); // You can optionally close server socket outside loop if needed
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
