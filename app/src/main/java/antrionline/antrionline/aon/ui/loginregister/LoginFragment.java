package antrionline.antrionline.aon.ui.loginregister;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.presenter.LoginPresenter;
import antrionline.antrionline.aon.presenter.ResetPasswordPresenter;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.ui.resetpassword.ResetPasswordFragment;
import antrionline.antrionline.aon.util.ShowAlert;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginView {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnToRegister;
    private LoginPresenter loginPresenter;
    private CoordinatorLayout clLogin;
    private TextView tvForgotPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        btnToRegister = view.findViewById(R.id.btn_open_register);
        clLogin = view.findViewById(R.id.cl_login);
        tvForgotPassword = view.findViewById(R.id.tv_forgot_password);
        initView();
        initPresenter();
        return  view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_open_register){
            getFragmentManager().beginTransaction().
                    replace(R.id.frame_container,
                            new RegisterFragment(),
                            RegisterFragment.class.getSimpleName()).commit();
        }
        if(view.getId() == R.id.btn_login){
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if(email.isEmpty()){
                etEmail.setError(getResources().getString(R.string.text_email_not_empty));
                etEmail.requestFocus();
            }else if(password.isEmpty()){
                etPassword.setError(getResources().getString(R.string.text_password_not_empty));
                etPassword.requestFocus();
            }else{
                ShowAlert.showProgresDialog(getActivity());
                loginPresenter.login(email, password);
            }
        }
        if(view.getId() == R.id.tv_forgot_password){
            getFragmentManager().beginTransaction().
                    replace(R.id.frame_container,
                            new ResetPasswordFragment(),
                            ResetPasswordFragment.class.getSimpleName()).commit();
        }
    }

    public void initView(){
        btnLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    public void initPresenter(){
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onFailureLogin(String s) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clLogin, s);
    }

    @Override
    public void onSuccessLogin() {
        ShowAlert.closeProgresDialog();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
