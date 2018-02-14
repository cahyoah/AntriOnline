package antrionline.antrionline.aon.ui.queuehistory;



import android.app.Fragment;
import android.content.Intent;
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

import com.google.gson.Gson;

import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.adapter.QueueHistoryListAdapter;
import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.presenter.QueueHistoryListPresenter;
import antrionline.antrionline.aon.ui.detailqueue.DetailQueueActivity;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueueHistoryListFragment extends Fragment implements QueueHistoryListView, QueueHistoryListAdapter.OnCardViewClickListener {

    private RecyclerView rvQueueHistory;
    private QueueHistoryListPresenter queueHistoryListPresenter;
    private QueueHistoryListAdapter queueHistoryListAdapter;
    private ProgressBar pbLoading;
    private TextView tvError;
    private SwipeRefreshLayout srlQueueHistoryList;
    
    public QueueHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queue_history_list, container, false);
        pbLoading = view.findViewById(R.id.pb_loading);
        tvError = view.findViewById(R.id.tv_error);
        srlQueueHistoryList = view.findViewById(R.id.srl_queuehistorylist);
        rvQueueHistory = view.findViewById(R.id.rv_queue_history);
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
        queueHistoryListPresenter = new QueueHistoryListPresenter(this);
        queueHistoryListPresenter.showQueueHistoryList();
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Daftar Antrianku");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        queueHistoryListAdapter = new QueueHistoryListAdapter(getActivity());
        queueHistoryListAdapter.setOnClickDetailListener(this);
        rvQueueHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvQueueHistory.setAdapter(queueHistoryListAdapter);
        srlQueueHistoryList.setOnRefreshListener(() -> {
            tvError.setText("");
            pbLoading.setVisibility(View.VISIBLE);
            rvQueueHistory.setVisibility(View.GONE);
            queueHistoryListPresenter.showQueueHistoryList();
            srlQueueHistoryList.setRefreshing(false);  });
    }

    @Override
    public void onMenuClicked(Queue queueHistory) {
        Intent intent = new Intent(getActivity(), DetailQueueActivity.class);
        intent.putExtra(Constant.QUEUE, queueHistory);
        startActivity(intent);
    }

    @Override
    public void onSuccessShowQueueHistoryList(List<Queue> queueHistoryList) {
        if(queueHistoryList.size()==0){
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText("Anda Belum Pernah Mengantri");

        }else{
            pbLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            rvQueueHistory.setVisibility(View.VISIBLE);
            queueHistoryListAdapter.setData(queueHistoryList);
        }
    }


    @Override
    public void onFailureShowQueueHistoryList(String s) {
        pbLoading.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(s);

    }


}
