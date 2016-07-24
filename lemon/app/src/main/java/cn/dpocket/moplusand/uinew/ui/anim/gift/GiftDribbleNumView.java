/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.utils.ResourceUtils;

/**
 * Created by Zhao on 16/5/12.
 * <p/>
 * 连击礼物动画视图
 */
public class GiftDribbleNumView extends LinearLayout {
    private ImageView ivX;
    private LinearLayout ivNumPanel;

    private List<ImageView> numViewList=new ArrayList<ImageView>();

    public GiftDribbleNumView(Context context) {
        super(context);
        initView();
    }

    public GiftDribbleNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GiftDribbleNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public GiftDribbleNumView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {

        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_gift_dribble_num_view, null);
        if (view == null) {
            return;
        }
        ivX = (ImageView) view.findViewById(R.id.ivNum0);
        ivNumPanel= (LinearLayout) view.findViewById(R.id.ivNumPanel);
        addView(view);

    }

    public void setNum(int num,boolean isAnim) {
        if (ivX != null && num > 0) {
            ivX.setVisibility(VISIBLE);
        }

        String numStr = (num + "");

        //先隐藏一下
        for (int i=0;i<numViewList.size();i++){
            numViewList.get(i).setVisibility(View.GONE);
        }

        //创建view
        int size=numStr.length()-numViewList.size();
        for (int m = 0; m < size; m++) {
            ImageView ivNum=new ImageView(this.getContext());
            numViewList.add(ivNum);
            ivNumPanel.addView(ivNum);
        }

        for (int i = 0; i < numStr.length(); i++) {

            int resId = ResourceUtils.getDrawableId(this.getContext(), "gift_dribble_num_" + numStr.substring(i, i + 1));

            Drawable drawable = getResources().getDrawable(resId);

            ImageView iv = numViewList.get(i);
            //ULog.log( "iv:" + iv);
            if (drawable != null && iv != null) {
                iv.setVisibility(VISIBLE);
                iv.setImageDrawable(drawable);
            }
        }
        if(isAnim){
            setLayoutAnimation(getAnimationController());
        }

    }


    protected LayoutAnimationController getAnimationController() {
        LayoutAnimationController controller;
        // AnimationSet set = new AnimationSet(true);
        Animation anim = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);// 从0.5倍放大到1倍
        anim.setDuration(300);
        anim.setInterpolator(new OvershootInterpolator());
        controller = new LayoutAnimationController(anim, 0.1f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }
}