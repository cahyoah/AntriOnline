package antrionline.antrionline.aon.ui.home.account;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.adapter.AccountMenuAdapter;
import antrionline.antrionline.aon.data.model.AccountMenu;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.presenter.AccountMenuPresenter;
import antrionline.antrionline.aon.presenter.PasswordSettingsPresenter;
import antrionline.antrionline.aon.ui.passwordsettings.PasswordSettingsFragment;
import antrionline.antrionline.aon.ui.profilesettings.ProfileSettingsFragment;
import antrionline.antrionline.aon.ui.landingpage.LandingPageActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountView, AccountMenuAdapter.OnCardViewClickListener, View.OnClickListener {

    private TextView tvName, tvEmail, tvPhoneNumber;
    private AccountMenuPresenter accountMenuPresenter;
    private ImageView imgGoEdit;
    private RecyclerView rvAccountMenu;
    private AccountMenuAdapter accountMenuAdapter;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
        imgGoEdit = view.findViewById(R.id.img_go_edit);
        rvAccountMenu = view.findViewById(R.id.rv_account_menu);
        initView();
        initPresenter();
        return  view;
    }

    public void initPresenter(){
        accountMenuPresenter = new AccountMenuPresenter(this);
        accountMenuPresenter.showAccountProfile();
        accountMenuPresenter.showAccountMenu();
    }

    public void initView(){
        imgGoEdit.setOnClickListener(this);
        accountMenuAdapter = new AccountMenuAdapter(getActivity());
        accountMenuAdapter.setOnClickDetailListener(this);
        rvAccountMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAccountMenu.setAdapter(accountMenuAdapter);
    }

    @Override
    public void showAccountProfile(User user) {
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvPhoneNumber.setText(user.getPhoneNumber());
    }

    @Override
    public void showAccountMenu(ArrayList<AccountMenu> accountMenuArrayList) {
        accountMenuAdapter.setData(accountMenuArrayList);
    }

    @Override
    public void onSuccessLogOut() {
        Intent intent = new Intent(getActivity(), LandingPageActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onMenuClicked(String string) {
        if(string.equals("Keluar")){
            accountMenuPresenter.logout();
        }
        if(string.equals("Ganti Password")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new PasswordSettingsFragment(),
                            PasswordSettingsFragment.class.getSimpleName())
                    .addToBackStack(PasswordSettingsFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.img_go_edit){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new ProfileSettingsFragment(),
                            ProfileSettingsFragment.class.getSimpleName())
                    .addToBackStack(ProfileSettingsFragment.class.getSimpleName())
                    .commit();
        }
    }
}
