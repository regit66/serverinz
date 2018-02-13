package gui;

import gui.menu.AboutMenu;
import gui.menu.DataMenu;
import gui.menu.HelpMenu;

public class MenuFactory {

    /**
     * @param app
     * @return help menu
     */
    public HelpMenu getHelpMenu(GuiApp app) {
        if (this.helpMenu == null) {
            this.helpMenu = new HelpMenu(app);
        }
        return this.helpMenu;
    }

    public DataMenu getDataMenu(GuiApp app) {
        if (this.dataMenu == null) {
            this.dataMenu = new DataMenu(app);
        }
        return this.dataMenu;
    }

    public DataMenu getDataMenu() {
        return dataMenu;
    }

    public AboutMenu getAboutMenu(GuiApp app) {
        if (this.aboutMenu == null) {
            this.aboutMenu = new AboutMenu(app);

        }
        return this.aboutMenu;
    }


    private HelpMenu helpMenu;
    private DataMenu dataMenu;
    private AboutMenu aboutMenu;


}
