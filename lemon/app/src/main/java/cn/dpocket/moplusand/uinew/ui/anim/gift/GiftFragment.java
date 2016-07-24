/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.dpocket.moplusand.app.BaseFragment;
import cn.dpocket.moplusand.common.Constants;
import cn.dpocket.moplusand.common.ULog;
import cn.dpocket.moplusand.common.entity.PayWareInfo;
import cn.dpocket.moplusand.common.entity.PaymentChannel;
import cn.dpocket.moplusand.common.entity.UserInfo;
import cn.dpocket.moplusand.common.message.PackagePayOrder;
import cn.dpocket.moplusand.common.message.PackageVipPurchaseList;
import cn.dpocket.moplusand.common.message.iteminfo.GiftInfo;
import cn.dpocket.moplusand.logic.LogicAccountMgr;
import cn.dpocket.moplusand.logic.LogicMasterGiftListMgr;
import cn.dpocket.moplusand.logic.LogicPaymentManager;
import cn.dpocket.moplusand.logic.LogicUserActions;
import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.WndActivityManager;
import cn.dpocket.moplusand.uinew.adapter.DifferentViewViewFlowAdapter;
import cn.dpocket.moplusand.uinew.widget.CircleFlowIndicator;
import cn.dpocket.moplusand.uinew.widget.ViewFlow;
import cn.dpocket.moplusand.utils.FragmentUtils;
import cn.dpocket.moplusand.utils.ListUtils;
import cn.dpocket.moplusand.utils.MathExtend;

public class GiftFragment extends BaseFragment implements GiftAdapter.OnGiftChecked {
    // private String mUserName = null;
    private String uucid = null;
    private List<GiftInfo> mGiftList = null;

    // 获取礼物回调
    LogicMasterGiftListMgr.LogicMasterGiftListMgrObserver masterGiftListCallBack = null;
    // 用户操作回调
    LogicUserActionsCallBack userActionsCallBack = null;
    //获取ub数量回调
    LogicPaymentManager.LogicPaymentManagerObserver paymentManagerObserver = null;

    // 礼物
    GridView giftView = null;


    // 每页多少个
    final int expPageNum = 10;

    int userID;

    View tabView;
    ViewFlow mGridViewViewFlow;

    GiftFragmentInterface fragmentInterface;
    Button submit;

    RelativeLayout rlCountDown;
    TextView tvMoney, tvCountDown;
    ImageButton ibCountDown;


    ArrayList<GiftAdapter> giftAdapters = new ArrayList<>();

    DifferentViewViewFlowAdapter mChatViewFlowAdapter;

    CountDownTimer countDownTimer;

    private Integer checkedPosition;

    public GiftFragment() {
        // Required empty public constructor
    }

