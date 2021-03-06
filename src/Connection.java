import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class Connection extends Thread {

    private List<Client> clientList;
    private GuiApp gui;
    private InputStream input;
    private OutputStream output;
    private Socket clientSocket;
    private int port;
    private Client client;
    private JLabel clientInfoLabel = new JLabel();
    private PlayerPanel playerPanel = new PlayerPanel();
    private JRadioButton jRadioButton = new JRadioButton();
    private String command = null;

    public Connection(Socket aClientSocket, List<Client> clientList, GuiApp gui, Client cc) {
        this.clientList = clientList;
        this.gui = gui;
        this.client = cc;
        gui.getRobotControlLabel().setVisible(true);
        gui.getConnection().setVisible(false);
        gui.changeConnectionPanel(clientInfoLabel, jRadioButton, cc, clientList, playerPanel);
        gui.repaint();

        //debug
        String listString = "";
        //
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

    /**
     * Get data from client
     */
    private void readFromClient(Client cc) {

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
            System.out.println("Client " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort() + ": " + redDataText);
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

    /**
     * Disconnect client when something get wrong
     */
    private synchronized void removeClient() {
        client.setConnected(false);
        Iterator<Client> i = clientList.iterator();
        while (i.hasNext()) {
            Client s = i.next(); // must be called before you can call i.remove()
            // Do something
            if (s.getPort() == port)
                i.remove();
        }

        gui.changeConnectionPanel(clientInfoLabel, jRadioButton, client, clientList, playerPanel);
    }

    /**
     * Send data to client
     */
    private void sendToOneClient(String rozkaz) {

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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    /**
     * Wait for command to send
     */
    private void waitForArgument() {
        command = null;
        gui.getButtonUp().addActionListener(e -> setCommand("up"));
        gui.getButtonDown().addActionListener(e -> setCommand("down"));
        gui.getLeftButton().addActionListener(e -> setCommand("left"));
        gui.getRightButton().addActionListener(e -> setCommand("right"));
        gui.getSetSpeed().addActionListener(e -> setCommand("speed"));
        gui.getGetDataButton().addActionListener(e -> setCommand("file"));
    }

    /**
     * Check client is selected
     */
    private void checkIsSelected() {
        while (!jRadioButton.isSelected()) {

            System.out.println("Wait for selected");
        }

    }

    /**
     * Wait for command to send
     */
    private void waitForCommand() {
        command = null;
        while (command == null) {


            waitForArgument();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {

        port = clientSocket.getPort();

        while (client.isConnected()) {


            waitForCommand();
            checkIsSelected();
            sendToOneClient(command);

            if (command.equals("speed")) {
                setCommand(Integer.toString(gui.getSpeed()));
                readFromClient(client);
                sendToOneClient(command);

            }

            if (command.equals("file")) {
                getFile();
            }

            readFromClient(client);
            playerPanel.scanQR();

        }

        disconnect();
    }

    /**
     * Disconnect client
     */
    private void disconnect() {
        System.out.println("Client " + clientSocket.getInetAddress().getHostName() + " : " + clientSocket.getPort() + " disconnect");
        removeClient();
        stop();

        try {
            clientSocket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * Get file from client
     */
    private void getFile() {
        System.out.println("waiting for file");

        try {
            output = new FileOutputStream("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[16 * 1024];

        int count;
        try {
            if ((count = input.read(bytes)) > 0) {
                output.write(bytes, 0, count);
                System.out.println("Send file completed");
                //   input.close();
                // output.close();
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }


    }
}