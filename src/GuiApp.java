import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class GuiApp extends JFrame {

    private JPanel panelMain;
    private JButton rightButton;
    private JButton stopButton;
    private JButton buttonUp;
    private JButton leftButton;
    private JButton buttonDown;
    private JButton setSpeed;
    private JPanel videoPanel;
    private JButton getDataButton;
    private JPanel IconPanel;
    private JLabel Connection;
    private JLabel robotControlLabel;
    private JButton openDataButton;
    private JSlider speedController;
    private JLabel stream;
    private MenuFactory menuFactory;


    public GuiApp() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setInitialSize();
        decorateFrame();
        createMenuBar();
        setContentPane(panelMain);
        IconPanel.setLayout(new BoxLayout(IconPanel, BoxLayout.PAGE_AXIS));
        //pack();
        setVisible(true);

    }

    /**
     * Creates menu bar
     */
    private void createMenuBar() {

        final JMenuBar menuBar = new JMenuBar();
        final MenuFactory menuFactory = getMenuFactory();
        menuBar.add(menuFactory.getHelpMenu(this));
        menuBar.add(menuFactory.getDataMenu(this));
        menuBar.add(menuFactory.getAboutMenu(this));
        setJMenuBar(menuBar);

    }

    /**
     * Set property Layout for video panel
     */
    private void setVideoPanelLayout(List<Client> clientList)
    {
        if (clientList.size()<=2)
        {
            GridLayout experimentLayout = new GridLayout(0,1);
            videoPanel.setLayout(experimentLayout);
        }
        else
        {
            GridLayout experimentLayout = new GridLayout(0,2);
            videoPanel.setLayout(experimentLayout);
        }
    }

    /**
     * Creates connected robot gui
     */
    public void changeConnectionPanel(JLabel icon, JRadioButton jRadioButton, Client client, List<Client> clientList,PlayerPanel playerPanel) {

        if (client.isConnected()) {

            ImageIcon imgThisImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/connected-256.png")));
            IconPanel.add(jRadioButton);
            IconPanel.add(icon);
            jRadioButton.setText("Robot: " + client.getIp());
            jRadioButton.setSelected(true);
            icon.setIcon(imgThisImg);
            setVideoPanelLayout(clientList);

            videoPanel.add(playerPanel);
            TitledBorder border = new TitledBorder(client.getIp());
            playerPanel.setBorder(border);
            videoPanel.remove(stream);
            // it s work with robot
            playerPanel.play("http://"+client.getName()+":8080/?action=stream");
            //for test
            //playerPanel.play("http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");



        } else {
            setVideoPanelLayout(clientList);
            playerPanel.stop();
            videoPanel.remove(playerPanel);
            jRadioButton.setVisible(false);
            icon.setVisible(false);
            IconPanel.repaint();
            videoPanel.repaint();

            if (clientList.size() == 0) {

                videoPanel.add(stream);
                getConnection().setVisible(true);
                robotControlLabel.setVisible(false);
                IconPanel.repaint();

            }

        }

    }

    /**
     * Sets initial size on startup
     */
    private void setInitialSize() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenWidth = (int) screenSize.getWidth();
        final int screenHeight = (int) screenSize.getHeight();
        setBounds(screenWidth / 16, screenHeight / 16, screenWidth * 7 / 8,
                screenHeight * 7 / 8);
        setLocation(0, 0);
        // For screenshots only -> setBounds(50, 50, 850, 650);
    }

    /**
     * Set title and icon
     */
    private void decorateFrame() {
        setTitle("Khepera IV control panel");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/logo.png")));

    }

    /**
     * @return the menu factory instance
     */
    private MenuFactory getMenuFactory() {
        if (this.menuFactory == null) {
            menuFactory = new MenuFactory();
        }
        return this.menuFactory;
    }

    public JLabel getConnection() {

        return Connection;
    }

    public JButton getSetSpeed() {
        return setSpeed;
    }

    public JButton getGetDataButton() {
        return getDataButton;
    }

    public int  getSpeed()
    {

        return speedController.getValue();

    }

    public JButton getButtonUp() {
        return buttonUp;
    }

    public void setButtonUp(JButton buttonUp) {
        this.buttonUp = buttonUp;
    }

    public JButton getButtonDown() {
        return buttonDown;
    }

    public void setButtonDown(JButton buttonDown) {
        this.buttonDown = buttonDown;
    }

    public JButton getRightButton() {return rightButton; }

    public void setRightButton(JButton rightButton) {
        this.rightButton = rightButton;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(JButton leftButton) {
        this.leftButton = leftButton;
    }

    public JLabel getRobotControlLabel() {
        return robotControlLabel;
    }

    public void setRobotControlLabel(JLabel robotControlLabel) {
        this.robotControlLabel = robotControlLabel;
    }

}
