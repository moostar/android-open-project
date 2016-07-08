package cn.moostar.lemon.ui;

import android.os.Bundle;

import cn.moostar.lemon.R;
import cn.moostar.lemon.logic.LogicSocialMgr;
import cn.moostar.lemon.ui.core.ResultActivity;
import cn.moostar.lemon.ui.view.SocialFragment;

/**
 * Created by Apple on 16/7/7.
 */
public class LoginActivity extends ResultActivity implements SocialFragment.ISSOLoginFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Override
    public void onLoginFramentClick(int type) {
        switch (type){
            case SocialFragment.ISSOLoginFragment.QQ:
                break;
            case SocialFragment.ISSOLoginFragment.WEIBO:
                LogicSocialMgr.getSingle().getSocialWeibo().Login();
                break;
            case SocialFragment.ISSOLoginFragment.WEIXIN:
                break;
            default:
                break;
        }
    }
}
