import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JRadioButton a1RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a2RadioButton;
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
    private JLabel ConnectedIcon1;
    private JLabel ConnectedIcon2;
    private JLabel ConnectedIcon3;
    private JLabel ConnectedIcon4;
    private PlayerPanel v1, v2, v3, v4;
    ArrayList<JLabel> listofLabels = new ArrayList<JLabel>(100);

    private PlayerPanel pole, player2;
    private MenuFactory menuFactory;

    public GuiApp() {
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        listofLabels.add(ConnectedIcon1);
        listofLabels.add(ConnectedIcon2);
        listofLabels.add(ConnectedIcon3);
        listofLabels.add(ConnectedIcon4);
        setInitialSize();
        decorateFrame();
        createMenuBar();
        setContentPane(panelMain);
        IconPanel.setLayout(new GridLayout());
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

    public void changeconnectionIcon ( JLabel icon)
    {


        ImageIcon imgThisImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/connected-256.png")));
        IconPanel.add(icon);
        icon.setIcon(imgThisImg);
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
