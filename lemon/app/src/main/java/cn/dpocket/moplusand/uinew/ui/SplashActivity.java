package cn.dpocket.moplusand.uinew.ui;

import android.view.View;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;
import cn.dpocket.moplusand.uinew.ui.jump.ToLogin;

public class SplashActivity extends BaseActivity {

    @Override
    public void wndCreate() {

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();





        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin.login();
            }
        });
    }





}
