import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GuiApp extends JFrame {

    private JPanel panelMain;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton ustawButton;
    private JButton wybierzButton;
    private JTextField textField1;
    private JPanel vid1;
    private JPanel vid2;
    private JPanel vid3;
    private JPanel vid4;
    private JButton button6;
    private JButton a270Button;
    private JButton a90Button;
    private JButton a180Button;
    private JButton a360Button;
    private JPanel IconPanel;
    private JLabel Connection;
    private PlayerPanel v1, v2, v3, v4;
    private Client c;
   private List<Client> clientList;
    ArrayList<JLabel> listofLabels = new ArrayList<JLabel>(100);


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

        ////v1.play("http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");
     //  v2.play("http://vjs.zencdn.net/v/oceans.mp4");
     //  v3.play("http://vjs.zencdn.net/v/oceans.mp4");
      //  v4.play("http://vjs.zencdn.net/v/oceans.mp4");
    }

    /**
     * @return the menu factory instance
     */
    private MenuFactory getMenuFactory()
    {
        if (this.menuFactory == null)
        {
            menuFactory = new MenuFactory();
        }
        return this.menuFactory;
    }

    public JButton getWybierzButton() {
        return wybierzButton;
    }

    public void setWybierzButton(JButton wybierzButton) {
        this.wybierzButton = wybierzButton;
    }

    public JLabel getConnection() {

        return Connection;
    }

    /**
     * Creates menu bar
     */
    private void createMenuBar()
    {

       final JMenuBar menuBar = new JMenuBar();
       final MenuFactory menuFactory = getMenuFactory();
       menuBar.add(menuFactory.getHelpMenu(this));

       setJMenuBar(menuBar);

    }

    public void changeconnectionIcon ( JLabel icon, JRadioButton jRadioButton,Client client, List<Client> clientList)
    {

if (client.isConnected()) {
    ImageIcon imgThisImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/connected-256.png")));
    IconPanel.add(jRadioButton);
    IconPanel.add(icon);


    jRadioButton.setText("Robot: "+ client.getClientNr());
    icon.setIcon(imgThisImg);
}
else
{
    //icon.setIcon(imgThisImg);
    jRadioButton.setVisible(false);
    icon.setVisible(false);
    IconPanel.repaint();


    if (clientList.size()==0)
    {
        //ImageIcon imgThisImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/disconnected-256.png")));
        getConnection().setVisible(true);
        getWybierzButton().setVisible(false);
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
}
