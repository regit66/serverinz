import javax.swing.*;
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
    private JPanel vid1;
    private JPanel vid2;
    private JPanel vid3;
    private JPanel vid4;
    private JButton getDataButton;
    private JButton a270Button;
    private JButton a90Button;
    private JButton a180Button;
    private JButton a360Button;
    private JPanel IconPanel;
    private JLabel Connection;
    private JLabel robotControlLabel;
    private JButton openDataButton;
    private JSlider speedControler;
    private PlayerPanel v1, v2, v3, v4;
    private MenuFactory menuFactory;

    public GuiApp() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        setInitialSize();
        decorateFrame();
        createMenuBar();
        setContentPane(panelMain);
        IconPanel.setLayout(new BoxLayout(IconPanel, BoxLayout.PAGE_AXIS));
        vid1.setLayout(new BorderLayout());
        vid1.add(v1 = new PlayerPanel());
        vid2.setLayout(new BorderLayout());
        vid2.add(v2 = new PlayerPanel());
        vid3.setLayout(new BorderLayout());
        vid3.add(v3 = new PlayerPanel());
        vid4.setLayout(new BorderLayout());
        vid4.add(v4 = new PlayerPanel());
        //pack();

        setVisible(true);

        // v1.play("http://192.168.1.28:8080/?action=stream");
        // v2.play("http://192.168.1.22:8080/?action=stream");
        //  v2.play("http://vjs.zencdn.net/v/oceans.mp4");
        //  v3.play("http://vjs.zencdn.net/v/oceans.mp4");
        //  v4.play("http://vjs.zencdn.net/v/oceans.mp4");

    }

    /**
     * Creates menu bar
     */
    private void createMenuBar() {

        final JMenuBar menuBar = new JMenuBar();
        final MenuFactory menuFactory = getMenuFactory();
        menuBar.add(menuFactory.getHelpMenu(this));

        setJMenuBar(menuBar);

    }
    /**
     * Creates connected robot gui
     */
    public void changeConnectionPanel(JLabel icon, JRadioButton jRadioButton, Client client, List<Client> clientList) {

        if (client.isConnected()) {
            ImageIcon imgThisImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/connected-256.png")));
            IconPanel.add(jRadioButton);
            IconPanel.add(icon);
            jRadioButton.setText("Robot: " + client.getIp());
            icon.setIcon(imgThisImg);
            //client.getIp();
           // v1.play("http://"+client.getName()+":8080/?action=stream");
            v1.play("http://192.168.1.28:8080/?action=stream");
            v2.play("http://192.168.1.22:8080/?action=stream");

        } else {

            jRadioButton.setVisible(false);
            icon.setVisible(false);
            IconPanel.repaint();

            if (clientList.size() == 0) {

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

    public JButton getSetSpeed() {
        return setSpeed;
    }

    public JButton getGetDataButton() {
        return getDataButton;
    }

    public void setGetDataButton(JButton getDataButton) {
        this.getDataButton = getDataButton;
    }

    public int  getSpeed()
    {

return   speedControler.getValue();

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
