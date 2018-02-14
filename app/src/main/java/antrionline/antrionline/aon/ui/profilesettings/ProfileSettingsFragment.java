package antrionline.antrionline.aon.ui.profilesettings;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.presenter.ProfileSettingsPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileSettingsFragment extends Fragment implements View.OnClickListener, ProfileSettingsView {

    private EditText etName, etEmail, etPhoneNumber, etPassword;
    private Button btnSave;
    private ProfileSettingsPresenter profileSettingsPresenter;
    private CoordinatorLayout clUpdateProfile;
    public ProfileSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_settings, container, false);
        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPhoneNumber = view.findViewById(R.id.et_phone_number);
        etPassword = view.findViewById(R.id.et_password);
        btnSave = view.findViewById(R.id.btn_save);
        clUpdateProfile = view.findViewById(R.id.cl_update_profile);
        initView();
        initPresenter();
        return view;
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Edit Profil");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        btnSave.setOnClickListener(this);
    }
    private void initPresenter(){
        profileSettingsPresenter = new ProfileSettingsPresenter(this);
        profileSettingsPresenter.showDataProfile();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_save){
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if(name.isEmpty()){
                etName.setError(getResources().getString(R.string.text_name_not_empty));
                etName.requestFocus();
            }else if(email.isEmpty()){
                etEmail.setError(getResources().getString(R.string.text_email_not_empty));
                etEmail.requestFocus();
            }else if (password.isEmpty()){
                etPassword.setError(getResources().getString(R.string.text_password_not_empty));
                etPassword.requestFocus();
            }else if (phoneNumber.isEmpty()){
                etPhoneNumber.setError(getResources().getString(R.string.text_phone_number_not_empty));
                etPhoneNumber.requestFocus();
            }else {
                ShowAlert.showProgresDialog(getActivity());
                profileSettingsPresenter.updateProfile(name, email, phoneNumber, password);
            }
        }
    }

    @Override
    public void showDataProfile(User user) {
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etPhoneNumber.setText(user.getPhoneNumber());
    }

    @Override
    public void onFailureUpdateProfile(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clUpdateProfile, message);
    }

    @Override
    public void onSuccessUpdate(User user) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clUpdateProfile, "Update Success");
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etPhoneNumber.setText(user.getPhoneNumber());
    }
}
