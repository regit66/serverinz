public class MenuFactory {

    /**
     * @param editorFrame
     * @return help menu
     */
     public HelpMenu getHelpMenu(GuiApp editorFrame) {
        if (this.helpMenu == null) {
            this.helpMenu = new HelpMenu(editorFrame);
        }
        return this.helpMenu;
    }

    public DataMenu getDataMenu(GuiApp editorFrame) {
        if (this.dataMenu == null) {
            this.dataMenu = new DataMenu(editorFrame);
        }
        return this.dataMenu;
    }

    public AboutMenu getAboutMenu(GuiApp editorFrame) {
        if (this.aboutMenu == null) {
            this.aboutMenu = new AboutMenu(editorFrame);
        }
        return this.aboutMenu;
    }



    private HelpMenu helpMenu;
    private DataMenu dataMenu;
    private AboutMenu aboutMenu;


}
