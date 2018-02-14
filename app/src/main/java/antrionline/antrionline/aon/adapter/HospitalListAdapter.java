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
public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.HospitalListViewHolder> {
    private Context context;
    private List<Hospital> hospitalList;
    private OnCardViewClickListener onCardViewClickListener;

    public HospitalListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Hospital> hospitalList){
        this.hospitalList = hospitalList;
        notifyDataSetChanged();
    }

    @Override
    public HospitalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        return new HospitalListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HospitalListViewHolder holder, int position) {
        final Hospital hospital = hospitalList.get(position);
        holder.tvhospitalName.setText(hospital.getHospitalName());
        holder.tvhospitalSpesialis.setText(hospital.getFaskesType());
        holder.tvhospitalAdress.setText(hospital.getFaskesAddress());
        holder.imgCallhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardViewClickListener.onMenuClicked(hospital);

            }
        });
    }


    public void setOnClickDetailListener(OnCardViewClickListener onCardViewClickListener){
        this.onCardViewClickListener = onCardViewClickListener;

    }

    @Override
    public int getItemCount() {
        if(hospitalList == null) return 0;
        return hospitalList.size();
    }

    public class HospitalListViewHolder extends RecyclerView.ViewHolder {
        TextView tvhospitalName, tvhospitalSpesialis, tvhospitalAdress;
        ImageView imgCallhospital;

        public HospitalListViewHolder(View itemView) {
            super(itemView);
            tvhospitalName = itemView.findViewById(R.id.tv_hospital_name);
            tvhospitalSpesialis = itemView.findViewById(R.id.tv_hospital_spesialis);
            tvhospitalAdress = itemView.findViewById(R.id.tv_hospital_address);
            imgCallhospital = itemView.findViewById(R.id.img_call);
        }
    }

    public  interface OnCardViewClickListener{
        void onMenuClicked(Hospital hospital);
    }
}