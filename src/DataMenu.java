import javax.swing.*;

public class DataMenu extends JMenu {

    /**
     * Default constructor
     *
     * @param mainFrame where this menu is atatched
     */

    public DataMenu(GuiApp mainFrame) {
        super("Sensors Data");
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    public JMenuItem getUserGuideItem5() {
        return userGuideItem5;
    }

    public void setUserGuideItem5(JMenuItem userGuideItem5) {
        this.userGuideItem5 = userGuideItem5;
    }

    public JMenuItem getUserGuideItem6() {
        return userGuideItem6;
    }

    public void setUserGuideItem6(JMenuItem userGuideItem6) {
        this.userGuideItem6 = userGuideItem6;
    }

    /**
     * Initializes menu
     */
    private void createMenu() {
        this.add(userGuideItem1= new JMenuItem("Data fom all sensors"));
        this.add(userGuideItem2= new JMenuItem("Ambiant ir sensors"));
        this.add(userGuideItem3= new JMenuItem("Accel and Gyro sensors"));
        this.add(userGuideItem4= new JMenuItem("Proximity ir sensors"));
        this.add(userGuideItem5= new JMenuItem("Ultrasonic sensors"));
        this.add(userGuideItem6= new JMenuItem("Baterry properties"));
        this.add(userGuideItem7= new JMenuItem("Motors properties"));




    }
    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;

    private JMenuItem userGuideItem1;

    public JMenuItem getUserGuideItem1() {
        return userGuideItem1;
    }

    public JMenuItem getUserGuideItem7() {
        return userGuideItem7;
    }

    private JMenuItem userGuideItem7;
    private JMenuItem userGuideItem5;
    private JMenuItem userGuideItem2;
    private JMenuItem userGuideItem3;
    private JMenuItem userGuideItem4;
    private JMenuItem userGuideItem6;

    public JMenuItem getUserGuideItem2() {
        return userGuideItem2;
    }

    public void setUserGuideItem2(JMenuItem userGuideItem2) {
        this.userGuideItem2 = userGuideItem2;
    }

    public JMenuItem getUserGuideItem3() {
        return userGuideItem3;
    }

    public void setUserGuideItem3(JMenuItem userGuideItem3) {
        this.userGuideItem3 = userGuideItem3;
    }

    public JMenuItem getUserGuideItem4() {
        return userGuideItem4;
    }

    public void setUserGuideItem4(JMenuItem userGuideItem4) {
        this.userGuideItem4 = userGuideItem4;
    }
}