package cn.moostar.lemon.ui.core;

import android.content.Intent;

import cn.moostar.lemon.logic.LogicSocialMgr;

/**
 * Created by Apple on 16/7/8.
 */
public class ResultActivity extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogicSocialMgr.getSingle().getSocialQQ().onActivityResult(requestCode,resultCode,data);
        LogicSocialMgr.getSingle().getSocialWeibo().onActivityResult(requestCode,resultCode,data);
        LogicSocialMgr.getSingle().getSocialWeiXin().onActivityResult(requestCode,resultCode,data);
    }
}
