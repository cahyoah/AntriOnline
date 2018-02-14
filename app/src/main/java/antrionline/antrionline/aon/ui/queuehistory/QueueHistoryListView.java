package antrionline.antrionline.aon.ui.queuehistory;

import java.util.List;

import antrionline.antrionline.aon.data.model.Queue;

/**
 * Created by adhit on 31/01/2018.
 */

public interface QueueHistoryListView {
    void onSuccessShowQueueHistoryList(List<Queue> doctorList);

    void onFailureShowQueueHistoryList(String s);
}
