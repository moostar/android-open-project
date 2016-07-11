package cn.dpocket.moplusand.uinew.ui;

import android.content.Intent;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.logic.LogicSocialMgr;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;
import cn.dpocket.moplusand.uinew.ui.view.SocialFragment;

/**
 * Created by Apple on 16/7/7.
 */
public class LoginActivity extends BaseActivity implements SocialFragment.ISSOLoginFragment {

    @Override
    public void wndCreate() {

        setContentView(R.layout.activity_login);

    }

    @Override
    public void onLoginFramentClick(int type) {
        switch (type){
            case SocialFragment.ISSOLoginFragment.QQ:
                LogicSocialMgr.getSingle().login(LogicSocialMgr.LOGIN_QQ);
                break;
            case SocialFragment.ISSOLoginFragment.WEIBO:
                LogicSocialMgr.getSingle().login(LogicSocialMgr.LOGIN_WEIBO);
                break;
            case SocialFragment.ISSOLoginFragment.WEIXIN:
                LogicSocialMgr.getSingle().login(LogicSocialMgr.LOGIN_WEIXIN);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogicSocialMgr.getSingle().onActivityResult(requestCode,resultCode,data);
    }
}
