import javax.swing.*;
import java.awt.*;

public class ShortcutDialog extends JDialog
{
    //@ResourceBundleBean(key="dialog.title")
    private String dialogTitle;

    //@ResourceBundleBean(key="dialog.table.behavior")
    private String behaviorName;

    //@ResourceBundleBean(key="dialog.table.shortcut")
    private String shortcut ;

    //ResourceBundleBean(key="dialog.table.nodata")
    private String noData;

    private JPanel shortcutPanel;

    /**
     * Default constructor of ShortcutDialog
     * @param parent JFrame parent
     */
    public ShortcutDialog(JFrame parent)
    {
        super(parent);
        //ResourceBundleInjector.getInjector().inject(this);

        this.setTitle(dialogTitle);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buildShortcutPanel(), BorderLayout.CENTER);
        pack();
        setCenterLocation(parent);
    }

    private JPanel buildShortcutPanel()
    {
        if(shortcutPanel == null)
        {
            shortcutPanel = new JPanel(new BorderLayout());

            String[] columnNames = {behaviorName, shortcut};

            JTable table = new JTable();
            table.setEnabled(false);
            table.setCellSelectionEnabled(false);
            table.setShowGrid(true);
            table.setGridColor(new Color(220, 220, 220));
            table.setRowHeight(30);
            table.setIntercellSpacing(new Dimension(15, 0));
            table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));

            JScrollPane scrollPane = new JScrollPane(table);
            shortcutPanel.add(scrollPane, BorderLayout.CENTER);
        }
        return this.shortcutPanel;
    }



    private void setCenterLocation(JFrame parent)
    {
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }
}