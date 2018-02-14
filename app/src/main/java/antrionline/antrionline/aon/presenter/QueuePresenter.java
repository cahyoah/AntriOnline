package antrionline.antrionline.aon.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.queue.QueueView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 01/02/2018.
 */

public class QueuePresenter {
    private QueueView queueView;

    public QueuePresenter(QueueView queueView){
        this.queueView = queueView;
    }

    public void queueInput(String patientName, String responsiblePersonName, String address, String phoneNumber, String date, String time,  String aim, int idHospital){
        RetrofitClient.getInstance()
                .getApi()
                .queueInput(patientName, responsiblePersonName, address, phoneNumber, date, time, SaveUserData.getInstance().getUser().getId(), aim, idHospital)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            String message = body.get("message").getAsString();
                            if(status){
                                JsonObject queueObject = body.get("result").getAsJsonObject();
                                Type type = new TypeToken<Queue>(){}.getType();
                                Queue queue = new Gson().fromJson(queueObject, type);
                                queueView.onSuccessQueueInput(queue);
                            }else{
                                queueView.onFailureQueueInput(message);
                            }
                        }else{
                            queueView.onFailureQueueInput("Pendaftaran Gagal");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        queueView.onFailureQueueInput("Terjadi Kesalahan");
                    }
                });
    }

    public void showDataProfile(){
        queueView.showDataProfile(SaveUserData.getInstance().getUser());
    }
}
