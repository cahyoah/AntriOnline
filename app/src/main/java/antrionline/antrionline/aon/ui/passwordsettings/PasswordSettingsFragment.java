package antrionline.antrionline.aon.ui.passwordsettings;


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
import antrionline.antrionline.aon.presenter.PasswordSettingsPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordSettingsFragment extends Fragment implements View.OnClickListener, PasswordSettingsView {

    private EditText etPasswordCurrent, etPasswordNew, etPasswordNewConfirm;
    private Button btnSave;
    private PasswordSettingsPresenter passwordSettingsPresenter;
    private CoordinatorLayout clUpdatePasswod;

    public PasswordSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password_settings, container, false);
        etPasswordCurrent = view.findViewById(R.id.et_current_password);
        etPasswordNew = view.findViewById(R.id.et_new_password);
        etPasswordNewConfirm = view.findViewById(R.id.et_password_new_confirm);
        btnSave = view.findViewById(R.id.btn_save);
        clUpdatePasswod = view.findViewById(R.id.cl_update_password);
        initView();
        initPresenter();
        return  view;
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Ganti Password");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        btnSave.setOnClickListener(this);
    }

    public  void initPresenter(){
        passwordSettingsPresenter = new PasswordSettingsPresenter(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_save){
            String currentPassword = etPasswordCurrent.getText().toString().trim();
            String newPassword = etPasswordNew.getText().toString().trim();
            String newPasswordConfirm = etPasswordNewConfirm.getText().toString().trim();

            if(currentPassword.isEmpty()){
                etPasswordCurrent.setError(getResources().getString(R.string.text_password_not_empty));
                etPasswordCurrent.requestFocus();
            }else if(newPassword.isEmpty()){
                etPasswordNew.setError(getResources().getString(R.string.text_password_not_empty));
                etPasswordNew.requestFocus();
            }else if(newPasswordConfirm.isEmpty()){
                etPasswordNewConfirm.setError(getResources().getString(R.string.text_password_not_empty));
                etPasswordNewConfirm.requestFocus();
            }else{
                if(newPassword.equals(newPasswordConfirm)){
                    ShowAlert.showProgresDialog(getActivity());
                    passwordSettingsPresenter.changePassword(currentPassword, newPassword);
                }else{
                    ShowAlert.showSnackBar(clUpdatePasswod, "Konfirmasi Password Tidak Sama");
                    ShowAlert.closeProgresDialog();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccessChangedPassword(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clUpdatePasswod, message);
        etPasswordCurrent.setText("");
        etPasswordNew.setText("");
        etPasswordNewConfirm.setText("");
    }

    @Override
    public void onFailureChangedPassword(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clUpdatePasswod, message);
    }
}
