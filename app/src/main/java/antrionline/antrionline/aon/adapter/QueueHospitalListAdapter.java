package antrionline.antrionline.aon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.Hospital;

/**
 * Created by adhit on 31/01/2018.
 */

public class QueueHospitalListAdapter extends RecyclerView.Adapter<QueueHospitalListAdapter.QueueHospitalListViewHolder> {
    private Context context;
    private List<Hospital> queueHospitalList;
    private HospitalListAdapter.OnCardViewClickListener onCardViewClickListener;

    public QueueHospitalListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Hospital> hospitalList){
        this.queueHospitalList = hospitalList;
        notifyDataSetChanged();
    }

    @Override
    public QueueHospitalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_queue_hospital, parent, false);
        return new QueueHospitalListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QueueHospitalListViewHolder holder, int position) {
        final Hospital hospital = queueHospitalList.get(position);
        holder.tvhospitalName.setText(hospital.getHospitalName());
        holder.tvhospitalSpesialis.setText(hospital.getFaskesType());
        holder.tvhospitalAdress.setText(hospital.getFaskesAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardViewClickListener.onMenuClicked(hospital);

            }
        });
    }


    public void setOnClickDetailListener(HospitalListAdapter.OnCardViewClickListener onCardViewClickListener){
        this.onCardViewClickListener = onCardViewClickListener;

    }

    @Override
    public int getItemCount() {
        if(queueHospitalList == null) return 0;
        return queueHospitalList.size();
    }

    public class QueueHospitalListViewHolder extends RecyclerView.ViewHolder {
        TextView tvhospitalName, tvhospitalSpesialis, tvhospitalAdress;

        public QueueHospitalListViewHolder(View itemView) {
            super(itemView);
            tvhospitalName = itemView.findViewById(R.id.tv_hospital_name);
            tvhospitalSpesialis = itemView.findViewById(R.id.tv_hospital_spesialis);
            tvhospitalAdress = itemView.findViewById(R.id.tv_hospital_address);

        }
    }

    public  interface OnCardViewClickListener{
        void onMenuClicked(Hospital hospital);
    }
}