    public static GiftFragment newInstance(int send2UserID) {

        Bundle args = new Bundle();
        args.putInt("send2UserID", send2UserID);

        GiftFragment fragment = new GiftFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getInt("send2UserID");
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ULog.log("Zhao onAttach");
        if (activity instanceof GiftFragmentInterface) {
            fragmentInterface = (GiftFragmentInterface) activity;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ULog.log("Zhao onDetach");
    }

    @Override
    public void onResume() {
        super.onResume();
        regLogicCallBack();
    }


    @Override
    public void onStop() {
        super.onStop();
        removeLogicCallBack();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        reset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return getView(inflater, container, savedInstanceState);
    }

    // 注册回调
    public void regLogicCallBack() {
        if (null == masterGiftListCallBack) {
            masterGiftListCallBack = new LogicMasterGiftListCallBack();
        }
        LogicMasterGiftListMgr.getSingleton().setObserver(
                masterGiftListCallBack);
        if (null == userActionsCallBack) {
            userActionsCallBack = new LogicUserActionsCallBack();
        }
        LogicUserActions.getSingleton().setObserver(userActionsCallBack);
        if (null == paymentManagerObserver) {
            paymentManagerObserver = new LogicPaymentCallback();
        }
        LogicPaymentManager.getSingleton().setObserver(paymentManagerObserver);

    }

    // 删除回调
    public void removeLogicCallBack() {
        masterGiftListCallBack = null;
        userActionsCallBack = null;
        paymentManagerObserver = null;
        LogicUserActions.getSingleton().setObserver(userActionsCallBack);
        LogicMasterGiftListMgr.getSingleton().setObserver(
                masterGiftListCallBack);
        LogicPaymentManager.getSingleton().setObserver(paymentManagerObserver);
    }


    // 发起网络请求
    private void loadData() {
//        mNoticeText.setText(R.string.getwait_notice);
        mGiftList = LogicMasterGiftListMgr.getSingleton().getLocalMasterGiftLists();

        if (ListUtils.isNullOrEmpty(mGiftList)) {
            LogicMasterGiftListMgr.getSingleton().getMasterGiftLists();
        }

    }

    /**
     * 加载本地数据
     */
    private void initViewWithData() {
        mGiftList = LogicMasterGiftListMgr.getSingleton()
                .getLocalMasterGiftLists();
        if (ListUtils.isNullOrEmpty(mGiftList)) {
            loadData();
            return;
        }
        setAdapter();
//load大礼动画
//        for (GiftInfo giftInfo : mGiftList) {
//
//            if (!StringUtils.isEmptyOrNullOrNullStr(giftInfo.getAnimationUrl())) {
//                ULog.log("Zhao getAnimationUrl:" + giftInfo.getAnimationUrl());
//
//                LogicGiftAnimtionMgr.getSingleton().downLoadGiftAnim(giftInfo.getGiftId() + "", giftInfo.getAnimationUrl());
//            }
//        }


//        mGiftAdapter.notifyDataSetChanged();
        // startHandleImages();
        // 获取icon列表
//        mMoreLayout.setVisibility(View.GONE);
//        mProBar.setVisibility(View.GONE);
    }

    private void sendGift(boolean isQuiet) {
        GiftInfo giftInfo = getCheckedGift();

        LogicUserActions.SendGiftResult result = null;

        if (giftInfo == null) {
            return;
        }

        if (uucid != null) {
            result = LogicUserActions.getSingleton().sendGiftToResource(
                    userID, uucid, giftInfo);
        } else {
            result = (isQuiet ? LogicUserActions.getSingleton()
                    .sendGiftQuietly(userID, giftInfo) : LogicUserActions
                    .getSingleton().sendGift(userID, giftInfo));
        }
        if (result == LogicUserActions.SendGiftResult.NOMONEY) {
            // u币不足
//			dialog();
            // dialogDismiss();
            return;
        } else if (result == LogicUserActions.SendGiftResult.ERROR) {
            // }else if (result == SendGiftResult.ERROR && mDialog != null){
            // dialogDismiss();
        }

//        ((OnFragmentInteractionListener) getActivity()).onFragmentInteraction(null);

    }

    class LogicPaymentCallback implements LogicPaymentManager.LogicPaymentManagerObserver {

        @Override
        public void LogicPaymentManager_chargeArrayReadyObs(int result, PayWareInfo[] array) {

        }

        @Override
        public void LogicPaymentManager_payChargeCostReadyObs(int result, int costId) {

        }

        @Override
        public void LogicPaymentManager_getMyPointReadyObs(int result, String point) {
            if (result == Constants.RESULT_SUCCESS) {
                setUMoney();
            }
        }

        @Override
        public void LogicPaymentManager_getPayPackegeReadyObs(int result, PackagePayOrder.PayOrderResp resp) {

        }

        @Override
        public void LogicPaymentManager_getPaymentChannelArrayReadyObs(int result, int pkgId, PaymentChannel[] array, String arrset_id) {

        }

        @Override
        public void LogicPaymentManager_localChargeArrayReadyObs(PayWareInfo[] array) {

        }

        @Override
        public void LogicPaymentManager_vipchargeArrayReadyObs(int result, PackageVipPurchaseList.MemberPriceItem[] info) {

        }

        @Override
        public void LogicPaymentManager_localvipchargeArrayReadyObs(PackageVipPurchaseList.MemberPriceItem[] info) {

        }

        @Override
        public void LogicPaymentManager_todayCheckReadyObs() {

        }
    }


    // 用户操作回调
    class LogicUserActionsCallBack implements LogicUserActions.LogicUserActionsObserver {

        @Override
        public void LogicUserAction_BlockOrUnblcokObs(int result, int userId,
                                                      byte type) {

        }

        @Override
        public void LogicUserAction_LikeOrUnLikeObs(int result, int userId,
                                                    byte type) {

        }

        @Override
        public void LogicUserAction_addPhotoObs(int result, boolean isHead,
                                                String photoId) {

        }

        @Override
        public void LogicUserAction_deletePhotoObs(int result, int photoId) {

        }

        @Override
        public void LogicUserAction_praisePhotoObs(int result, String photoId,
                                                   String rankNumber) {

        }

        @Override
        public void LogicUserAction_updateUserInfoObs(int result) {

        }

        @Override
        public void LogicUserAction_sendGiftObs(int result) {
            // dialogDismiss();
            if (result == Constants.RESULT_SUCCESS) {
                // 显示礼物动画，完成后，关闭页面。
                // 礼物动画
//                Bitmap bmp = LogicHttpImageMgr.getSingleton()
//                        .getBitmapFromCache(
//                                LogicHttpImageMgr.convertImageIdToHttpUrl(
//                                        Constants.RESTYPE_IMAGE_GIFT, mGiftList
//                                                .get(realGiftIndex)
//                                                .getResourceid()
//                                                + ""), 0);


                setUMoney();
                fragmentInterface.whenGiftSent(result, null);
            } else {
//				Toast.makeText(getActivity(), R.string.retrysend,
//						Toast.LENGTH_LONG).show();
                fragmentInterface.whenGiftSent(result, null);
            }

        }

        @Override
        public void LogicHallMgr_GoToHallCheckSucess(int result) {

        }

        @Override
        public void LogicHallMgr_GoToHallCheckError(int result, String msg) {

        }

        @Override
        public void LogicUserAction_changeAddedPhotoName(String newSmallPath,
                                                         String newBigPath) {
        }

    }

    // private void dialogDismiss(){
    // if (mDialog != null && mDialog.isShowing()) {
    // mDialog.dismiss();
    // mDialog = null;
    // }
    // }

    // 礼物总表回调
    class LogicMasterGiftListCallBack implements LogicMasterGiftListMgr.LogicMasterGiftListMgrObserver {

        @Override
        public void LogicMasterGiftListMgr_giftListReady(int result) {
            if (result == Constants.RESULT_SUCCESS) {
                initViewWithData();
            } else {
//                mProBar.setVisibility(View.GONE);
//                mNoticeText.setText(R.string.getgiftfailed);
            }
        }

    }


    //    private Dialog showDialog(final GiftInfo giftInfo) {
//        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
//        builder.setTitle(R.string.sendgift_choose_title);
//        builder.setMessage(getResources().getString(
//                R.string.sendgift_choose_text));
//        // builder.setCancelable(false);
//        builder.setPositiveButton(
//                getResources().getString(R.string.sendgift_choose_normal),
//                new AlertDialog.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        sendGift(giftInfo, false);
//                        dialog.dismiss();
//                    }
//                });
//        builder.setNegativeButton(
//                getResources().getString(R.string.sendgift_choose_quiet),
//                new AlertDialog.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        sendGift(giftInfo, true);
//                        dialog.dismiss();
//                    }
//                });
//        Dialog dialog = builder.create();
//        dialog.show();
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }


