package cn.dpocket.moplusand.uinew.logic.social;

import android.content.Intent;

import cn.dpocket.moplusand.uinew.logic.LogicSocialMgr;

/**
 * Created by Apple on 16/7/8.
 */

public abstract class Socialbase {

    public LogicSocialMgr.SocialNotitfy socialObs = null;

    public class ShareObject{
        public int type;
    }

    public abstract void Login();

    public abstract void Share(ShareObject obj);

    public abstract void UserInfo();

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public void setNotity(LogicSocialMgr.SocialNotitfy notify){
        this.socialObs = notify;
    };
}
