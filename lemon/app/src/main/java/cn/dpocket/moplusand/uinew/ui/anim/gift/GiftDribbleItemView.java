/*
 * Copyright (c) 2016. 
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.dpocket.moplusand.logic.LogicFileCacheMgr;
import cn.dpocket.moplusand.logic.LogicHttpImageMgr;
import cn.dpocket.moplusand.logic.message.MessageCenter;
import cn.dpocket.moplusand.logic.message.UMessage;
import cn.dpocket.moplusand.uinew.R;

/**
 * Created by Zhao on 16/5/12.
 * <p/>
 * 连击礼物动画视图
 */
public class GiftDribbleItemView extends LinearLayout {

    private Context context;

    public ImageView ivHeader;
    public ImageView ivGift;
    public TextView tvName;
    public TextView tvContent;
    public GiftDribbleNumView numView;

    private int arrow = 0;

    public GiftDribbleItemView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public GiftDribbleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GiftDribbleItemView);
        arrow = ta.getInteger(0,0);
        ta.recycle();
        initView();
    }

    public GiftDribbleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GiftDribbleItemView);
        arrow = ta.getInteger(0,0);
        ta.recycle();
        initView();
    }

    public GiftDribbleItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GiftDribbleItemView);
        arrow = ta.getInteger(0,0);
        ta.recycle();
        initView();
    }


    private void initView() {
        View view;
        if (arrow == 0){
            view = LayoutInflater.from(context).inflate(R.layout.layout_gift_dribble_view_item, null);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.layout_gift_dribble_view_item2, null);
        }
        if (view == null) {
            return;
        }

        ivHeader = (ImageView) view.findViewById(R.id.ivGiftHeader);
        ivGift = (ImageView) view.findViewById(R.id.ivGiftImg);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        numView = (GiftDribbleNumView) view.findViewById(R.id.numView);

        addView(view);
    }

    public void updateView(UMessage message) {
        if (message == null) {
            return;
        }

        if (message.getSender() != null) {
            LogicHttpImageMgr.getSingleton().appendCircleImageWithStroke(ivHeader, message.getSender().avatarId, R.drawable.def_headicon, 0);
            tvName.setText(message.getSender().nickname);
        }
        if (message.getType() == MessageCenter.MSGTYPE_GIFT) {
            LogicHttpImageMgr.getSingleton().appendImage(ivGift, message.getThumbnailUrl(), R.drawable.nothing, null, 0, LogicFileCacheMgr.TYPE_FILECACHE_GIFT);
        } else if (message.getType() == MessageCenter.MSGTYPE_SHOWACTION) {
            String iconUrl = null;
            if (message.getLittleAction() != null) {
                iconUrl = message.getLittleAction().url;
            }
            LogicHttpImageMgr.getSingleton().appendImage(ivGift, iconUrl, R.drawable.nothing, null, 0, LogicFileCacheMgr.TYPE_FILECACHE_GIFT);
        }
        tvContent.setText(message.getContent());
        int maxWidth = 160;
        if (context instanceof Activity) {
            maxWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth()  / 2;
        }

        tvContent.setMaxWidth(maxWidth);

        this.setVisibility(View.VISIBLE);
    }


}
