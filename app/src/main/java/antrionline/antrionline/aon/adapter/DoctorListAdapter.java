package antrionline.antrionline.aon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.AccountMenu;
import antrionline.antrionline.aon.data.model.Doctor;

/**
 * Created by adhit on 31/01/2018.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorListViewHolder> {
    private Context context;
    private List<Doctor> doctorList;
    private OnCardViewClickListener onCardViewClickListener;

    public DoctorListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Doctor> doctorList){
        this.doctorList = doctorList;
        notifyDataSetChanged();
    }

    @Override
    public DoctorListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new DoctorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DoctorListViewHolder holder, int position) {
        final Doctor doctor = doctorList.get(position);
        holder.tvDoctorName.setText(doctor.getHospitalName());
        holder.tvDoctorSpesialis.setText(doctor.getFaskesType());
        holder.tvDoctorAdress.setText(doctor.getFaskesAddress());
        holder.imgCallDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardViewClickListener.onMenuClicked(doctor);

            }
        });
    }


    public void setOnClickDetailListener(OnCardViewClickListener onCardViewClickListener){
        this.onCardViewClickListener = onCardViewClickListener;

    }

    @Override
    public int getItemCount() {
        if(doctorList == null) return 0;
        return doctorList.size();
    }

    public class DoctorListViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName, tvDoctorSpesialis, tvDoctorAdress;
        ImageView imgCallDoctor;

        public DoctorListViewHolder(View itemView) {
            super(itemView);
            tvDoctorName = itemView.findViewById(R.id.tv_doctor_name);
            tvDoctorSpesialis = itemView.findViewById(R.id.tv_doctor_spesialis);
            tvDoctorAdress = itemView.findViewById(R.id.tv_doctor_address);
            imgCallDoctor = itemView.findViewById(R.id.img_call);
        }
    }

    public  interface OnCardViewClickListener{
        void onMenuClicked(Doctor string);
    }
}