    private void setAdapter() {
        int expColumnNum = 5;

        GiftAdapter.setGiftList(mGiftList);

        int pages = (mGiftList.size() + expPageNum - 1) / expPageNum;

        mChatViewFlowAdapter = new DifferentViewViewFlowAdapter();

        CircleFlowIndicator indic = (CircleFlowIndicator) tabView
                .findViewById(R.id.circleFlowIndicator);
        indic.setRadius(2.5f);
        indic.setSelfSeparation(2.0f);
        mGridViewViewFlow.setFlowIndicator(indic);

        for (int i = 0; i < pages; i++) {
            final int currPage = i;

            View view = getActivity().getLayoutInflater().inflate(R.layout.include_gridview,
                    null);
            final GridView chat_gridview = (GridView) view
                    .findViewById(R.id.gridView);
            chat_gridview.setNumColumns(expColumnNum);

            chat_gridview.setLongClickable(true);

            giftAdapters = new ArrayList<>();
            GiftAdapter mGiftAdapter = new GiftAdapter(getActivity(), i, expPageNum, this);

            chat_gridview.setAdapter(mGiftAdapter);
            giftAdapters.add(mGiftAdapter);

//            chat_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//                    int realGiftIndex = getRealGiftIndex(currPage, position);
//                    sendGift(mGiftList.get(realGiftIndex), false);
//                }
//            });
//            chat_gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//                @Override
//                public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                               int position, long id) {
//
//                    int realGiftIndex = getRealGiftIndex(currPage, position);
//                    showDialog(mGiftList.get(realGiftIndex));
//                    return true;
//                }
//
//            });


//
//            chat_gridview.setAdapter(new MoctionAdapter2(getActivity(), expList,
//                    i, expPageNum + 1, pages));

            mChatViewFlowAdapter.AddView(view);
//            if (null != expItemClick) {
//                chat_gridview.setOnItemClickListener(expItemClick);
//            }
        }


        mGridViewViewFlow.setAdapter(mChatViewFlowAdapter);
        mGridViewViewFlow.setSelection(0);
        mGridViewViewFlow.postInvalidate();
    }

