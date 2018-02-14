package antrionline.antrionline.aon.presenter;

import com.google.gson.JsonObject;

import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.resetpassword.ResetPasswordView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 01/02/2018.
 */

public class ResetPasswordPresenter {
    private ResetPasswordView resetPasswordView;

    public ResetPasswordPresenter(ResetPasswordView resetPasswordView){
        this.resetPasswordView = resetPasswordView;
    }

    public void resetPassword(String email){
        RetrofitClient.getInstance()
                .getApi()
                .resetPassword(email)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            String message = body.get("message").getAsString();
                            if(status){
                                resetPasswordView.onSuccessResetPassword(message);
                            }else{
                                resetPasswordView.onFailureResetPassword(message);
                            }
                        }else{
                            resetPasswordView.onFailureResetPassword("Terjadi kesalahan");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        resetPasswordView.onFailureResetPassword("Terjadi kesalahan");
                    }
                });
    }

}
