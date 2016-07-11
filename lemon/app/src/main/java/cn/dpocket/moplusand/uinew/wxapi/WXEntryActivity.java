package cn.dpocket.moplusand.uinew.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.dpocket.moplusand.uinew.CONSTANTS;
import cn.dpocket.moplusand.uinew.logic.LogicSocialMgr;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;


public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, CONSTANTS.APPID_FOR_WEIXIN);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        LogicSocialMgr.getSingle().onWeixinResponse(resp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}