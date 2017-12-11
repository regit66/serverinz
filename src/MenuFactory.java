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

    private HelpMenu helpMenu;

}
