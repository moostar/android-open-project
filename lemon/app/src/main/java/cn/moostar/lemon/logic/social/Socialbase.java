package cn.moostar.lemon.logic.social;

/**
 * Created by Apple on 16/7/8.
 */

public interface Socialbase {

    public class ShareObject{
        public int type;

    }

    public class AuthObject{

    }



    public void Login(AuthObject obj);

    public void Share(ShareObject obj);
}
