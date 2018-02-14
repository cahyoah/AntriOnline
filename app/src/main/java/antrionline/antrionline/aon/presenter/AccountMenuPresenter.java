package antrionline.antrionline.aon.presenter;

import java.util.ArrayList;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.local.SessionLogin;
import antrionline.antrionline.aon.data.model.AccountMenu;
import antrionline.antrionline.aon.ui.home.account.AccountView;

/**
 * Created by adhit on 31/01/2018.
 */

public class AccountMenuPresenter {
    private AccountView accountView;
    public AccountMenuPresenter(AccountView accountView){
        this.accountView = accountView;
    }

    public void showAccountProfile(){
        accountView.showAccountProfile(SaveUserData.getInstance().getUser());
    }


    public void showAccountMenu(){
        ArrayList<AccountMenu> accountMenuArrayList = new ArrayList<>();
        accountMenuArrayList.add(new AccountMenu(R.drawable.ic_lock_outline_black_24dp, "Ganti Password"));
        accountMenuArrayList.add(new AccountMenu(R.drawable.ic_power_settings_new_black_24dp, "Keluar"));
        accountView.showAccountMenu(accountMenuArrayList);

    }

    public void logout() {
        SaveUserData.getInstance().removeUser();
        SaveUserData.getInstance().removeUserToken();
        SessionLogin.getInstance().setLoginStatus(false);
        accountView.onSuccessLogOut();
    }
}
