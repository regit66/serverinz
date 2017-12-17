// TCPServer.java
// A server program implementing TCP socket


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TCPServer {

    public static void main(String args[]) {
        List<Client> clientList = new ArrayList<Client>();
        GuiApp gui = new GuiApp();

        try {
            int serverPort = 20000;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            System.out.println("server start listening... ... ...");

            while (true) {
                Socket clientSocket = listenSocket.accept();
                //System.out.println("Client connect" + clientSocket.getLocalAddress());
                System.out.println("Connection received from " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort());

                Client cc = new Client(clientSocket.getInetAddress().toString(), clientSocket.getPort(), clientSocket.getInetAddress().getHostName(), clientList.size(), true);
                clientList.add(cc);
                Connection c = new Connection(clientSocket, clientList, gui, cc);

            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }


    }
}

class Connection extends Thread {

    List<Client> clientList;
    GuiApp gui;
    InputStream input;
    OutputStream output;
    Socket clientSocket;
    int port;
    Client cc;
    JLabel jlabel = new JLabel();
    JRadioButton jRadioButton = new JRadioButton();
    String rozkaz = null;

    public Connection(Socket aClientSocket, List<Client> clientList, GuiApp gui, Client cc) {
        this.clientList = clientList;
        this.gui = gui;
        this.cc = cc;
        gui.getRobotControlLabel().setVisible(true);
        gui.getConnection().setVisible(false);

        gui.changeconnectionIcon(jlabel, jRadioButton, cc, clientList);
        String listString = "";

        for (Client client : clientList) {

            listString += client.toString() + "\t";
        }
        System.out.println("Polaczeni : \n" + listString);

        try {
            clientSocket = aClientSocket;
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());

        }
    }

    public void readFromClient(Client cc) {

        int red = -1;
        byte[] buffer = new byte[5 * 1024]; // a read buffer of 5KiB
        byte[] redData;
        StringBuilder clientData = new StringBuilder();
        String redDataText;
        try {
            red = clientSocket.getInputStream().read(buffer);


            if (red == -1) {
                cc.setConnected(false);
                return;
            }
            redData = new byte[red];


            //wyjatek
            System.arraycopy(buffer, 0, redData, 0, red);
            redDataText = new String(redData, "UTF-8"); // assumption that client sends data UTF-8 encoded
            System.out.println("Client " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort() +": " + redDataText);
            clientData.append(redDataText);

        } catch (IOException | NegativeArraySizeException e) {
            System.out.println("error");


            removeClient();

            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        // System.out.println("odczytano, Data From Client: " + clientData.toString());

        if (clientData.toString().equals("stop")) {
            cc.setConnected(false);
        }

    }

    public synchronized void removeClient() {
        cc.setConnected(false);
        //  System.out.println(cc.isConnected());
///////////

        Iterator<Client> i = clientList.iterator();
        while (i.hasNext()) {
            Client s = i.next(); // must be called before you can call i.remove()
            // Do something
            if (s.getPort() == port)
                i.remove();
        }

        ///////////////    if (client.getPort() == port)

        //System.out.print("dadsadasdsadsa"+ clientList.size());
        gui.changeconnectionIcon(jlabel, jRadioButton, cc, clientList);
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

        System.out.println("Server to client " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort() + ": " + rozkaz);
    }

    public String getRozkaz() {
        return rozkaz;
    }

    public void setRozkaz(String rozkaz) {
        this.rozkaz = rozkaz;
    }


    public void waitfor() {
        rozkaz = null;
        gui.getButtonUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRozkaz("up");

            }
        });
        gui.getButtonDown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRozkaz("down");

            }
        });
    }

    public void run() {

        port = clientSocket.getPort();
        while (cc.isConnected()) {




            rozkaz = null;
            while (rozkaz == null) {

                waitfor();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!jRadioButton.isSelected() == true) {
                System.out.println("wybierz robota");
            }
            sendToOneClient(rozkaz);
            readFromClient(cc);



            //  try { // an echo server
            //  String data = input.readUTF();


            // thread sleep ...
            // break condition , close sockets and the like ...

            // sendToOneClient("65");
            // readFromClient();

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

            /// if (clientSocket.isClosed() || !clientSocket.isConnected()) {


            // }


        }
        System.out.println("Client " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort() + " disconnect");
        removeClient();
        stop();


        try {
            clientSocket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}