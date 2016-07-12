package cn.dpocket.moplusand.uinew.ui.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Apple on 16/7/7.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        WndCreate(savedInstanceState);

        CoreActivity.getSingle().onCreate(this);
        JumpActivity.getSingle().addCache(this.getClass());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        WndNewIntent();

        CoreActivity.getSingle().onNewIntent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CoreActivity.getSingle().onDestory(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        WndPause();

        CoreActivity.getSingle().onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        WndResume();

        CoreActivity.getSingle().onResume(this);
    }


    protected void WndCreate(Bundle savedInstanceState){};

    protected void WndNewIntent(){};

    protected void WndPause(){};

    protected void WndResume(){};


}
