package cn.moostar.lemon.logic.social;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.openapi.RefreshTokenApi;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.moostar.lemon.LemonApp;
import cn.moostar.lemon.R;
import cn.moostar.lemon.ui.core.CoreActivity;

/**
 * Created by Apple on 16/7/8.
 */
public class SocialWeibo implements Socialbase,IWeiboHandler.Response{



    private final String APP_KEY = "4007297864";
    //    private final String CONSUMER_SECRET = "6a3111383c98a154e73e7f7e65eafaf1";
    private final String REDIRECT_URL = "http://www.youja.cn/sina_oauth2";
    public  final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";

    AuthInfo mAuthInfo = null;
    SsoHandler mSsoHandler = null;

    private Oauth2AccessToken mAccessToken = null;



    @Override
    public void Login() {
        mAuthInfo = new AuthInfo(LemonApp.getContext(), APP_KEY, REDIRECT_URL, SCOPE);
        mSsoHandler = new SsoHandler(CoreActivity.getSingle().getTopActivity(), mAuthInfo);

        mSsoHandler.authorize(new AuthListener());
    }

    @Override
    public void Share(ShareObject obj) {

    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息
            String  phoneNum =  mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);

                // 保存 Token 到 SharedPreferences
//                AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);

                Toast.makeText(LemonApp.getContext(),
                        "auth_success", Toast.LENGTH_SHORT).show();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = "auth_failed";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(LemonApp.getContext(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(LemonApp.getContext(),
                    "weibosdk_demo_toast_auth_canceled", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LemonApp.getContext(),
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }



    /** 微博微博分享接口实例 */
    private IWeiboShareAPI mWeiboShareAPI = null;

    private void refreshTokenRequest() {
        Oauth2AccessToken   token  =  mAccessToken;//AccessTokenKeeper.readAccessToken(WBOpenAPIActivity.this);
        RefreshTokenApi.create(LemonApp.getContext()).refreshToken(
                APP_KEY, token.getRefreshToken(), new RequestListener() {

                    @Override
                    public void onWeiboException( WeiboException arg0 ) {
                        Toast.makeText(LemonApp.getContext(), "RefreshToken Result : " + arg0.getMessage(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete( String arg0 ) {
                        Toast.makeText(LemonApp.getContext(), "RefreshToken Result : " + arg0, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void initActivity() {

        // 创建微博分享接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(CoreActivity.getSingle().getTopActivity(), APP_KEY);

        // 注册第三方应用到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
        // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
        // NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
        mWeiboShareAPI.registerApp();

        // 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        // 失败返回 false，不调用上述回调
//        if (savedInstanceState != null)
        {
            mWeiboShareAPI.handleWeiboResponse(CoreActivity.getSingle().getTopActivity().getIntent(), this);
        }
    }


    /**
     * 接收微客户端博请求的数据。
     * 当微博客户端唤起当前应用并进行分享时，该方法被调用。
     *
     * @param baseRequest 微博请求数据对象
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */
    @Override
    public void onResponse(BaseResponse baseResp) {
        if(baseResp!= null){
            switch (baseResp.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    Toast.makeText(LemonApp.getContext(), "share_success", Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    Toast.makeText(LemonApp.getContext(), "share_canceled", Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    Toast.makeText(LemonApp.getContext(),
                            "Error Message: " + baseResp.errMsg,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * @see {@link #sendMultiMessage} 或者 {@link #sendSingleMessage}
     */
    private void sendMessage(boolean hasText, boolean hasImage,
                             boolean hasWebpage, boolean hasMusic, boolean hasVideo, boolean hasVoice) {

//        if (mShareType == SHARE_CLIENT) {
//            if (mWeiboShareAPI.isWeiboAppSupportAPI()) {
//                int supportApi = mWeiboShareAPI.getWeiboAppSupportAPI();
//                if (supportApi >= 10351 /*ApiUtils.BUILD_INT_VER_2_2*/) {
//                    sendMultiMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo, hasVoice);
//                } else {
//                    sendSingleMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo/*, hasVoice*/);
//                }
//            } else {
//                Toast.makeText(this, R.string.weibosdk_demo_not_support_api_hint, Toast.LENGTH_SHORT).show();
//            }
//        }
//        else if (mShareType == SHARE_ALL_IN_ONE)
        {
            sendMultiMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo, hasVoice);
        }
    }
    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 注意：当 {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条消息，
     * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     *
     * @param hasText    分享的内容是否有文本
     * @param hasImage   分享的内容是否有图片
     * @param hasWebpage 分享的内容是否有网页
     * @param hasMusic   分享的内容是否有音乐
     * @param hasVideo   分享的内容是否有视频
     * @param hasVoice   分享的内容是否有声音
     */
    private void sendMultiMessage(boolean hasText, boolean hasImage, boolean hasWebpage,
                                  boolean hasMusic, boolean hasVideo, boolean hasVoice) {

        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMessage.textObject = getTextObj();
        }

        if (hasImage) {
            weiboMessage.imageObject = getImageObj();
        }

        // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj();
        }
        if (hasMusic) {
            weiboMessage.mediaObject = getMusicObj();
        }
        if (hasVideo) {
            weiboMessage.mediaObject = getVideoObj();
        }
        if (hasVoice) {
            weiboMessage.mediaObject = getVoiceObj();
        }

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
//        if (mShareType == SHARE_CLIENT) {
//            mWeiboShareAPI.sendRequest(WBShareActivity.this, request);
//        }
//        else if (mShareType == SHARE_ALL_IN_ONE)
        {
            AuthInfo authInfo = new AuthInfo(LemonApp.getContext(), APP_KEY, REDIRECT_URL, SCOPE);
//            Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());

            String token = "";
            if (mAccessToken != null) {
                token = mAccessToken.getToken();
            }
            mWeiboShareAPI.sendRequest(CoreActivity.getSingle().getTopActivity(), request, authInfo, token, new WeiboAuthListener() {

                @Override
                public void onWeiboException( WeiboException arg0 ) {
                }

                @Override
                public void onComplete( Bundle bundle ) {
                    // TODO Auto-generated method stub
                    Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
//                    AccessTokenKeeper.writeAccessToken(getApplicationContext(), newToken);
                    Toast.makeText(LemonApp.getContext(), "onAuthorizeComplete token = " + newToken.getToken(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancel() {
                }
            });
        }
    }
//    /**
//     * 第三方应用发送请求消息到微博，唤起微博分享界面。
//     * 当{@link IWeiboShareAPI#getWeiboAppSupportAPI()} < 10351 时，只支持分享单条消息，即
//     * 文本、图片、网页、音乐、视频中的一种，不支持Voice消息。
//     *
//     * @param hasText    分享的内容是否有文本
//     * @param hasImage   分享的内容是否有图片
//     * @param hasWebpage 分享的内容是否有网页
//     * @param hasMusic   分享的内容是否有音乐
//     * @param hasVideo   分享的内容是否有视频
//     */
//    private void sendSingleMessage(boolean hasText, boolean hasImage, boolean hasWebpage,
//                                   boolean hasMusic, boolean hasVideo/*, boolean hasVoice*/) {
//
//        // 1. 初始化微博的分享消息
//        // 用户可以分享文本、图片、网页、音乐、视频中的一种
//        WeiboMessage weiboMessage = new WeiboMessage();
//        if (hasText) {
//            weiboMessage.mediaObject = getTextObj();
//        }
//        if (hasImage) {
//            weiboMessage.mediaObject = getImageObj();
//        }
//        if (hasWebpage) {
//            weiboMessage.mediaObject = getWebpageObj();
//        }
//        if (hasMusic) {
//            weiboMessage.mediaObject = getMusicObj();
//        }
//        if (hasVideo) {
//            weiboMessage.mediaObject = getVideoObj();
//        }
//        /*if (hasVoice) {
//            weiboMessage.mediaObject = getVoiceObj();
//        }*/
//
//        // 2. 初始化从第三方到微博的消息请求
//        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
//        // 用transaction唯一标识一个请求
//        request.transaction = String.valueOf(System.currentTimeMillis());
//        request.message = weiboMessage;
//
//        // 3. 发送请求消息到微博，唤起微博分享界面
//        mWeiboShareAPI.sendRequest(WBShareActivity.this, request);
//    }

    /**
     * 获取分享的文本模板。
     *
     * @return 分享的文本模板
     */
    private String getSharedText() {

        return "shareText";
    }

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) mImageView.getDrawable();
        //设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        Bitmap  bitmap = BitmapFactory.decodeResource(CoreActivity.getSingle().getTopActivity().getResources(), R.drawable.login_account_weibo);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = "title";//mShareWebPageView.getTitle();
        mediaObject.description = "description";//mShareWebPageView.getShareDesc();

        Bitmap  bitmap = BitmapFactory.decodeResource(CoreActivity.getSingle().getTopActivity().getResources(), R.drawable.login_account_weibo);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://www.baidu.com";//mShareWebPageView.getShareUrl();
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }

    /**
     * 创建多媒体（音乐）消息对象。
     *
     * @return 多媒体（音乐）消息对象。
     */
    private MusicObject getMusicObj() {
        // 创建媒体消息
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        musicObject.title = "title";//mShareWebPageView.getTitle();
        musicObject.description = "description";//mShareWebPageView.getShareDesc();

        Bitmap  bitmap = BitmapFactory.decodeResource(CoreActivity.getSingle().getTopActivity().getResources(), R.drawable.login_account_weibo);



        // 设置 Bitmap 类型的图片到视频对象里        设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        musicObject.setThumbImage(bitmap);
        musicObject.actionUrl = "http://www.baidu.com";//mShareMusicView.getShareUrl();
        musicObject.dataUrl = "www.weibo.com";
        musicObject.dataHdUrl = "www.weibo.com";
        musicObject.duration = 10;
        musicObject.defaultText = "Music 默认文案";
        return musicObject;
    }

    /**
     * 创建多媒体（视频）消息对象。
     *
     * @return 多媒体（视频）消息对象。
     */
    private VideoObject getVideoObj() {
        // 创建媒体消息
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = "title";//mShareVideoView.getTitle();
        videoObject.description = "descritpion";//mShareVideoView.getShareDesc();
        Bitmap  bitmap = BitmapFactory.decodeResource(CoreActivity.getSingle().getTopActivity().getResources(), R.drawable.login_account_weibo);
        // 设置 Bitmap 类型的图片到视频对象里  设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。


        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
            System.out.println("kkkkkkk    size  "+ os.toByteArray().length );
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("Weibo.BaseMediaObject", "put thumb failed");
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        videoObject.setThumbImage(bitmap);
        videoObject.actionUrl = "http://www.baidu.com";//mShareVideoView.getShareUrl();
        videoObject.dataUrl = "www.weibo.com";
        videoObject.dataHdUrl = "www.weibo.com";
        videoObject.duration = 10;
        videoObject.defaultText = "Vedio 默认文案";
        return videoObject;
    }

    /**
     * 创建多媒体（音频）消息对象。
     *
     * @return 多媒体（音乐）消息对象。
     */
    private VoiceObject getVoiceObj() {
        // 创建媒体消息
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.identify = Utility.generateGUID();
        voiceObject.title = "title";//mShareVoiceView.getTitle();
        voiceObject.description = "description";//mShareVoiceView.getShareDesc();
        Bitmap  bitmap = BitmapFactory.decodeResource(CoreActivity.getSingle().getTopActivity().getResources(), R.drawable.login_account_weibo);
        // 设置 Bitmap 类型的图片到视频对象里      设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        voiceObject.setThumbImage(bitmap);
        voiceObject.actionUrl = "http://www.baidu.com";//mShareVoiceView.getShareUrl();
        voiceObject.dataUrl = "www.weibo.com";
        voiceObject.dataHdUrl = "www.weibo.com";
        voiceObject.duration = 10;
        voiceObject.defaultText = "Voice 默认文案";
        return voiceObject;
    }
}
