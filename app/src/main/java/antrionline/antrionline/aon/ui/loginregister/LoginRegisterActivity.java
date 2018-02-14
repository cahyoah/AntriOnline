package antrionline.antrionline.aon.ui.loginregister;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import antrionline.antrionline.aon.R;

public class LoginRegisterActivity extends AppCompatActivity {

    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        action = getIntent().getStringExtra("action");
        if(action.equals("login")){
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new LoginFragment(),
                            LoginFragment.class.getSimpleName()).commit();
        }else{
            getFragmentManager().beginTransaction().
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                    replace(R.id.frame_container,
                            new RegisterFragment(),
                            RegisterFragment.class.getSimpleName()).commit();
        }

    }
}
