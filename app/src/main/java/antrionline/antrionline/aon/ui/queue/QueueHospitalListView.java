package antrionline.antrionline.aon.ui.queue;

import java.util.List;

import antrionline.antrionline.aon.data.model.Hospital;

/**
 * Created by adhit on 31/01/2018.
 */

public interface QueueHospitalListView {
    void onSuccessShowQueueHospitalList(List<Hospital> hospitalList);

    void onFailureShowQueueHospitalList(String s);
}
