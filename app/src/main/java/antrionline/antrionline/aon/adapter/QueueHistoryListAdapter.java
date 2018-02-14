package antrionline.antrionline.aon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.Queue;

/**
 * Created by adhit on 31/01/2018.
 */

public class QueueHistoryListAdapter extends RecyclerView.Adapter<QueueHistoryListAdapter.QueueHistoryListViewHolder> {
    private Context context;
    private List<Queue> queueHistoryList;
    private QueueHistoryListAdapter.OnCardViewClickListener onCardViewClickListener;

    public QueueHistoryListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Queue> queueHistoryList){
        this.queueHistoryList = queueHistoryList;
        notifyDataSetChanged();
    }

    @Override
    public QueueHistoryListAdapter.QueueHistoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_queue_history, parent, false);
        return new QueueHistoryListAdapter.QueueHistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QueueHistoryListAdapter.QueueHistoryListViewHolder holder, int position) {
        final Queue queueHistory = queueHistoryList.get(position);
        String[] date = queueHistory.getDate().split(" ");

        holder.tvQueueNumber.setText(Integer.toString(queueHistory.getQueueNumber()));
        holder.tvHospitalName.setText(queueHistory.getHospitalName());
        holder.tvDate.setText(date[0]);
        holder.tvTime.setText(queueHistory.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardViewClickListener.onMenuClicked(queueHistory);

            }
        });
    }


    public void setOnClickDetailListener(QueueHistoryListAdapter.OnCardViewClickListener onCardViewClickListener){
        this.onCardViewClickListener = onCardViewClickListener;

    }

    @Override
    public int getItemCount() {
        if(queueHistoryList == null) return 0;
        return queueHistoryList.size();
    }

    public class QueueHistoryListViewHolder extends RecyclerView.ViewHolder {
        TextView tvQueueNumber, tvHospitalName, tvDate, tvTime;

        public QueueHistoryListViewHolder(View itemView) {
            super(itemView);
            tvQueueNumber = itemView.findViewById(R.id.tv_queue_number);
            tvHospitalName = itemView.findViewById(R.id.tv_hospital_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    public  interface OnCardViewClickListener{
        void onMenuClicked(Queue string);
    }
}
