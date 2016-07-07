package cn.moostar.lemon.ui;

import android.os.Bundle;

import cn.moostar.lemon.R;
import cn.moostar.lemon.ui.core.BaseActivity;
import cn.moostar.lemon.ui.core.jump.ToLogin;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        ToLogin.login();
    }



}
