package cn.moostar.lemon.logic;

import cn.moostar.lemon.logic.social.SocialQQ;
import cn.moostar.lemon.logic.social.SocialWeiXin;
import cn.moostar.lemon.logic.social.SocialWeibo;

/**
 * Created by Apple on 16/7/8.
 */
public class LogicSocialMgr {


    private static LogicSocialMgr mgr = new LogicSocialMgr();


    private SocialQQ socialQQ = new SocialQQ();
    private SocialWeibo socialWeibo = new SocialWeibo();
    private SocialWeiXin socialWeiXin = new SocialWeiXin();

    private LogicSocialMgr(){};

    public static LogicSocialMgr getSingle(){return  mgr ;}

    public SocialQQ getSocialQQ(){
        return socialQQ;
    }

    public SocialWeiXin getSocialWeiXin(){
        return socialWeiXin;
    }

    public SocialWeibo getSocialWeibo(){
        return socialWeibo;
    }
}
