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

    /**
     * Initializes menu
     */
    private void createMenu() {

        this.add(userGuideItem2= new JMenuItem("Ambiant ir sensors"));
        this.add(userGuideItem3= new JMenuItem("Accel and Gyro sensors"));
        this.add(userGuideItem4= new JMenuItem("Proximity ir sensors"));
        this.add(userGuideItem5= new JMenuItem("Ultrasonic sensors"));
        this.add(userGuideItem6= new JMenuItem("Baterry properties"));




    }
    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;

    private JMenuItem userGuideItem5, userGuideItem2,userGuideItem3,userGuideItem4,userGuideItem6;

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