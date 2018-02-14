package antrionline.antrionline.aon.ui.loginregister;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.presenter.RegisterPresenter;
import antrionline.antrionline.aon.util.ShowAlert;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterView {

    private Button btnRegister, btnToLogin;
    private EditText etName, etEmail, etPassword, etPhoneNumber;
    private RegisterPresenter registerPresenter;
    private CoordinatorLayout clRegister;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btnToLogin = view.findViewById(R.id.btn_open_login);
        btnRegister = view.findViewById(R.id.btn_register);
        clRegister = view.findViewById(R.id.cl_register);
        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etPhoneNumber = view.findViewById(R.id.et_phone_number);
        initView();
        initPresenter();
        return view;
    }

    private void initPresenter(){
        registerPresenter = new RegisterPresenter(this);
    }

    private void initView(){
        btnToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_open_login){
            getFragmentManager().beginTransaction().
                    replace(R.id.frame_container,
                            new LoginFragment(),
                            LoginFragment.class.getSimpleName()).commit();
        }
        if(view.getId() == R.id.btn_register){
            String name  = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
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
                registerPresenter.register(name, email, password, phoneNumber);
            }
        }
    }

    @Override
    public void onSuccessRegister(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clRegister, message);
    }

    @Override
    public void onFailedRegister(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clRegister, message);
    }
}
