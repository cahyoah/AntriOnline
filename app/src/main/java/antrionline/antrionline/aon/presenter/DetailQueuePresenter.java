package antrionline.antrionline.aon.presenter;

import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.ui.detailqueue.DetailQueueView;

/**
 * Created by adhit on 01/02/2018.
 */

public class DetailQueuePresenter {
    private DetailQueueView detailQueueView;

    public DetailQueuePresenter(DetailQueueView detailQueueView){
        this.detailQueueView = detailQueueView;
    }

    public void showQueue(Queue queue){
        detailQueueView.onSuccessShowQueue(queue);
    }
}
