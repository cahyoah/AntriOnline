package antrionline.antrionline.aon.presenter;

import com.google.gson.JsonObject;

import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.loginregister.RegisterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 23/01/2018.
 */

public class RegisterPresenter {
    private RegisterView registerview;

    public  RegisterPresenter(RegisterView registerView){
        this.registerview = registerView;
    }

    public void register(String name, String email, String password, String phoneNumber){
        RetrofitClient.getInstance()
                .getApi()
                .register(name, email, password, phoneNumber)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            String message = body.get("message").getAsString();
                            if(status){
                                registerview.onSuccessRegister(message);
                            }else{
                                registerview.onFailedRegister(message);
                            }
                        }else{
                            registerview.onFailedRegister("Register Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        registerview.onFailedRegister("Register Failed");
                    }
                });
    }
}
