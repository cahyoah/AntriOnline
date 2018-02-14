package antrionline.antrionline.aon.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.profilesettings.ProfileSettingsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class ProfileSettingsPresenter {
    private ProfileSettingsView profileSettingsView;

    public  ProfileSettingsPresenter(ProfileSettingsView profileSettingsView){
        this.profileSettingsView = profileSettingsView;
    }

    public void showDataProfile(){
        profileSettingsView.showDataProfile(SaveUserData.getInstance().getUser());
    }

    public void updateProfile(String name, String email, String phoneNumber, String password){
        RetrofitClient.getInstance()
                .getApi()
                .updateProfile(SaveUserData.getInstance().getUser().getId(), name, email, phoneNumber, password)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            String message = body.get("message").getAsString();
                            if(status){
                                JsonObject userObject = body.get("data").getAsJsonObject();
                                Type type = new TypeToken<User>(){}.getType();
                                User user = new Gson().fromJson(userObject, type);
                                SaveUserData.getInstance().saveUser(user);
                                profileSettingsView.onSuccessUpdate(user);
                            }else{
                                profileSettingsView.onFailureUpdateProfile(message);
                            }
                        }else{
                            profileSettingsView.onFailureUpdateProfile("Update Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        profileSettingsView.onFailureUpdateProfile("Update Failed");
                    }
                });
    }


}
