package antrionline.antrionline.aon.ui.hospital;


import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.adapter.HospitalListAdapter;
import antrionline.antrionline.aon.data.model.Hospital;
import antrionline.antrionline.aon.presenter.HospitalListPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalListFragment extends Fragment implements HospitalListAdapter.OnCardViewClickListener, HospitalListView {

    private RecyclerView rvHospital;
    private HospitalListPresenter hospitalListPresenter;
    private HospitalListAdapter hospitalListAdapter;
    private ProgressBar pbLoading;
    private TextView tvError;
    private SwipeRefreshLayout srlHospitalList;
    
    public HospitalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hospital_list, container, false);
        pbLoading = view.findViewById(R.id.pb_loading);
        tvError = view.findViewById(R.id.tv_error);
        srlHospitalList = view.findViewById(R.id.srl_hospitallist);
        rvHospital = view.findViewById(R.id.rv_hospital);
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
        hospitalListPresenter = new HospitalListPresenter(this);
        hospitalListPresenter.showHospitalList();
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Lokasi Faskes");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        hospitalListAdapter = new HospitalListAdapter(getActivity());
        hospitalListAdapter.setOnClickDetailListener(this);
        rvHospital.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHospital.setAdapter(hospitalListAdapter);
        srlHospitalList.setOnRefreshListener(() -> {
            tvError.setText("");
            pbLoading.setVisibility(View.VISIBLE);
            rvHospital.setVisibility(View.GONE);
            hospitalListPresenter.showHospitalList();
            srlHospitalList.setRefreshing(false);  });
    }

    @Override
    public void onMenuClicked(Hospital Hospital) {
        int checkPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Hubungi Dokter");
            alertDialog.setMessage("Apakah anda yakin ingin menghubungi "+ Hospital.getHospitalName() +" ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Hospital.getPhoneNumber()));
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
    public void onSuccessShowHospitalList(List<Hospital> hospitalList) {
        if(hospitalList.size()==0){
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText("Dokter Tidak Ditemukan");

        }else{
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            rvHospital.setVisibility(View.VISIBLE);
            hospitalListAdapter.setData(hospitalList);
        }
    }

    @Override
    public void onFailureShowHospitalList(String s) {
        pbLoading.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(s);

    }

}
