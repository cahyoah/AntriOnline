package antrionline.antrionline.aon.ui.home.account;

import java.util.ArrayList;

import antrionline.antrionline.aon.data.model.AccountMenu;
import antrionline.antrionline.aon.data.model.User;

/**
 * Created by adhit on 31/01/2018.
 */

public interface AccountView {
    void showAccountProfile(User user);

    void showAccountMenu(ArrayList<AccountMenu> accountMenuArrayList);

    void onSuccessLogOut();
}
