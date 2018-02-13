package gui.menu;

import gui.GuiApp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

                    String inputPdf = "manual/guide.pdf";
                    try (InputStream is = HelpMenu.class.getClassLoader().getResourceAsStream(inputPdf)) {
                        Path tempOutput = Files.createTempFile("TempManual", ".pdf");
                        tempOutput.toFile().deleteOnExit();
                        Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                        Desktop.getDesktop().open(tempOutput.toFile());
                    }

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