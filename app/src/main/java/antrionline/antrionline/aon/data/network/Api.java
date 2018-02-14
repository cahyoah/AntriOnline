package antrionline.antrionline.aon.data.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by adhit on 23/01/2018.
 */

public interface Api {
    @POST("login")
    @FormUrlEncoded
    Call<JsonObject> login(@Field("email") String email,
                           @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<JsonObject> register(@Field("name") String name,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("phone_number") String phoneNumber);

    @PUT("updateprofile/{user}")
    @FormUrlEncoded
    Call<JsonObject> updateProfile(
            @Path("user") int idUser,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_number") String phoneNumber,
            @Field("password") String password) ;

    @PUT("changepassword/{user}")
    @FormUrlEncoded
    Call<JsonObject> changePassword(
            @Path("user") int idUser,
            @Field("current_password") String currentPassword,
            @Field("new_password") String newPassword) ;

    @GET("hospital/{faskesType}")
    Call<JsonObject> getDoctorList(
            @Path("faskesType") String faskesType) ;

    @GET("hospital")
    Call<JsonObject> getHospitalList();

    @GET("showqueue/{idUser}")
    Call<JsonObject> getQueueHistoryList(
            @Path("idUser") Integer idUser);

    @POST("queue")
    @FormUrlEncoded
    Call<JsonObject> queueInput(@Field("patient_name") String patientName,
                                @Field("responsible_person_name") String responsiblePersonName,
                                @Field("address") String address,
                                @Field("phone_number") String phoneNumber,
                                @Field("date") String date,
                                @Field("time") String time,
                                @Field("id_user") int idUser,
                                @Field("aim") String aim,
                                @Field("id_hospital") int idHospital);

    @POST("resetpassword")
    @FormUrlEncoded
    Call<JsonObject> resetPassword(@Field("email") String email);
}
