import javax.swing.*;
import java.awt.*;

public class ShortcutDialog extends JDialog {

    /**
     * Default constructor of ShortcutDialog
     *
     * @param parent JFrame parent
     */
    public ShortcutDialog(JFrame parent) {
        super(parent);
        //ResourceBundleInjector.getInjector().inject(this);

        this.setTitle("About");

        this.setModal(true);
        this.setResizable(false);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        JLabel label1 = new JLabel("Autor: Marcin Dzioch\n \n" +
                "This program comes with ABSOLUTELY NO WARRANTY. This is free software, and you are welcome to redistribute it under certain conditions.\n");
        this.add(label1);
        // pack();
        setCenterLocation(parent);
    }


    private void setCenterLocation(JFrame parent) {
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }
}