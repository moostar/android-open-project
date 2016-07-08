package cn.moostar.lemon.logic.social;

import android.content.Intent;

/**
 * Created by Apple on 16/7/8.
 */

public interface Socialbase {

    public class ShareObject{
        public int type;

    }

    public class AuthObject{

    }



    public void Login();

    public void Share(ShareObject obj);

    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
