package antrionline.antrionline.aon.ui.home;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.ui.home.account.AccountFragment;
import antrionline.antrionline.aon.ui.home.menu.MenuFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        getFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.frame_container,
                        new MenuFragment(),
                        MenuFragment.class.getSimpleName()).commit();

    }

    private void initView() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_container,
                                    new MenuFragment(),
                                    MenuFragment.class.getSimpleName())
                            .commit();
                    return true;


                case R.id.navigation_account:
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_container,
                                    new AccountFragment(),
                                    AccountFragment.class.getSimpleName())

                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(getFragmentManager().getBackStackEntryCount()==0){
            navigation.setVisibility(View.VISIBLE);
            findViewById(R.id.img_logo).setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }


}
