package antrionline.antrionline.aon.ui.doctor;

import java.util.List;

import antrionline.antrionline.aon.data.model.Doctor;

/**
 * Created by adhit on 31/01/2018.
 */

public interface DoctorListView {
    void onSuccessShowDoctorList(List<Doctor> doctorList);

    void onFailureShowDoctorList(String s);
}
