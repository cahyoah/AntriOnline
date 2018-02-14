package antrionline.antrionline.aon.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import antrionline.antrionline.aon.data.model.Doctor;
import antrionline.antrionline.aon.data.network.RetrofitClient;
import antrionline.antrionline.aon.ui.doctor.DoctorListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adhit on 31/01/2018.
 */

public class DoctorListPresenter {
    private DoctorListView doctorListView;
    public DoctorListPresenter(DoctorListView doctorListView){
        this.doctorListView = doctorListView;
    }

    public  void showDoctorList(){
        RetrofitClient.getInstance()
                .getApi()
                .getDoctorList("DOKTER UMUM")
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            JsonObject body = response.body();
                            boolean status = body.get("status").getAsBoolean();
                            if(status){
                                JsonArray results = body.get("data").getAsJsonArray();
                                Type type = new TypeToken<List<Doctor>>(){}.getType();
                                List<Doctor> doctorList = new Gson().fromJson(results, type);
                                doctorListView.onSuccessShowDoctorList(doctorList);
                            }else{
                                doctorListView.onFailureShowDoctorList("Data Dokter tidak ditemukan");
                            }

                        }else{
                            doctorListView.onFailureShowDoctorList("Data Dokter tidak ditemukan");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        doctorListView.onFailureShowDoctorList("Data Dokter tidak ditemukan" );
                    }
                });
    }
}
