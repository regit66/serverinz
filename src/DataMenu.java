import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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


        this.add(userGuideItem= new JMenuItem("Ambiant ir sensors"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }

        });

        this.add(userGuideItem= new JMenuItem("Accel and Gyro sensors"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }


        });

        this.add(userGuideItem= new JMenuItem("Accel and Gyro sensors"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }


        });

        this.add(userGuideItem= new JMenuItem("Proximity ir sensors"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }


        });

        this.add(userGuideItem= new JMenuItem("Ultrasonic sensors"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }


        });

        this.add(userGuideItem= new JMenuItem("Baterry properties"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("test.csv");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            }


        });



    }
    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;

    private JMenuItem userGuideItem;
}