    public View getView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {

        tabView = inflater.inflate(R.layout.fragment_gift_flowview, container, false);

        mGridViewViewFlow = (ViewFlow) tabView.findViewById(R.id.uviewflow);
        submit = (Button) tabView.findViewById(R.id.submit);
        tvMoney = (TextView) tabView.findViewById(R.id.tvMoney);
        tvMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WndActivityManager.gotoActivity(WndActivityManager.ub);
            }
        });

        rlCountDown = (RelativeLayout) tabView.findViewById(R.id.rlCountDown);
        tvCountDown = (TextView) tabView.findViewById(R.id.tvCountDown);
        ibCountDown = (ImageButton) tabView.findViewById(R.id.ibCountDown);

        View.OnTouchListener doNothing = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        };
        tabView.findViewById(R.id.rl).setOnTouchListener(doNothing);
        tabView.findViewById(R.id.circleFlowIndicator).setOnTouchListener(doNothing);

        ibCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountDown();

                sendGift(false);
            }
        });

        setUMoney();
        return tabView;
    }

    private void setUMoney() {
        UserInfo userInfo = LogicAccountMgr.getSingleton().getLocalMyUserInfo();
        if (userInfo != null) {
            if (TextUtils.isEmpty(userInfo.getPoint())) {
                LogicPaymentManager.getSingleton().getMyPoints();
            } else if (tvMoney != null) {
                tvMoney.setText(userInfo.getPoint());
            }
        }
    }


    private int getRealGiftIndex(int page, int position) {
        int realGiftIndex = position + (page * expPageNum);

        ULog.log("realGiftIndex:" + realGiftIndex);

        return realGiftIndex;
    }


    @Override
    public void onCheckChanged(int position, int page, boolean isChecked) {
        notifyDataSetChanged();
    }

    @Override
    public void onChecked(int position, int page) {
        checkedPosition = getRealGiftIndex(page, position);

        showSubmit(true);
    }


    @Override
    public void onNoneChecked() {
        checkedPosition = null;
        showSubmit(false);
    }

    public interface GiftFragmentInterface {
        void removeGiftFragment();

        //当礼物送完之后
        void whenGiftSent(int result, Bitmap bmp);
    }


    private void notifyDataSetChanged() {

        mChatViewFlowAdapter.notifyDataSetChanged();

        for (GiftAdapter giftAdapter : giftAdapters) {
            giftAdapter.notifyDataSetChanged();
        }
    }


    private GiftInfo getCheckedGift() {
        if (ListUtils.isNullOrEmpty(GiftAdapter.getGiftList())
                || checkedPosition == null) {
            return null;
        }
        return GiftAdapter.getGiftList().get(checkedPosition);
    }


    private void startCountDown() {
        if (countDownTimer != null)
            countDownTimer.cancel();


        countDownTimer = new CountDownTimer(4000, 100) {
            public void onTick(long millisUntilFinished) {
                if (FragmentUtils.isAvailable(GiftFragment.this)) {
                    String str = String.format(getResources()
                                    .getString(R.string.gift_dribble_click_countdown)
                            , MathExtend.divide(millisUntilFinished, 1000, 2) + "");
                    tvCountDown.setText(str);
                }
            }

            public void onFinish() {
                if (FragmentUtils.isAvailable(GiftFragment.this)) {
                    rlCountDown.setVisibility(View.GONE);
                    showSubmit(checkedPosition != null);
                }
            }
        }.start();
    }

    /**
     * 显示确定按钮
     *
     * @param isClickable
     */
    private void showSubmit(boolean isClickable) {

        if (!FragmentUtils.isAvailable(this)) {
            return;
        }
        if (rlCountDown != null && submit != null) {
            rlCountDown.setVisibility(View.GONE);

            submit.setVisibility(View.VISIBLE);
            submit.setClickable(isClickable);
            if (isClickable) {
                submit.setBackgroundResource(R.drawable.bg_btn_yellow_right_angle);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendGift(false);

                        if (!ListUtils.isNullOrEmpty(GiftAdapter.getGiftList())
                                && "0".equals(GiftAdapter.getGiftList().get(checkedPosition).getCombo())) {
                            //非连击直接返回
                            return;
                        }
                        submit.setVisibility(View.GONE);
                        rlCountDown.setVisibility(View.VISIBLE);
                        startCountDown();
                    }
                });
            } else {
                submit.setBackgroundResource(R.drawable.bg_btn_gray_right_angle);
                submit.setOnClickListener(null);
            }
        }

    }

    /**
     * 重置GiftFragment的界面
     */
    public void reset() {
        if (!ListUtils.isNullOrEmpty(GiftAdapter.getGiftList())) {
            for (int i = 0; i < GiftAdapter.getGiftList().size(); i++) {
                GiftAdapter.getGiftList().get(i).setChecked(false);
            }
        }
        showSubmit(false);
        initViewWithData();
    }


}
