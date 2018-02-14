package antrionline.antrionline.aon.ui.doctor;


import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.adapter.DoctorListAdapter;
import antrionline.antrionline.aon.data.model.Doctor;
import antrionline.antrionline.aon.presenter.DoctorListPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorListFragment extends Fragment implements DoctorListView, DoctorListAdapter.OnCardViewClickListener {

    private RecyclerView rvDoctor;
    private DoctorListPresenter doctorListPresenter;
    private DoctorListAdapter doctorListAdapter;
    private ProgressBar pbLoading;
    private TextView tvError;
    private SwipeRefreshLayout srlDoctorList;

    public DoctorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        pbLoading = view.findViewById(R.id.pb_loading);
        tvError = view.findViewById(R.id.tv_error);
        srlDoctorList = view.findViewById(R.id.srl_doctorlist);
        rvDoctor = view.findViewById(R.id.rv_doctor);

        initView();
        initPresenter();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public  void initPresenter(){
        doctorListPresenter = new DoctorListPresenter(this);
        doctorListPresenter.showDoctorList();
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Daftar Dokter");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        doctorListAdapter = new DoctorListAdapter(getActivity());
        doctorListAdapter.setOnClickDetailListener(this);
        rvDoctor.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDoctor.setAdapter(doctorListAdapter);
        srlDoctorList.setOnRefreshListener(() -> {
            tvError.setText("");
            pbLoading.setVisibility(View.VISIBLE);
            rvDoctor.setVisibility(View.GONE);
            doctorListPresenter.showDoctorList();
            srlDoctorList.setRefreshing(false);  });
    }

    @Override
    public void onMenuClicked(Doctor doctor) {
        int checkPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                   getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Hubungi Dokter");
            alertDialog.setMessage("Apakah anda yakin ingin menghubungi "+ doctor.getHospitalName() +" ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + doctor.getPhoneNumber()));
                            startActivity(intent);
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",(dialogInterface, i) -> alertDialog.dismiss());
            alertDialog.show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ShowAlert.showToast(getActivity(),"Tidak Diijinkan");
                }
                return;
            }
        }
    }

    @Override
    public void onSuccessShowDoctorList(List<Doctor> doctorList) {
        if(doctorList.size()==0){
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText("Dokter Tidak Ditemukan");

        }else{
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            rvDoctor.setVisibility(View.VISIBLE);
            doctorListAdapter.setData(doctorList);
        }
    }

    @Override
    public void onFailureShowDoctorList(String s) {
        pbLoading.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(s);

    }
}
