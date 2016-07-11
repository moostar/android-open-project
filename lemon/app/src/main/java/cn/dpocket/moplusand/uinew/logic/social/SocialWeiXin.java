package cn.dpocket.moplusand.uinew.logic.social;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXVideoObject;
import com.tencent.mm.sdk.platformtools.Util;

import java.net.URL;

import cn.dpocket.moplusand.uinew.CONSTANTS;
import cn.dpocket.moplusand.uinew.LemonApp;

/**
 * Created by Apple on 16/7/8.
 */
public class SocialWeiXin extends Socialbase{


//    String APP_PAYID = "wxd4b23d766a1fdece";
//    String APP_SECRECT = "010601f4aa59c6473cd0a2e75c805ead";

    private IWXAPI api = null;
    @Override
    public void Login() {

        api = WXAPIFactory.createWXAPI(LemonApp.getContext(), CONSTANTS.APPID_FOR_WEIXIN);


        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @Override
    public void Share(ShareObject obj) {
        api = WXAPIFactory.createWXAPI(LemonApp.getContext(), CONSTANTS.APPID_FOR_WEIXIN);
        sendVideo(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

    }

    @Override
    public void UserInfo() {

    }

    public void toBaseResp(BaseResp resp){

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {

                }
                else if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {//分享成功,上报给服务器

                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:

                if(resp.getType() == ConstantsAPI.COMMAND_SENDAUTH){
                    socialObs.Login(CONSTANTS.RESULT_CANCEL);
                }

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if(resp.getType() == ConstantsAPI.COMMAND_SENDAUTH){
                    socialObs.Login(CONSTANTS.RESULT_FAIL);
                }
                break;
            default:
                if(resp.getType() == ConstantsAPI.COMMAND_SENDAUTH){
                    socialObs.Login(CONSTANTS.RESULT_FAIL);
                }
                break;
        }
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    private void sendText(boolean timeLine){
        WXTextObject textObj = new WXTextObject();
        textObj.text = "text";


        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = "text description";


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = timeLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;


        api.sendReq(req);
    }
    private void sendImage(boolean timeline) {

        String url = "http://weixin.qq.com/zh_CN/htmledition/images/weixin/weixin_logo0d1938.png";
        int THUMB_SIZE = 150;
        try {
            WXImageObject imgObj = new WXImageObject();
            imgObj.imageUrl = url;

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = imgObj;

            Bitmap bmp = BitmapFactory.decodeStream(new URL(url).openStream());
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
            bmp.recycle();
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("img");
            req.message = msg;
            req.scene = timeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
            api.sendReq(req);
        }
        catch (Exception e){

        }
    }

    private void sendVideo(boolean timeline){
        WXVideoObject video = new WXVideoObject();
        video.videoLowBandUrl = "http://www.qq.com";

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = "Video Title";
        msg.description = "Video Description";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = timeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
}
