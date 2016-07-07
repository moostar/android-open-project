package cn.moostar.lemon.ui.core.jump;

import cn.moostar.lemon.ui.LoginActivity;
import cn.moostar.lemon.ui.core.JumpActivity;

/**
 * Created by Apple on 16/7/7.
 */
public class ToLogin {

    public static void login(){
        JumpActivity.JumpInfo info = new JumpActivity.JumpInfo();
        info.classname = LoginActivity.class + "";
        info.map = null;

        JumpActivity.getSingle().toJump(info);
    }
}
