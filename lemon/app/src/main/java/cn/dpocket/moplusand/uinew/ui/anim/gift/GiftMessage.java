/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

/**
 * 消息结构
 */
package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.os.Handler;
import android.os.Message;

import java.io.Serializable;
import java.util.List;

import cn.dpocket.moplusand.logic.LogicChatroom;
import cn.dpocket.moplusand.logic.message.UMessage;
import cn.dpocket.moplusand.utils.interfage.IEqual;

/**
 * @author zubin
 */
public class GiftMessage implements Serializable, IEqual {


    private static final long serialVersionUID = -7508554318388739745L;
    private int count = 1;
    private UMessage msg;

    //是否要播放数字动画
    private boolean isNumAnim = false;

    /**
     * 数字是否会发生变化
     * @return
     */
    public boolean isNumAnim() {
        return isNumAnim;
    }

    public void setIsNumAnim(boolean isNumAnim) {
        this.isNumAnim = isNumAnim;
    }

    private GiftMessageCall messageCall;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UMessage getMsg() {
        return msg;
    }

    public void setMsg(UMessage msg) {
        isNumAnim = true;
        this.msg = msg;
    }

    public interface GiftMessageCall {
        void update();
    }

    public GiftMessageCall getMessageCall() {
        return messageCall;
    }

    public void setMessageCall(GiftMessageCall messageCall) {
        this.messageCall = messageCall;
    }

    @Override
    public boolean equal(Object o) {
        if (o != null &&
                o instanceof UMessage) {
            UMessage message = (UMessage) o;
            //ULog.log( "-------o.getMessageFlag():" + o.getMessageFlag());
            //ULog.log( "-------thisMessageFlag:" + thisMessageFlag);
            return (message.getSender() != null
                    && message.getSender().userId != null
                    && message.getSender().userId.equals(this.msg.getSender().userId)
                    && message.getThumbnailUrl() != null
                    && message.getThumbnailUrl().equals(this.msg.getThumbnailUrl()));
        }
        return false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                GiftMessage message2 = (GiftMessage) msg.obj;
                //从列表中吧自己删除
                List<GiftMessage> giftMsgList = LogicChatroom.getSingleton().getGiftMsgList();
                if (giftMsgList.contains(message2)) {
                    giftMsgList.remove(message2);
                    if (messageCall != null) {
                        messageCall.update();
                    }
                }
            }
        }
    };

    public void startTimmer() {
        Message msg = handler.obtainMessage();
        msg.obj = this;
        handler.sendMessageDelayed(msg, 8000);
    }

    public void resetTimmer() {
        isNumAnim = true;
        handler.removeMessages(0);
        Message msg = handler.obtainMessage();
        msg.obj = this;
        handler.sendMessageDelayed(msg, 8000);
    }


}
