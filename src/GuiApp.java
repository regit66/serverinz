import javax.swing.*;
import java.awt.*;

public class GuiApp extends JFrame  {

    private JPanel panelMain;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton ustawButton;
    private JButton wybierzButton;
    private JLabel speed;
    private JTextField textField1;
    private JRadioButton a1RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a2RadioButton;

    public  GuiApp()
    {
        setContentPane(panelMain);
        setInitialSize();
        decorateFrame();
        setVisible(true);
    }

    /**
     * Sets initial size on startup
     */
    private void setInitialSize()
    {
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
    private void decorateFrame()
    {
        setTitle("Khepera IV control panel");
        //    setIconImage(this.applicationIcon);
    }
}
