package antrionline.antrionline.aon.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import antrionline.antrionline.aon.data.model.Hospital;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.hospital.HospitalListView;
import antrionline.antrionline.aon.ui.queue.QueueHospitalListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class QueueHospitalListPresenter {
    private QueueHospitalListView queueHospitalListView;

    public QueueHospitalListPresenter(QueueHospitalListView queueHospitalListView){
        this.queueHospitalListView = queueHospitalListView;
    }

    public  void showQueueHospitalList(){
        RetrofitClient.getInstance()
                .getApi()
                .getHospitalList()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            if(status){
                                JsonArray results = body.get("data").getAsJsonArray();
                                Type type = new TypeToken<List<Hospital>>(){}.getType();
                                List<Hospital> hospitalList = new Gson().fromJson(results, type);
                                queueHospitalListView.onSuccessShowQueueHospitalList(hospitalList);
                            }else{
                                queueHospitalListView.onFailureShowQueueHospitalList("Data Rumah Sakit tidak ditemukan");
                            }

                        }else{
                            queueHospitalListView.onFailureShowQueueHospitalList("Data Rumah Sakit tidak ditemukan");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        queueHospitalListView.onFailureShowQueueHospitalList("Data Rumah Sakit tidak ditemukan" );
                    }
                });
    }
}
