import javax.swing.*;
import java.awt.*;
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
    private String batteryLevel;
    private String colorsName[] = {"off", "red", "blue", "yellow", "pink", "purple", "orange", "green", "white"};

    private Color colors[] = {new Color(Color.TRANSLUCENT,Color.TRANSLUCENT,Color.TRANSLUCENT,Color.TRANSLUCENT ), Color.red, Color.cyan, Color.yellow, Color.pink, Color.magenta, Color.orange, Color.green, Color.white};


    public Connection(Socket aClientSocket, List<Client> clientList, GuiApp gui, Client cc) {
        this.clientList = clientList;
        this.gui = gui;
        this.client = cc;
        gui.getRobotControlLabel().setVisible(true);
        gui.getConnection().setVisible(false);
        gui.changeConnectionPanel(clientInfoLabel, jRadioButton, cc, clientList, playerPanel);
        gui.repaint();
        ;

        //Initialize the values of the array

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
            batteryLevel = redDataText;

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

        gui.getStopButton().addActionListener(e -> setCommand("stop"));
        gui.getButtonUp().addActionListener(e -> setCommand("up"));
        gui.getButtonDown().addActionListener(e -> setCommand("down"));
        gui.getLeftButton().addActionListener(e -> setCommand("left"));
        gui.getRightButton().addActionListener(e -> setCommand("right"));
        gui.getSpeedController().addChangeListener(e -> {
            gui.getSpeedInfoLabel().setText(gui.getSpeedController().getValue() + "%");
            setCommand("speed");
        });
        gui.getLoadScriptButton().addActionListener(e -> setCommand("loadscript"));
        gui.getRunScriptButton().addActionListener(e -> setCommand("runscript"));
        gui.getDiodeSlider().addChangeListener(e -> {
            gui.getDiodeColorLabel().setText(colorsName[gui.getDiodeSlider().getValue()]);
            setCommand("diode");
        });
        gui.getMenuFactory().getDataMenu().getUserGuideItem1().addActionListener(e -> setCommand("alldata"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem2().addActionListener(e -> setCommand("ambiant"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem3().addActionListener(e -> setCommand("acel"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem4().addActionListener(e -> setCommand("proximity"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem5().addActionListener(e -> setCommand("ultrasonic"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem6().addActionListener(e -> setCommand("battery"));
        gui.getMenuFactory().getDataMenu().getUserGuideItem7().addActionListener(e -> setCommand("line"));

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

            if (command.equals("diode")) {
                readFromClient(client);

                sendToOneClient(gui.getDiodeSelectorSpiner().getValue().toString());
                readFromClient(client);
                sendToOneClient(colorsName[gui.getDiodeSlider().getValue()]);

            }

            if (command.equals("loadscript")) {

                ///send file
                FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                String file = dialog.getDirectory()+dialog.getFile();
                System.out.println(dialog.getDirectory()+file + " chosen.");
                if (dialog.getFile()==null)
                {
                    file="script.sh";
                }
                try {

                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[256];
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        output.write(buffer, 0, len);
                    }

                    fis.close();
                    //fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("alldata")) {
                getFile(clientSocket.getInetAddress().getHostName());
            }

            readFromClient(client);
            clientInfoLabel.setText("battery: " + batteryLevel + "% " + "  color : " + colorsName[gui.getDiodeSlider().getValue()]);
            clientInfoLabel.setOpaque(true);
            clientInfoLabel.setBackground(colors[gui.getDiodeSlider().getValue()]);
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
    private void getFile(String ClientIP) {
        System.out.println("waiting for file");
        File file = new File(ClientIP + "sensors.csv");
        try {
            output = new FileOutputStream(file);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File is used");
            return;
        }
        byte[] bytes = new byte[16 * 1024];
        int count;
        try {
            if ((count = input.read(bytes)) > 0) {
                output.write(bytes, 0, count);

                System.out.println("read file completed");
                //   input.close();

                output.close();
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }


    }
}