package cn.dpocket.moplusand.uinew.ui.jump;

import cn.dpocket.moplusand.uinew.ui.LoginActivity;
import cn.dpocket.moplusand.uinew.ui.core.JumpActivity;

/**
 * Created by Apple on 16/7/7.
 */
public class ToLogin {

    public static void login(){
        JumpActivity.JumpInfo info = new JumpActivity.JumpInfo();
        info.classname = LoginActivity.class.getSimpleName() + "";
        info.map = null;

        JumpActivity.getSingle().toJump(info);
    }
}
