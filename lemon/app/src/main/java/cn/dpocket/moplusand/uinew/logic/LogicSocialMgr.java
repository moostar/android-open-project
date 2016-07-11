package cn.dpocket.moplusand.uinew.logic;

import android.content.Intent;

import com.sina.weibo.sdk.api.share.BaseResponse;
import com.tencent.mm.sdk.openapi.BaseResp;

import cn.dpocket.moplusand.uinew.CONSTANTS;
import cn.dpocket.moplusand.uinew.tools.Utils;
import cn.dpocket.moplusand.uinew.logic.social.SocialQQ;
import cn.dpocket.moplusand.uinew.logic.social.SocialWeiXin;
import cn.dpocket.moplusand.uinew.logic.social.SocialWeibo;

/**
 * Created by Apple on 16/7/8.
 */
public class LogicSocialMgr {

    public interface ISocialNotitfy{
        /**
         * 登陆回调
         * @param result
         */
        public void Login(int result);

        /**
         * 分享回调
         * @param result
         */
        public void Share(int result);
    }


    public static final int LOGIN_QQ = 0;
    public static final int LOGIN_WEIXIN = LOGIN_QQ + 1;
    public static final int LOGIN_WEIBO = LOGIN_WEIXIN + 1;
    public static final int SHARE_QQ = LOGIN_WEIBO + 1;
    public static final int SHARE_QZONE = SHARE_QQ + 1;
    public static final int SHARE_WXTIMELINE = SHARE_QZONE + 1;//朋友圈
    public static final int SHARE_WXSESSION = SHARE_WXTIMELINE + 1;//好友
    public static final int SHARE_WB = SHARE_WXSESSION + 1;
    public static final int ACT_QQ = SHARE_WB + 1;
    public static final int ACT_WEIXIN = ACT_QQ + 1;
    public static final int ACT_WEIBO = ACT_WEIXIN + 1;





    private static LogicSocialMgr mgr = new LogicSocialMgr();


    private SocialQQ socialQQ = new SocialQQ();
    private SocialWeibo socialWeibo = new SocialWeibo();
    private SocialWeiXin socialWeiXin = new SocialWeiXin();
    private int mCurrentActivityType = -1;


    private LogicSocialMgr(){
        socialQQ.setNotity(new SocialNotitfy());
        socialWeibo.setNotity(new SocialNotitfy());
        socialWeiXin.setNotity(new SocialNotitfy());
    };

    public static LogicSocialMgr getSingle(){return  mgr ;}

    /**
     * 在界面返回的时候需要调用
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(mCurrentActivityType == ACT_QQ)
            socialQQ.onActivityResult(requestCode,resultCode,data);
        if(mCurrentActivityType == ACT_WEIXIN)
            socialWeiXin.onActivityResult(requestCode,resultCode,data);
        if(mCurrentActivityType == ACT_WEIBO)
            socialWeibo.onActivityResult(requestCode,resultCode,data);
    }

    /**
     * 登陆
     * @param type
     */
    public void login(int type){
        switch (type){
            case LOGIN_QQ:
                mCurrentActivityType = ACT_QQ;
                socialQQ.Login();
                break;
            case LOGIN_WEIBO:
                mCurrentActivityType = ACT_WEIBO;
                socialWeibo.Login();
                break;
            case LOGIN_WEIXIN:
                mCurrentActivityType = ACT_WEIXIN;
                socialWeiXin.Login();
                break;
            default:
                break;
        }
    }

    /**
     * 分享
     * @param type
     */
    public void share(int type){
        switch(type){
            case SHARE_QQ:
                mCurrentActivityType = ACT_QQ;
                socialQQ.Share(null);
                break;
            case SHARE_QZONE:
                mCurrentActivityType = ACT_QQ;
                socialQQ.Share(null);
                break;
            case SHARE_WXSESSION:
                mCurrentActivityType = ACT_WEIXIN;
                socialWeiXin.Share(null);
                break;
            case SHARE_WXTIMELINE:
                mCurrentActivityType = ACT_WEIXIN;
                socialWeiXin.Share(null);
                break;
            case SHARE_WB:
                mCurrentActivityType = ACT_WEIBO;
                socialWeibo.Share(null);
                break;
            default:
                break;
        }
    }

    /**
     * 微信返回数据
     * @param resp
     */
    public void onWeixinResponse(BaseResp resp){
        socialWeiXin.toBaseResp(resp);
    }

    /**
     * 微博返回数据
     * @param resp
     */
    public void onWeiBoResponse(BaseResponse resp){
        socialWeibo.toBaseResp(resp);
    }


    public class SocialNotitfy implements ISocialNotitfy{
        @Override
        public void Login(int result) {
            if(result == CONSTANTS.RESULT_CANCEL){
                Utils.ShowToast("cancel");
            }
            else if(result == CONSTANTS.RESULT_SUCCESS){
                Utils.ShowToast("success");
            }
            else if(result == CONSTANTS.RESULT_FAIL){
                Utils.ShowToast("fail");
            }
        }

        @Override
        public void Share(int result) {

        }
    }
}
