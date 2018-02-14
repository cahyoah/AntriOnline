package antrionline.antrionline.aon.ui.queue;

import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.data.model.User;

/**
 * Created by adhit on 01/02/2018.
 */

public interface QueueView {
    void onFailureQueueInput(String message);

    void onSuccessQueueInput(Queue queue);

    void showDataProfile(User user);
}
