package antrionline.antrionline.aon.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import antrionline.antrionline.aon.data.local.SaveUserData;
import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.queuehistory.QueueHistoryListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class QueueHistoryListPresenter {
    private QueueHistoryListView queueHistoryListView;
    public  QueueHistoryListPresenter(QueueHistoryListView queueHistoryListView){
        this.queueHistoryListView = queueHistoryListView;
    }

    public void showQueueHistoryList(){
        RetrofitClient.getInstance()
                .getApi()
                .getQueueHistoryList(SaveUserData.getInstance().getUser().getId())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            if(status){
                                JsonArray results = body.get("data").getAsJsonArray();
                                Type type = new TypeToken<List<Queue>>(){}.getType();
                                List<Queue> doctorList = new Gson().fromJson(results, type);
                                queueHistoryListView.onSuccessShowQueueHistoryList(doctorList);
                            }else{
                                queueHistoryListView.onFailureShowQueueHistoryList("Anda belum pernah mengantri");
                            }
                        }else{
                            queueHistoryListView.onFailureShowQueueHistoryList("Anda belum pernah mengantri");
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        queueHistoryListView.onFailureShowQueueHistoryList("Terjadi Kesalahan");
                    }
                });
    }

}
