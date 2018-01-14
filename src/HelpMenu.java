import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelpMenu extends JMenu {

    /**
     * Default constructor
     *
     * @param mainFrame where this menu is atatched
     */

    public HelpMenu(GuiApp mainFrame) {
        super("Help");
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */
    private void createMenu() {


        this.add(userGuideItem = new JMenuItem("Guide"));
        userGuideItem.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("./manual/guide.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
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