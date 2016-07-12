package cn.dpocket.moplusand.uinew.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;

/**
 * Created by Apple on 16/7/12.
 */
public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar;
    @Override
    public void WndCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_home);

        // Customize the colors here
        mBottomBar = BottomBar.attach(this, savedInstanceState,
                Color.parseColor("#FFFFFF"), // Background Color
                ContextCompat.getColor(this, R.color.colorAccent), // Tab Item Color
                0.25f); // Tab Item Alpha

        mBottomBar.setItems(R.menu.bottombar_menu);

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
//                mMessageView.setText(TabMessage.get(menuItemId, false));
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
//                Toast.makeText(getApplicationContext(), TabMessage.get(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }



}
