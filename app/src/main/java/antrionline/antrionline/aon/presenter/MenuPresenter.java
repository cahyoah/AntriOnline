package antrionline.antrionline.aon.presenter;

import java.util.ArrayList;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.Menu;
import antrionline.antrionline.aon.ui.home.menu.MenuView;

/**
 * Created by adhit on 28/01/2018.
 */

public class MenuPresenter {
    private MenuView menuView;

    public MenuPresenter(MenuView menuView){
        this.menuView = menuView;
    }

    public void showListMenu(){
        ArrayList<Menu> MenuArrayList = new ArrayList<>();
        MenuArrayList.add(new Menu(R.drawable.logomenu4, "Antri"));
        MenuArrayList.add(new Menu(R.drawable.logomenu1, "Daftar Antrianku"));
        MenuArrayList.add(new Menu(R.drawable.logomenu3, "Daftar Dokter"));
        MenuArrayList.add(new Menu(R.drawable.logomenu2, "Lokasi Pelayanan Kesehatan"));
        menuView.showData(MenuArrayList);

    }

}

