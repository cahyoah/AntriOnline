package antrionline.antrionline.aon.ui.home.menu;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.adapter.MenuAdapter;
import antrionline.antrionline.aon.adapter.QueueHistoryListAdapter;
import antrionline.antrionline.aon.data.model.Menu;
import antrionline.antrionline.aon.presenter.MenuPresenter;
import antrionline.antrionline.aon.ui.doctor.DoctorListFragment;
import antrionline.antrionline.aon.ui.hospital.HospitalListFragment;
import antrionline.antrionline.aon.ui.profilesettings.ProfileSettingsFragment;
import antrionline.antrionline.aon.ui.queue.QueueHospitalListFragment;
import antrionline.antrionline.aon.ui.queuehistory.QueueHistoryListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements MenuView, MenuAdapter.OnDetailListener {

    private SliderLayout sliderLayout;
    private MenuPresenter menuPresenter;
    private MenuAdapter menuAdapter;
    private RecyclerView rvMenu;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_menu, container, false);
        sliderLayout = view.findViewById(R.id.slider);
        rvMenu = view.findViewById(R.id.rv_menu);
        showImage();
        initView();
        initPresenter();
        return view;
    }

    private void initView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        menuAdapter = new MenuAdapter(getActivity());
        menuAdapter.setOnClickDetailListener(this);
        rvMenu.setAdapter(menuAdapter);
        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(gridLayoutManager);
        menuPresenter = new MenuPresenter(this);
        menuPresenter.showListMenu();
    }

    private void initPresenter(){
        menuPresenter = new MenuPresenter(this);
    }

    public void showImage(){
        ArrayList<Integer> gambar = new ArrayList<>();
        gambar.add(R.drawable.a1);
        gambar.add(R.drawable.a2);
        for(int i=0; i<gambar.size(); i++){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .image(gambar.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }


    @Override
    public void showData(ArrayList<Menu> menuArrayList) {
        menuAdapter.setData(menuArrayList);
    }

    @Override
    public void onItemDetailClicked(String menu) {
        if(menu.equals("Daftar Dokter")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new DoctorListFragment(),
                            DoctorListFragment.class.getSimpleName())
                    .addToBackStack(DoctorListFragment.class.getSimpleName())
                    .commit();
        }
        if(menu.equals("Daftar Antrianku")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new QueueHistoryListFragment(),
                            QueueHistoryListFragment.class.getSimpleName())
                    .addToBackStack(QueueHistoryListFragment.class.getSimpleName())
                    .commit();
        }
        if(menu.equals("Lokasi Pelayanan Kesehatan")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new HospitalListFragment(),
                            HospitalListFragment.class.getSimpleName())
                    .addToBackStack(HospitalListFragment.class.getSimpleName())
                    .commit();
        }
        if(menu.equals("Antri")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new QueueHospitalListFragment(),
                            QueueHospitalListFragment.class.getSimpleName())
                    .addToBackStack(QueueHospitalListFragment.class.getSimpleName())
                    .commit();
        }
    }
}
