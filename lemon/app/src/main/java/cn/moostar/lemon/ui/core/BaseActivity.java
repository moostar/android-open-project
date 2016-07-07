package cn.moostar.lemon.ui.core;

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

        CoreActivity.getSingle().onCreate(this);
        JumpActivity.getSingle().addCache(this.getClass());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
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
        CoreActivity.getSingle().onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CoreActivity.getSingle().onResume(this);
    }
}
