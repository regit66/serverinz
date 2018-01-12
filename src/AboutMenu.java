import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutMenu extends JMenu {

    /**
     * Default constructor
     *
     * @param mainFrame where this menu is atatched
     */

    public AboutMenu(GuiApp mainFrame) {
        super("About");
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */
    private void createMenu() {


        this.add(userGuideItem= new JMenuItem("Informations"));
        userGuideItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShortcutDialog dialog = new ShortcutDialog(mainFrame);
                dialog.setVisible(true);
            }

        });




    }
    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;

    private JMenuItem userGuideItem;
}