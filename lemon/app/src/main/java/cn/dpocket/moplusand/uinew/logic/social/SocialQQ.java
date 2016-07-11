package cn.dpocket.moplusand.uinew.logic.social;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import cn.dpocket.moplusand.uinew.CONSTANTS;
import cn.dpocket.moplusand.uinew.LemonApp;
import cn.dpocket.moplusand.uinew.ui.core.CoreActivity;

/**
 * Created by Apple on 16/7/8.
 */
public class SocialQQ extends Socialbase{

    private static final String SCOPE = "all";

    public static final int LOGIN = 0;
    public static final int QQSHARE= LOGIN +1;
    public static final int QZONESHARE = QQSHARE + 1;
    public static final int USEINFO = QZONESHARE + 1;

    Tencent mTencent =null;
    @Override
    public void Login() {

        mTencent = Tencent.createInstance(CONSTANTS.APPID_FOR_QQ, LemonApp.getContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(CoreActivity.getSingle().getTopActivity(), SCOPE, new BaseUiListener(LOGIN));
        }
        else
        {
            mTencent.logout(CoreActivity.getSingle().getTopActivity());
            socialObs.Login(CONSTANTS.RESULT_FAIL);
        }
    }

    @Override
    public void Share(ShareObject obj) {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Constants.REQUEST_QZONE_SHARE)
            Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener(QZONESHARE));
        else if (requestCode == Constants.REQUEST_LOGIN ||
            requestCode == Constants.REQUEST_APPBAR)
            Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener(LOGIN));
        else if(requestCode == Constants.REQUEST_QQ_SHARE)
            Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener(QQSHARE));
    }

    @Override
    public void UserInfo() {
        if (mTencent != null && mTencent.isSessionValid()){
            UserInfo mInfo = new UserInfo(LemonApp.getContext(), mTencent.getQQToken());
            mInfo.getUserInfo(new BaseUiListener(USEINFO));
        }
    }


    private class BaseUiListener implements IUiListener {

        private int type = -1;
        public BaseUiListener(int type){
            this.type = type;
        }
        @Override
        public void onComplete(Object response) {

            if (null == response) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
            doComplete(type,jsonResponse);
        }
        @Override
        public void onError(UiError e) {
//            Toast.makeText(LemonApp.getContext(),"onError:" +"code:" + e.errorCode + ", msg:"
//                    + e.errorMessage + ", detail:" + e.errorDetail,Toast.LENGTH_SHORT).show();
            if(type == LOGIN)
                socialObs.Login(CONSTANTS.RESULT_FAIL);
        }
        @Override
        public void onCancel() {
//            Toast.makeText(LemonApp.getContext(),"onCancel"+ "",Toast.LENGTH_SHORT).show();
            if(type == LOGIN)
                socialObs.Login(CONSTANTS.RESULT_CANCEL);
        }
    }

    private void doComplete(int type, JSONObject jsonResponse){

        switch (type){
            case LOGIN:
                initOpenidAndToken(jsonResponse);
                socialObs.Login(CONSTANTS.RESULT_SUCCESS);
                break;
            case QQSHARE:
                break;
            case QZONESHARE:
                break;
            case USEINFO:
                break;
        }
    }

    private void shareToQzone () {
        //分享类型
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "摘要");//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "跳转URL");//必填
//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, "图片链接ArrayList");
        mTencent.shareToQzone(CoreActivity.getSingle().getTopActivity(), params, new BaseUiListener(QZONESHARE));
    }

    private void onClickShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用222222");
        mTencent.shareToQQ(CoreActivity.getSingle().getTopActivity(), params, new BaseUiListener(QQSHARE));
    }


    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }

//    IUiListener loginListener = new BaseUiListener() {
//        @Override
//        protected void doComplete(JSONObject values) {
////            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
//            initOpenidAndToken(values);
//            updateUserInfo();
////            updateLoginButton();
//        }
//    };
//
//
//
//    private void updateUserInfo() {
//        if (mTencent != null && mTencent.isSessionValid()) {
//            IUiListener listener = new IUiListener() {
//
//                @Override
//                public void onError(UiError e) {
//
//                }
//
//                @Override
//                public void onComplete(final Object response) {
//                    Message msg = new Message();
//                    msg.obj = response;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
//                    new Thread(){
//
//                        @Override
//                        public void run() {
//                            JSONObject json = (JSONObject)response;
//                            if(json.has("figureurl")){
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        }
//
//                    }.start();
//                }
//
//                @Override
//                public void onCancel() {
//
//                }
//            };
//            mInfo = new UserInfo(this, mTencent.getQQToken());
//            mInfo.getUserInfo(listener);
//
//        } else {
//            mUserInfo.setText("");
//            mUserInfo.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
//        }
//    }
//
//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                JSONObject response = (JSONObject) msg.obj;
//                if (response.has("nickname")) {
//                    try {
////                        mUserInfo.setVisibility(android.view.View.VISIBLE);
////                        mUserInfo.setText(response.getString("nickname"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }else if(msg.what == 1){
//            }
//        }
//
//    };
}
