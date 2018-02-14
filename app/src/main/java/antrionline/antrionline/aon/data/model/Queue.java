package antrionline.antrionline.aon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adhit on 31/01/2018.
 */

public class Queue  implements Parcelable{
    @SerializedName("id_queue")
    @Expose
    private Integer idQueue;
    @SerializedName("queue_number")
    @Expose
    private Integer queueNumber;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("responsible_person_name")
    @Expose
    private String responsiblePersonName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_hospital")
    @Expose
    private String idHospital;
    @SerializedName("aim")
    @Expose
    private String aim;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("faskes_type")
    @Expose
    private String faskesType;
    @SerializedName("faskes_address")
    @Expose
    private String faskesAddress;
    @SerializedName("hospital_phone_number")
    @Expose
    private String hospitalPhoneNumber;


    protected Queue(Parcel in) {
        if (in.readByte() == 0) {
            idQueue = null;
        } else {
            idQueue = in.readInt();
        }
        if (in.readByte() == 0) {
            queueNumber = null;
        } else {
            queueNumber = in.readInt();
        }
        patientName = in.readString();
        responsiblePersonName = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        date = in.readString();
        time = in.readString();
        if (in.readByte() == 0) {
            idUser = null;
        } else {
            idUser = in.readInt();
        }
        idHospital = in.readString();
        aim = in.readString();
        createdAt = in.readString();
        hospitalName = in.readString();
        faskesType = in.readString();
        faskesAddress = in.readString();
        hospitalPhoneNumber = in.readString();
    }

    public static final Creator<Queue> CREATOR = new Creator<Queue>() {
        @Override
        public Queue createFromParcel(Parcel in) {
            return new Queue(in);
        }

        @Override
        public Queue[] newArray(int size) {
            return new Queue[size];
        }
    };

    public Integer getIdQueue() {
        return idQueue;
    }

    public void setIdQueue(Integer idQueue) {
        this.idQueue = idQueue;
    }

    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(String idHospital) {
        this.idHospital = idHospital;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getFaskesType() {
        return faskesType;
    }

    public void setFaskesType(String faskesType) {
        this.faskesType = faskesType;
    }

    public String getFaskesAddress() {
        return faskesAddress;
    }

    public void setFaskesAddress(String faskesAddress) {
        this.faskesAddress = faskesAddress;
    }

    public String getHospitalPhoneNumber() {
        return hospitalPhoneNumber;
    }

    public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
        this.hospitalPhoneNumber = hospitalPhoneNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idQueue == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idQueue);
        }
        if (queueNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(queueNumber);
        }
        parcel.writeString(patientName);
        parcel.writeString(responsiblePersonName);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(date);
        parcel.writeString(time);
        if (idUser == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idUser);
        }
        parcel.writeString(idHospital);
        parcel.writeString(aim);
        parcel.writeString(createdAt);
        parcel.writeString(hospitalName);
        parcel.writeString(faskesType);
        parcel.writeString(faskesAddress);
        parcel.writeString(hospitalPhoneNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
