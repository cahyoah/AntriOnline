package antrionline.antrionline.aon.data.model;

/**
 * Created by adhit on 29/01/2018.
 */

public class Menu {
    private int imgMenu;
    private String nameMenu;

    public Menu(int imgMenu, String nameMenu) {
        this.imgMenu = imgMenu;
        this.nameMenu = nameMenu;
    }

    public int getImageMenu() {
        return imgMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }
}
