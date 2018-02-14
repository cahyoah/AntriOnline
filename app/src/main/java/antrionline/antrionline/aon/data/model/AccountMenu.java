package antrionline.antrionline.aon.data.model;

/**
 * Created by adhit on 31/01/2018.
 */

public class AccountMenu {

    private int imageMenu;
    private String nameMenu;

    public AccountMenu(int imageMenu, String nameMenu) {
        this.imageMenu = imageMenu;
        this.nameMenu = nameMenu;
    }

    public int getImageMenu() {
        return imageMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }
}
