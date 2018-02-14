package antrionline.antrionline.aon.ui.landingpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.local.SessionLogin;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.ui.loginregister.LoginRegisterActivity;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToRegister;
    private Button btnToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        btnToLogin = findViewById(R.id.btn_open_login);
        btnToRegister = findViewById(R.id.btn_open_register);
        initView();
    }

    private void initView(){
        btnToLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);
        if(SessionLogin.getInstance().getLoginStatus()){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        if(view.getId() == R.id.btn_open_login){
            intent.putExtra("action", "login");

        }
        if(view.getId() == R.id.btn_open_register){
            intent.putExtra("action", "register");
        }
        startActivity(intent);
        finish();
    }
}
