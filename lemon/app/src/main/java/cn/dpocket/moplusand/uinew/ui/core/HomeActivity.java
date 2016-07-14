package cn.dpocket.moplusand.uinew.ui.core;



import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import cn.dpocket.moplusand.uinew.R;

/**
 * Created by Apple on 16/7/12.
 */
public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar = null;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


        WndCreate();



        InitBottomBar(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    private void InitBottomBar(Bundle savedInstanceState){
        // Customize the colors here
        mBottomBar = BottomBar.attach(this, savedInstanceState);//,
//                Color.parseColor("#FFFFFF"), // Background Color
//                ContextCompat.getColor(this, R.color.colorAccent), // Tab Item Color
//                0.25f); // Tab Item Alpha
        mBottomBar.setMaxFixedTabs(2);

        mBottomBar.setItems(R.menu.bottombar_menu);

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                MenuTabSelected(menuItemId);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
    }

    protected void MenuTabSelected(int menuItemId){

    }
}
