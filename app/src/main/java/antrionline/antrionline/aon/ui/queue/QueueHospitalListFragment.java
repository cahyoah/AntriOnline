package antrionline.antrionline.aon.ui.queue;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import antrionline.antrionline.aon.adapter.QueueHospitalListAdapter;
import antrionline.antrionline.aon.data.model.Hospital;
import antrionline.antrionline.aon.presenter.QueueHospitalListPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueueHospitalListFragment extends Fragment implements QueueHospitalListView, HospitalListAdapter.OnCardViewClickListener {

    private RecyclerView rvQueueHospital;
    private QueueHospitalListPresenter queueHospitalListPresenter;
    private QueueHospitalListAdapter queueHospitalListAdapter;
    private ProgressBar pbLoading;
    private TextView tvError;
    private SwipeRefreshLayout srlQueueHospitalList;

    public QueueHospitalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_queue_hospital_list, container, false);
        pbLoading = view.findViewById(R.id.pb_loading);
        tvError = view.findViewById(R.id.tv_error);
        srlQueueHospitalList = view.findViewById(R.id.srl_queuehospitallist);
        rvQueueHospital = view.findViewById(R.id.rv_queuehospital);
        initView();
        initPresenter();
        return  view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public  void initPresenter(){
        queueHospitalListPresenter = new QueueHospitalListPresenter(this);
        queueHospitalListPresenter.showQueueHospitalList();
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Pilih Rumah Sakit");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        queueHospitalListAdapter = new QueueHospitalListAdapter(getActivity());
        queueHospitalListAdapter.setOnClickDetailListener(this);
        rvQueueHospital.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvQueueHospital.setAdapter(queueHospitalListAdapter);
        srlQueueHospitalList.setOnRefreshListener(() -> {
            tvError.setText("");
            pbLoading.setVisibility(View.VISIBLE);
            rvQueueHospital.setVisibility(View.GONE);
            queueHospitalListPresenter.showQueueHospitalList();
            srlQueueHospitalList.setRefreshing(false);  });
    }

    @Override
    public void onMenuClicked(Hospital hospital) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.HOSPITAL, hospital);
        QueueFragment queueFragment = new QueueFragment();
        queueFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().
                replace(R.id.frame_container,
                        queueFragment).
                addToBackStack(QueueFragment.class.getSimpleName()).commit();

    }

    @Override
    public void onSuccessShowQueueHospitalList(List<Hospital> hospitalList) {
        if(hospitalList.size()==0){
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText("Dokter Tidak Ditemukan");

        }else{
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            rvQueueHospital.setVisibility(View.VISIBLE);
            queueHospitalListAdapter.setData(hospitalList);
        }
    }

    @Override
    public void onFailureShowQueueHospitalList(String s) {
        pbLoading.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(s);

    }

}
