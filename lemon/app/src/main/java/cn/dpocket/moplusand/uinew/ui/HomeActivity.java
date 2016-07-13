package cn.dpocket.moplusand.uinew.ui;



import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;
import cn.dpocket.moplusand.uinew.ui.view.FavoritesFragment;
import cn.dpocket.moplusand.uinew.ui.view.LiveFragment;
import cn.dpocket.moplusand.uinew.ui.view.ProfileFragment;

/**
 * Created by Apple on 16/7/12.
 */
public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar;
    private FavoritesFragment  mFavorites = null;
    private LiveFragment mLive = null;
    private ProfileFragment mProfile = null;

    private FragmentManager mFragmentManager = null;
    @Override
    public void WndCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_home);


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
//                mMessageView.setText(TabMessage.get(menuItemId, false));
                setBottomBarSelection(menuItemId);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
//                Toast.makeText(getApplicationContext(), TabMessage.get(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setBottomBarSelection(int menuItemId){
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();


        if(mFavorites != null){
            transaction.hide(mFavorites);
        }
        if(mLive != null){
            transaction.hide(mLive);
        }
        if(mProfile != null){
            transaction.hide(mProfile);
        }

        if(menuItemId == R.id.home_menu_favorites){

            if(mFavorites == null)
            {
                mFavorites = new FavoritesFragment();
                transaction.add(R.id.content,mFavorites);
            }
            else{
                transaction.show(mFavorites);
            }

        }else if(menuItemId == R.id.home_menu_live){

            if(mLive == null)
            {
                mLive = new LiveFragment();
                transaction.add(R.id.content,mLive);
            }
            else{
                transaction.show(mLive);
            }

        }else if(menuItemId == R.id.home_menu_profile){

            if(mProfile == null)
            {
                mProfile = new ProfileFragment();
                transaction.add(R.id.content,mProfile);
            }
            else{
                transaction.show(mProfile);
            }

        }

        transaction.commit();
    }
}
