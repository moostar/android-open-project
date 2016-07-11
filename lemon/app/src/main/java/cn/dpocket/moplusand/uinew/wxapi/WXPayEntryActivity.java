package cn.dpocket.moplusand.uinew.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.uiweixinpay);
        
//    	api = WXAPIFactory.createWXAPI(this, CONSTANTS.APP_PAYID);
//        api.handleIntent(getIntent(), this);
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

//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
////			AlertDialog.Builder builder = new AlertDialog.Builder(this);
////			builder.setTitle(R.string.app_tip);
////			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
////			builder.show();
//			String error = "";
//
//		    if(resp.errCode == BaseResp.ErrCode.ERR_OK)
//		    {
//		    	error = getResources().getString(R.string.pay_succ);
//		    	Toast.makeText(MoplusApp.getCtx(), R.string.pay_succ, Toast.LENGTH_LONG).show();
//		    }
//		    else if(resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL)
//		    {
//		    	error = getResources().getString(R.string.pay_cancel);
//		    	Toast.makeText(MoplusApp.getCtx(), R.string.pay_cancel, Toast.LENGTH_LONG).show();
//		    }
//		    else
//		    {
//		    	error = getResources().getString(R.string.upluspayfail);
//		    	Toast.makeText(MoplusApp.getCtx(), R.string.upluspayfail, Toast.LENGTH_LONG).show();
//		    }
//
//			LogicPaymentManager.getSingleton().getMyPoints();
//			String order = LogicMobilePaymentMgr.getSingleton().getMicroMsg().getOrderNo();
//			LogicPaymentManager.getSingleton().tellPayResultToServer(order, resp.errCode + "",
//					LogicPaymentManager.WXPAY, error);
//		}
//		finish();
	}
}