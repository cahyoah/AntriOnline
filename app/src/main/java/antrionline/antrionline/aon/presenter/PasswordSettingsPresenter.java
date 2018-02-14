package antrionline.antrionline.aon.presenter;

import com.google.gson.JsonObject;

import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.passwordsettings.PasswordSettingsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class PasswordSettingsPresenter {
    private PasswordSettingsView passwordSettingsView;
    public PasswordSettingsPresenter(PasswordSettingsView passwordSettingsView){
        this.passwordSettingsView = passwordSettingsView;
    }

    public void changePassword(String currentPassword, String newPassword){
        RetrofitClient.getInstance()
                .getApi()
                .changePassword(SaveUserData.getInstance().getUser().getId(), currentPassword, newPassword)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            String message = body.get("message").getAsString();
                            if(status){
                                passwordSettingsView.onSuccessChangedPassword(message);
                            }else {
                                passwordSettingsView.onFailureChangedPassword(message);
                            }

                        }else{
                            passwordSettingsView.onFailureChangedPassword("Update Password Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        passwordSettingsView.onFailureChangedPassword("Update Password Failed");
                    }
                });
    }

}
