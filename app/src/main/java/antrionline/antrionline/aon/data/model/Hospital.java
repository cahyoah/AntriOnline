package antrionline.antrionline.aon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adhit on 31/01/2018.
 */

public class Hospital  implements  Parcelable{

    @SerializedName("id_hospital")
    @Expose
    private Integer idHospital;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("faskes_type")
    @Expose
    private String faskesType;
    @SerializedName("faskes_address")
    @Expose
    private String faskesAddress;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;


    protected Hospital(Parcel in) {
        if (in.readByte() == 0) {
            idHospital = null;
        } else {
            idHospital = in.readInt();
        }
        hospitalName = in.readString();
        faskesType = in.readString();
        faskesAddress = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idHospital == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idHospital);
        }
        parcel.writeString(hospitalName);
        parcel.writeString(faskesType);
        parcel.writeString(faskesAddress);
        parcel.writeString(phoneNumber);
    }
}
