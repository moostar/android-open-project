package cn.dpocket.moplusand.uinew.ui.wbapi;

import android.content.Intent;
import android.os.Bundle;

import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

import cn.dpocket.moplusand.uinew.CONSTANTS;
import cn.dpocket.moplusand.uinew.logic.LogicSocialMgr;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;

/**
 * Created by Apple on 16/7/11.
 */
public class WBShareActivity extends BaseActivity implements IWeiboHandler.Response {


//    private final String APP_KEY = "4007297864";
    /** 微博微博分享接口实例 */
    private IWeiboShareAPI mWeiboShareAPI = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建微博分享接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, CONSTANTS.APPKEY_FOR_WEIBO);

        // 注册第三方应用到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
        // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
        // NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
        mWeiboShareAPI.registerApp();

        // 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        // 失败返回 false，不调用上述回调
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }



    }

    /**
     * @see {@link WBShareActivity#onNewIntent}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }

    @Override
    public void onResponse(BaseResponse baseResp) {
        LogicSocialMgr.getSingle().onWeiBoResponse(baseResp);
    }
}
