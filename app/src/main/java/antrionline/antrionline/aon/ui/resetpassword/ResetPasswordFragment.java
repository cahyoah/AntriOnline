package antrionline.antrionline.aon.ui.resetpassword;


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
import antrionline.antrionline.aon.presenter.ResetPasswordPresenter;
import antrionline.antrionline.aon.ui.loginregister.LoginFragment;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment implements ResetPasswordView, View.OnClickListener {

    private ResetPasswordPresenter resetPasswordPresenter;
    private EditText etEmail;
    private Button btnResetPassword, btnGoToLogin;
    private CoordinatorLayout clResetPassword;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        etEmail =view.findViewById(R.id.et_email);
        btnGoToLogin = view.findViewById(R.id.btn_go_to_login);
        btnResetPassword = view.findViewById(R.id.btn_reset_password);
        clResetPassword = view.findViewById(R.id.cl_reset_password);
        initPresenter();
        initView();
        return view;
    }
    private void initView(){
        btnResetPassword.setOnClickListener(this);
        btnGoToLogin.setOnClickListener(this);
    }

    private void initPresenter() {
        resetPasswordPresenter = new ResetPasswordPresenter(this);
    }


    @Override
    public void onSuccessResetPassword(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clResetPassword, message);
    }

    @Override
    public void onFailureResetPassword(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clResetPassword, message);
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
        if(view.getId() == R.id.btn_go_to_login){
            getFragmentManager().beginTransaction().
                    replace(R.id.frame_container,
                            new LoginFragment(),
                            LoginFragment.class.getSimpleName()).commit();
        }
        if(view.getId() == R.id.btn_reset_password){
            String email = etEmail.getText().toString().trim();
            if(email.isEmpty()){
                etEmail.setError(getResources().getString(R.string.text_email_not_empty));
                etEmail.requestFocus();
            }else{
                ShowAlert.showProgresDialog(getActivity());
                resetPasswordPresenter.resetPassword(email);
            }

        }
    }
}
