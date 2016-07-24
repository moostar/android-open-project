/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.dpocket.moplusand.logic.LogicChatroom;
import cn.dpocket.moplusand.logic.message.UMessage;
import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.utils.ResourceUtils;

/**
 * Created by Zhao on 16/5/12.
 * <p/>
 * 连击礼物动画视图
 */
public class GiftDribbleView extends LinearLayout {

    private Context context;
    private final int ITEM_MAX_NUM = 2;

    private GiftMessage.GiftMessageCall call;
    OnGiftDrebbleViewPlayingListener onGiftDrebbleViewPlayingListener;

    private List<GiftDribbleItemView> giftHolders = new ArrayList<GiftDribbleItemView>();

    public GiftDribbleView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public GiftDribbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public GiftDribbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public GiftDribbleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }


    private void initView() {


        View view = LayoutInflater.from(context).inflate(R.layout.layout_gift_dribble_view, null);

        if (view == null) {
            return;
        }

        for (int i = 0; i < ITEM_MAX_NUM; i++) {
            int widgetId = ResourceUtils.getViewId(context, "gift" + i);
            GiftDribbleItemView giftItemView = (GiftDribbleItemView) view.findViewById(widgetId);
            giftHolders.add(giftItemView);
        }

        addView(view);

    }


    private GiftMessage.GiftMessageCall getCall() {
        if (call != null)
            return call;
        else {
            call = new GiftMessage.GiftMessageCall() {
                @Override
                public void update() {
                    updateGiftView();
                }
            };
        }
        return call;
    }

    public void setGift(UMessage message) {
        List<GiftMessage> giftMsgList = LogicChatroom.getSingleton().getGiftMsgList();
        if (giftMsgList.size() == 0) {
            GiftMessage lastMessage = new GiftMessage();
            lastMessage.setMessageCall(getCall());
            lastMessage.setMsg(message);
            giftMsgList.add(lastMessage);
            lastMessage.startTimmer();
        } else {
            //查找有没有相同的对象
            GiftMessage sameMessage = null;
            for (int i = 0; i < giftMsgList.size(); i++) {
                GiftMessage gm = giftMsgList.get(i);
                if (gm.equal(message)) {
                    sameMessage = gm;
                }
            }
            //GiftMessage lastMessage= giftMsgList.get(giftMsgList.size()-1);
            //if(lastMessage.equal(message)){
            if (sameMessage != null) {
                //+1
                sameMessage.setCount(sameMessage.getCount() + 1);
                sameMessage.resetTimmer();
            } else {
                if (giftMsgList.size() >= ITEM_MAX_NUM) {
                    if (giftMsgList.get(0) != null) {
                        call = null;
                        giftMsgList.get(0).setMessageCall(call);
                    }
                    giftMsgList.remove(0);
                }
                GiftMessage msg = new GiftMessage();

                msg.setMessageCall(getCall());
                msg.setMsg(message);
                giftMsgList.add(msg);
                msg.startTimmer();
            }
        }
        updateGiftView();
    }

    public void updateGiftView() {
        List<GiftMessage> giftMsgList = LogicChatroom.getSingleton().getGiftMsgList();
        int giftCount = giftMsgList == null ? 0 : giftMsgList.size();

        for (int i = 0; i < giftHolders.size(); i++) {
            giftHolders.get(i).setVisibility(View.INVISIBLE);
        }
        //没数据,啥时候也不干
        if (giftCount == 0) {
            return;
        }

        for (int i = 0; i < giftCount; i++) {
            GiftMessage uMessage = giftMsgList.get(i);

            if (giftHolders.size() > i) {
                GiftDribbleItemView itemViewLast = giftHolders.get(i);
                itemViewLast.updateView(uMessage.getMsg());
                itemViewLast.numView.setNum(uMessage.getCount(), uMessage.isNumAnim());

                if (uMessage.isNumAnim()) {
                    uMessage.setIsNumAnim(false);

                    if (onGiftDrebbleViewPlayingListener != null) {
                        onGiftDrebbleViewPlayingListener.onPlaying(uMessage);
                    }
                }


            }

        }
    }

    public void clearView() {
        if (giftHolders != null) {
            giftHolders.clear();
        }
        removeAllViews();
    }

    public void setOnGiftDrebbleViewPlayingListener(OnGiftDrebbleViewPlayingListener onGiftDrebbleViewPlayingListener) {
        this.onGiftDrebbleViewPlayingListener = onGiftDrebbleViewPlayingListener;
    }

    public interface OnGiftDrebbleViewPlayingListener {

        void onPlaying(GiftMessage message);
    }


}
