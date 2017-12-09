// TCPServer.java
// A server program implementing TCP socket


import java.net.*;
import java.io.*;


public class TCPServer {
    public static void main(String args[]) {
        new GuiApp();

        try {
            int serverPort = 20000;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            System.out.println("server start listening... ... ...");

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Client connect" + clientSocket.getLocalAddress());
                System.out.println("Connection received from " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort());
                Connection c = new Connection(clientSocket);

            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }


    }
}

class Connection extends Thread {
    InputStream input;
    OutputStream output;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void readFromClient() {
        int red = -1;
        byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
        byte[] redData;
        StringBuilder clientData = new StringBuilder();
        String redDataText;
        try {
            red = clientSocket.getInputStream().read(buffer);
                redData = new byte[red];
                System.arraycopy(buffer, 0, redData, 0, red);
                redDataText = new String(redData,"UTF-8"); // assumption that client sends data UTF-8 encoded
                System.out.println("client :" + redDataText);
                clientData.append(redDataText);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data From Client :" + clientData.toString());


        System.out.println("odczytano");

    }


    public void sendToOneClient(String rozkaz) {


        // Sending the response back to the client.
        // Note: Ideally you want all these in a try/catch/finally block
        OutputStream os = null;
        try {
            os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(rozkaz);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("wyslano");
    }

    public void run() {

      //  try { // an echo server
            //  String data = input.readUTF();


                    sendToOneClient("t");
            readFromClient();
            sendToOneClient("65");
            readFromClient();

          //  System.out.println("waiting for file");

/*c
            ////////////////////
            output = new FileOutputStream("test.txt");
            byte[] bytes = new byte[16 * 1024];

            int count;
            while ((count = input.read(bytes)) > 0) {
                output.write(bytes, 0, count);
            }
            //  output.writeUTF(st);
            output.close();
            input.close();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                System.out.println("Send file completed");
                clientSocket.close();
            } catch (IOException e) {close failed}
  }

*/
            if (clientSocket.isClosed()) {
                System.out.println("Client disconnect");
            }
        }
    }

