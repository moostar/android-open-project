package cn.moostar.lemon.ui;

import android.os.Bundle;
import android.view.View;

import cn.moostar.lemon.R;
import cn.moostar.lemon.ui.core.BaseActivity;
import cn.moostar.lemon.ui.jump.ToLogin;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
