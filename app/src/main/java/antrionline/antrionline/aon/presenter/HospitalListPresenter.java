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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class HospitalListPresenter {
    private HospitalListView hospitalListView;
    
    public HospitalListPresenter(HospitalListView hospitalListView){
        this.hospitalListView = hospitalListView;
    }

    public  void showHospitalList(){
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
                                hospitalListView.onSuccessShowHospitalList(hospitalList);
                            }else{
                                hospitalListView.onFailureShowHospitalList("Data Rumah Sakit tidak ditemukan");
                            }

                        }else{
                            hospitalListView.onFailureShowHospitalList("Data Rumah Sakit tidak ditemukan");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        hospitalListView.onFailureShowHospitalList("Data Rumah Sakit tidak ditemukan" );
                    }
                });
    }
    
}
