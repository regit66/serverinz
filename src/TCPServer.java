// TCPServer.java
// A server program implementing TCP socket

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {

    public static void main(String args[]) {
        List<Client> clientList = new ArrayList<Client>();
        GuiApp gui = new GuiApp();

        try {
            int serverPort = 20000;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server work ok. Server start listening... ... ...");

            while (true) {

                Socket clientSocket = listenSocket.accept();
                System.out.println("Connection received from " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort());
                Client client = new Client(clientSocket.getInetAddress().toString(), clientSocket.getPort(), clientSocket.getInetAddress().getHostName(), clientList.size(), true);
                clientList.add(client);
                new Connection(clientSocket, clientList, gui, client);

            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }

    }
}

