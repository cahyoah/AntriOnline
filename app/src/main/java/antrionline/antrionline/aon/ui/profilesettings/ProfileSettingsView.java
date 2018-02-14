package antrionline.antrionline.aon.ui.profilesettings;

import antrionline.antrionline.aon.data.model.User;

/**
 * Created by adhit on 31/01/2018.
 */

public interface ProfileSettingsView {
    void showDataProfile(User user);

    void onFailureUpdateProfile(String message);

    void onSuccessUpdate(User user);
}
