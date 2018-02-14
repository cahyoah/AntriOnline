package antrionline.antrionline.aon.ui.hospital;

import java.util.List;

import antrionline.antrionline.aon.data.model.Hospital;

/**
 * Created by adhit on 31/01/2018.
 */

public interface HospitalListView {
    void onSuccessShowHospitalList(List<Hospital> hospitalList);

    void onFailureShowHospitalList(String s);
}
