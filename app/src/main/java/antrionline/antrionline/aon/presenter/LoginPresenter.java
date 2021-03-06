package antrionline.antrionline.aon.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.local.SessionLogin;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.loginregister.LoginView;
import antrionline.antrionline.aon.util.ShowAlert;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 23/01/2018.
 */

public class LoginPresenter {
    private LoginView loginView;
    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(String email, String password){
        RetrofitClient.getInstance()
                .getApi()
                .login(email, password)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            if(status){
                                JsonObject userObject = body.get("data").getAsJsonObject();
                                Type type = new TypeToken<User>(){}.getType();
                                User user = new Gson().fromJson(userObject, type);
                                SaveUserData.getInstance().saveUser(user);
                                SessionLogin.getInstance().setLoginStatus(true);
                                SaveUserData.getInstance().saveUserToken(setUserAuth(Integer.toString(user.getId()), user.getApiToken()));
                                loginView.onSuccessLogin();
                            }else{
                                loginView.onFailureLogin(body.get("message").getAsString());
                            }
                        }else{
                            loginView.onFailureLogin("Login Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        loginView.onFailureLogin("Login Failed");
                    }
                });
    }

    public String setUserAuth(String id, String token) {
        byte[] encodedBytes;
        String authorization;
        authorization = id + ":" + token;
        encodedBytes = Base64.encode(authorization.getBytes(), Base64.NO_WRAP);
        authorization = "Basic " + new String(encodedBytes);
        return authorization;
    }

}
