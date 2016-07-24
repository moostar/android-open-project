/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 大礼物动画视图
 * Created by Zhao on 16/5/20.
 */
public class GiftAnimView extends RelativeLayout {
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override
    public int getBaseline() {
        return super.getBaseline();
    }

    @Override
    public int getGravity() {
        return super.getGravity();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public GiftAnimView(Context context) {
        super(context);
    }

    public GiftAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GiftAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GiftAnimView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
    }

    @Override
    public void setHorizontalGravity(int horizontalGravity) {
        super.setHorizontalGravity(horizontalGravity);
    }

    @Override
    public void setIgnoreGravity(int viewId) {
        super.setIgnoreGravity(viewId);
    }

    @Override
    public void setVerticalGravity(int verticalGravity) {
        super.setVerticalGravity(verticalGravity);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return super.shouldDelayChildPressedState();
    }
//    private Context context;
//    private GiftAnimConfig config;
//
//    private int id;
//
//    private int[] location;
//    /**
//     * 存在时间最长的view id
//     */
//    private LinkedHashSet<Long> idsLongestTime;
//
//    /**
//     * 上次存在时间最久的时间
//     */
//    private long lastTime;
//
//
//    ArrayList<AnimatorSet> animatorSets;
//    Vector<Animator> animatorKeep = new Vector<>();
//
//
//    public GiftAnimView(Context context) {
//        super(context);
//        this.context = context;
//        initView();
//    }
//
//    public GiftAnimView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initView();
//    }
//
//    public GiftAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        initView();
//    }
//
//    public GiftAnimView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.context = context;
//        initView();
//    }
//
//
//    private void initView() {
//
//        reset();
////        RelativeLayout.LayoutParams layoutParams
////                = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
////                , ViewGroup.LayoutParams.MATCH_PARENT);
////
////        this.addView(relativeLayout, layoutParams);
//    }
//
//
//    public void startAnim(Context context, String path, GiftAnimConfig config) {
//        this.context = context;
//        this.config = config;
//
//        if (location == null) {
//            location = new int[2];
//            getLocationInWindow(location); //获取在当前窗口内的绝对坐标
//        }
//
//        if (isPlaying()) {
//            ULog.log("Zhao isPlaying isPlaying");
//            return;
//        }
//
//        reset();
//
//        setParent(config);
//
//        if (!ArrayUtils.isNullOrEmpty(config.getItems())) {
//            for (final GiftAnimConfig.AnimItem item : config.getItems()) {
//                setItems(path, item);
//            }
//        }
//
//
//        ULog.log("Zhao animatorSets.size():" + animatorSets.size());
//
//
//        start();
//
//    }
//
//
//    private boolean isPlaying() {
//        if (!ListUtils.isNullOrEmpty(animatorSets)) {
//            return true;
//        }
//        return false;
//    }
//
//
//    private void start() {
//
//        location = new int[2];
//        getLocationOnScreen(location); //获取在当前窗口内的绝对坐标
//
//        ULog.log("Zhao getWidth():" + getWidth() + ",getHeight():" + getHeight());
//        ULog.log("Zhao locationX:" + location[0] + ",locationY :" + location[1]);
//
////        setBackgroundColor(getResources().getColor(R.color.app_bg3));
//        setVisibility(VISIBLE);
//        bringToFront();
//
//        if (!ListUtils.isNullOrEmpty(animatorSets)) {
//            for (AnimatorSet set : animatorSets) {
//                set.start();
//            }
//        }
//
//        invalidate();
//    }
//
//    /**
//     * 重置
//     */
//    private synchronized void reset() {
//        setVisibility(GONE);
//        removeAllViews();
//
////        if (!ListUtils.isNullOrEmpty(animatorKeep)) {
////            for (Animator animator : animatorKeep) {
////                animator.removeAllListeners();
////                animator.cancel();
////            }
////        }
//
//        if (!ListUtils.isNullOrEmpty(animatorSets)) {
//            for (AnimatorSet set : animatorSets) {
//                if (set != null) {
//                    set.removeAllListeners();
//                    set.cancel();
//                }
//            }
//        }
//
//
//        animatorSets = new ArrayList<AnimatorSet>();
//
//        animatorKeep = new Vector<>();
//
//        id = 3000;
//        idsLongestTime = new LinkedHashSet<>();
//        lastTime = 0;
//
////            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////            layoutParams.setMargins(0, 0, 0, 0);
////            setLayoutParams(layoutParams);
//        invalidate();
//
////        if (getParent() != null) {
////            RelativeLayout relativeLayout = ((RelativeLayout) getParent());
////            relativeLayout.removeView(this);
////            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////            layoutParams.setMargins(location[0], location[1], 0, 0);
////            setLayoutParams(layoutParams);
////
////            relativeLayout.addView(this);
////        }
//    }
//
//    /**
//     * 设置子控件（容器内的item）的动画
//     *
//     * @param path
//     * @param item
//     * @return
//     */
//    private void setItems(String path, GiftAnimConfig.AnimItem item) {
//        AnimatorSet itemSet = new AnimatorSet();
//
//
//        Vector<Animator> animators = new Vector<Animator>();
//        ++id;
//
//        float itemLayoutWidth = (item.getWidth());
//        float itemLayoutHeight = (item.getHeight());
//
//
//        ImageView imageView = new ImageView(context);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) itemLayoutWidth, (int) itemLayoutHeight);
////        lp.addRule(ALIGN_PARENT_LEFT,1);
//        lp.setMargins((int) item.getOriginX(), (int) item.getOriginY(), 0, 0);
//        imageView.setLayoutParams(lp);
//        imageView.setId(id);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        String realPath = path + "/" + item.getFileUrl();
////        ULog.log("Zhao id:" + id + " " + item.getType() + " realPath:" + realPath);
//        long delay = (long) (item.getBeginTime());
//        long duration = (long) (item.getDuration());
//
//        itemSet.setStartDelay(delay);
//        itemSet.setDuration(duration);
//
//
//        GiftAnimConfig.Anim[] animsOfItem = item.getAnimations();
//        if (ArrayUtils.isNullOrEmpty(animsOfItem)) {
//            imageView.setVisibility(VISIBLE);
//        } else {
//            imageView.setVisibility(INVISIBLE);
//        }
//
//        Vector<Animator> animatorsOfItem = null;
//        switch (item.getType()) {
//            case GiftAnimConfig.ANIM_FILE_TYPE_GIF:
//                setGif(imageView, realPath, item);
//                break;
//
//            case GiftAnimConfig.ANIM_FILE_TYPE_IMAGE:
//                setImage(imageView, realPath, item);
//                break;
//
//            case GiftAnimConfig.ANIM_FILE_TYPE_IMAGE_ARRAY://类gif，此时才会有repeatCount
//                setImageArray(imageView, path, item);
//                break;
//        }
//
//        animatorsOfItem
//                = getAnimators(imageView, itemLayoutWidth, itemLayoutHeight, animsOfItem, item);
//
//        if (!ListUtils.isNullOrEmpty(animatorsOfItem)) {
//            animators.addAll(animatorsOfItem);
//        }
//
//        itemSet.playTogether(animators);
//        itemSet.addListener(new AnimatorSetListener(imageView, item));
//
//        addView(imageView);
//
//        animatorSets.add(itemSet);
//
//        animatorKeep.addAll(animators);
//
//    }
//
//
//    /**
//     * 设置父控件（容器）的动画
//     *
//     * @param config
//     * @return
//     */
//    private void setParent(GiftAnimConfig config) {
//        AnimatorSet parentSet = new AnimatorSet();
//
//        float layoutWidth = (config.getWidth());
//        float layoutHeight = (config.getHeight());
//        ULog.log("Zhao sw:" + LogicCommonUtility.getScreenWidth() + " sh:" + LogicCommonUtility.getScreenHeight());
//
//        ULog.log("Zhao layoutWidth:" + layoutWidth + " layoutHeight:" + layoutHeight);
//
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) layoutWidth, (int) layoutHeight);
//        layoutParams.setMargins((int) config.getOriginX(), (int) config.getOriginY(), 0, 0);
//        setLayoutParams(layoutParams);
//
//
//        GiftAnimConfig.Anim[] anims = config.getAnimations();
//
//        Vector<Animator> animators = getAnimators(this, config.getWidth(), config.getHeight(), anims, null);
//        Vector<Animator> reset = getResetPositionAnimators(this, config.getOriginX(), config.getOriginY());
//
//        if (ListUtils.isNullOrEmpty(animators)) {
//            animators = new Vector<Animator>();
//        }
//
//        reset.addAll(animators);
//        parentSet.playTogether(reset);
//
//        parentSet.addListener(new AnimatorSetListener(this, null));
//
//
//        animatorSets.add(parentSet);
//
//
//        invalidate();
//
//        animatorKeep.addAll(animators);
//
//
//    }
//
//    /**
//     * 设置时间最长的item展示时间
//     */
//    private void setIdLongestTime(View view, long delay, long duration) {
//
//        long thisTime = duration + delay;
//        int idLongestTime = -1;
//        if (thisTime >= lastTime) {
//            idLongestTime = view.getId();
//        }
//        idsLongestTime.add((long) idLongestTime);
//
//        lastTime = duration + delay;
//    }
//
//
//    /**
//     * 设置动画
//     *
//     * @param view
//     * @param width  控件宽度
//     * @param height 控件高度
//     * @param anims
//     * @return
//     */
//    private Vector<Animator> getAnimators(final View view, float width, float height, GiftAnimConfig.Anim[] anims, GiftAnimConfig.AnimItem item) {
//
//        Vector<Animator> animators = new Vector<Animator>();
//        ObjectAnimator objectAnimator = null;
//        if (ArrayUtils.isNullOrEmpty(anims)) {
//            //如果没动画效果做一个假动画
//            objectAnimator = getOneObjectAnimator(view, item);
//            animators.add(objectAnimator);
//        } else {
//            for (int i = 0; i < anims.length; i++) {
//
//                final GiftAnimConfig.Anim anim = anims[i];
//                GiftAnimConfig.Move start = anim.getFromValue();
//                GiftAnimConfig.Move end = anim.getToValue();
//
//                PropertyValuesHolder p1;
//                PropertyValuesHolder p2;
//                if (start != null && end != null) {
//                    switch (anim.getKeyPath()) {
//                        case GiftAnimConfig.ANIM_PATH_TYPE_ALPHA:
//                            objectAnimator = ObjectAnimator.ofFloat(view, "alpha", start.getOpacity(), end.getOpacity());
//                            break;
//
//                        case GiftAnimConfig.ANIM_PATH_TYPE_TRANSLATE:
////                        ULog.log("Zhao NIM_PATH_TYPE_TRANSLATE getCenterX:" + start.getCenterX() + " width:" + width);
////
//                            float startX = (float) start.getCenterX() - (float) MathExtend.divide(width, 2);
//                            float endX = (float) end.getCenterX() - (float) MathExtend.divide(width, 2);
//                            float startY = (float) start.getCenterY() - (float) MathExtend.divide(height, 2);
//                            float endY = (float) end.getCenterY() - (float) MathExtend.divide(height, 2);
//                            ULog.log("Zhao NIM_PATH_TYPE_TRANSLATE startX:" + startX + " startY:" + startY);
//                            ULog.log("Zhao NIM_PATH_TYPE_TRANSLATE endX:" + endX + " endY:" + endY);
//
////                            p1 = PropertyValuesHolder.ofFloat("translationX",  endX-startX);
////                            p2 = PropertyValuesHolder.ofFloat("translationY",  endY -startY);
//
//                            p1 = PropertyValuesHolder.ofFloat("x", startX, endX);
//                            p2 = PropertyValuesHolder.ofFloat("y", startY, endY);
//                            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, p1, p2);
//                            break;
//
//                        case GiftAnimConfig.ANIM_PATH_TYPE_SCALE:
//                            p1 = PropertyValuesHolder.ofFloat("scaleX", start.getScale(), end.getScale());
//                            p2 = PropertyValuesHolder.ofFloat("scaleY", start.getScale(), end.getScale());
//                            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, p1, p2);
////                        objectAnimator = ObjectAnimator.ofFloat(view, "scale", start.getScale(), end.getScale());
//
//                            break;
//
//                        case GiftAnimConfig.ANIM_PATH_TYPE_ROTATE:
//                            p1 = PropertyValuesHolder.ofFloat("rotationX", start.getRotation(), end.getRotation());
//                            p2 = PropertyValuesHolder.ofFloat("rotationY", start.getRotation(), end.getRotation());
//                            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, p1, p2);
////                        objectAnimator = ObjectAnimator.ofFloat(view, "scale", start.getScale(), end.getScale());
//
//                            break;
//
//                    }
//                    if (item == null) {//容器本身的子动画
//                        objectAnimator = setParentAnimator(objectAnimator, view, anim);
//                    } else {//item的子动画
//                        objectAnimator = setItemObjectAnimator(objectAnimator, view, item, i);
//                    }
//
//                }
//                animators.add(objectAnimator);
//
//            }
//        }
//        return animators;
//
//    }
//
//    /**
//     * 设置容器的动画
//     *
//     * @param objectAnimator
//     * @param view
//     * @param anim
//     * @return
//     */
//    private ObjectAnimator setParentAnimator(ObjectAnimator objectAnimator, View view, GiftAnimConfig.Anim anim) {
//        if (anim != null) {
//            /**
//             * 设置动画速率
//             */
//            Interpolator interpolator = anim.getInterpolator();
//            if (interpolator != null) {
//                ULog.log("Zhao setParentAnimator setInterpolator:" + interpolator);
//                objectAnimator.setInterpolator(interpolator);
//            }
//
//
//            long durationItem = (long) (anim.getDuration());
//            long delayItem = (long) (anim.getBeginTime());
//
//            if (anim.isAutoReverses()) {
//                objectAnimator.setRepeatMode(Animation.REVERSE);
//            }
//
//            objectAnimator.setDuration(durationItem);
//            objectAnimator.setStartDelay(delayItem);
//            setIdLongestTime(view, delayItem, durationItem);
//        } else {
////            objectAnimator.setRepeatMode(Animation.REVERSE);
//
//            long durationItem = (long) (config.getDuration());
//            long delayItem = (long) (config.getBeginTime());
//
//
//            objectAnimator.setDuration(durationItem);
//            objectAnimator.setStartDelay(delayItem);
//            setIdLongestTime(view, delayItem, durationItem);
//        }
//
//
//        objectAnimator.addListener(new ItemAnimatorListener(view, null, -1));
//
//
//        return objectAnimator;
//    }
//
//    private ObjectAnimator setItemObjectAnimator(ObjectAnimator objectAnimator, View view, GiftAnimConfig.AnimItem item, int position) {
//        int repeatCount = -1;
//
//        if (item == null) {//容器
//            objectAnimator = setParentAnimator(objectAnimator, view, null);
//            return objectAnimator;
//        }
//
//        if (position >= 0) {//说明item有子动画
//
//            GiftAnimConfig.Anim anim = item.getAnimations()[position];
//
//            long durationItem = (long) (anim.getDuration());
//            long delayItem = (long) (item.getBeginTime() + anim.getBeginTime());//item的子动画延时时间为item的开始时间加上子动画的开始时间
//
//
//            objectAnimator.setDuration(durationItem);
//            objectAnimator.setStartDelay(delayItem);
//
//            repeatCount = item.getRepeatCount();
//
//            if (repeatCount == 0)
//                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//            else
//                objectAnimator.setRepeatCount(repeatCount);
//
//
//            setIdLongestTime(view, delayItem, durationItem);
//
//            /**
//             * 设置动画速率
//             */
//            Interpolator interpolator = anim.getInterpolator();
//            if (interpolator != null) {
//                ULog.log("Zhao item setInterpolator:" + interpolator);
//                objectAnimator.setInterpolator(interpolator);
//            }
//
//            if (anim.isAutoReverses()) {
//                objectAnimator.setRepeatMode(Animation.REVERSE);
//            }
//
//            objectAnimator.addListener(new ItemAnimatorListener(view, item, position));
//        } else {//item没有子动画，动画属性用item的
//            long duration = 0;
//            switch (item.getType()) {
//                case GiftAnimConfig.ANIM_FILE_TYPE_IMAGE_ARRAY://类gif，用repeatCount*每次播放所需时间算出总时间
//                    duration = (long) (item.getDuration() * item.getRepeatCount());
//
//                    ULog.log("Zhao item ANIM_FILE_TYPE_IMAGE_ARRAY duration:" + duration);
//
//                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//
//                    break;
//                default:
//                    duration = (long) (item.getDuration());
//
//                    repeatCount = item.getRepeatCount();
//                    if (repeatCount == 0) {
//                        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//                    } else {
//                        objectAnimator.setRepeatCount(repeatCount);
//                    }
//                    break;
//            }
//
//            long delay = (long) (item.getBeginTime());
//
//            objectAnimator.setDuration(duration);
//            objectAnimator.setStartDelay(delay);
//
//
//            setIdLongestTime(view, delay, duration);
//
//
////            ULog.log("Zhao repeatCount:" + repeatCount + " delay:" + delay + " duration:" + duration);
//
//            objectAnimator.addListener(new ItemAnimatorListener(view, item, position));
//        }
//
//
//        return objectAnimator;
//    }
//
//
//    /**
//     * 重置位置
//     *
//     * @param view
//     * @param endX
//     * @param endY
//     * @return
//     */
//    private Vector<Animator> getResetPositionAnimators(View view, float endX, float endY) {
//
//        Vector<Animator> animators = new Vector<Animator>();
//
//        PropertyValuesHolder p1;
//        PropertyValuesHolder p2;
//        p1 = PropertyValuesHolder.ofFloat("x", 0, endX);
//        p2 = PropertyValuesHolder.ofFloat("y", 0, endY);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, p1, p2);
//        objectAnimator.setDuration(1);
//        objectAnimator.setStartDelay(0);
//        animators.add(objectAnimator);
//        return animators;
//
//    }
//
//    /**
//     * 得到一个ObjectAnimator
//     *
//     * @param view
//     * @param item
//     * @return
//     */
//    private ObjectAnimator getOneObjectAnimator(View view, GiftAnimConfig.AnimItem item) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f);
//        objectAnimator = setItemObjectAnimator(objectAnimator, view, item, -1);
//        return objectAnimator;
//    }
//
//    /**
//     * 设置bitmap
//     *
//     * @param imageView
//     * @param realPath  真实的路径
//     * @param item
//     */
//    private void setImage(ImageView imageView, String realPath, final GiftAnimConfig.AnimItem item) {
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(realPath);
//        imageView.setImageDrawable(bitmapDrawable);
//    }
//
//
//    /**
//     * 设置bitmap
//     *
//     * @param imageView
//     * @param path      路径，不包含文件名
//     * @param item
//     */
//    private void setImageArray(ImageView imageView, String path, final GiftAnimConfig.AnimItem item) {
//        String[] fileUrls = item.getFileUrls();
//        AnimationDrawable animationDrawable = null;
//
//        if (!ArrayUtils.isNullOrEmpty(fileUrls)) {
//            animationDrawable = new AnimationDrawable();
//            for (String fileUrl : fileUrls) {
//                String realPath = path + "/" + fileUrl;
//                ULog.log("Zhao AnimatorSetListener realPath:" + realPath + item.getDuration());
//
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(realPath);
//                animationDrawable.addFrame(bitmapDrawable, (int) item.getDuration());
//            }
//        }
//
//        if (animationDrawable != null) {
//            imageView.setImageDrawable(animationDrawable);
//            animationDrawable.start();
//        }
//    }
//
//
//    /**
//     * 设置gif
//     *
//     * @param imageView
//     * @param realPath  真实的路径
//     * @param item
//     */
//    private void setGif(final ImageView imageView, String realPath, final GiftAnimConfig.AnimItem item) {
//        File file = new File(realPath);
//        final GifDrawable gifDrawable = UPlusUtils.getGifDrawable(file);
//
//        if (gifDrawable != null) {
//            ULog.log("Zhao gifDrawable is:" + gifDrawable);
//            final int repeatCount = item.getRepeatCount();
////            final int repeatCount = 3;
//            if (repeatCount > 0) {
//                gifDrawable.setLoopCount(repeatCount);
//                gifDrawable.addAnimationListener(new AnimationListener() {
//                    @Override
//                    public void onAnimationCompleted(int i) {
//                        ULog.log("Zhao gifDrawable i:" + i);
//                        if (i >= repeatCount - 1) {
//                            gifDrawable.stop();
//                            if (item.isRemovedOnCompletion()) {
//                                imageView.setVisibility(INVISIBLE);
//
//                                if (idsLongestTime.contains((long) imageView.getId())) {
//                                    goneView();
//                                }
//                            }
//                        }
//                    }
//                });
//            } else {
//                gifDrawable.setLoopCount(65535);
//            }
//            gifDrawable.start();
//            imageView.setImageDrawable(gifDrawable);
//        }
//    }
//
//    /**
//     * item内的子动画的动画监听，但IMAGE_ARRAY类型的item也用这个来搞
//     */
//    public class ItemAnimatorListener implements Animator.AnimatorListener {
//
//        private View view;
//        private GiftAnimConfig.Anim anim;
//        private GiftAnimConfig.AnimItem item;
//        private int repeatCount;
//        private int repeatTimes = 0;
//
//        public ItemAnimatorListener(View view, GiftAnimConfig.AnimItem item, int position) {
////            ULog.log("Zhao ItemAnimatorListener create view.getId():" + view.getId());
//            this.view = view;
//            this.item = item;
//            if (item != null && position >= 0) {
//                this.anim = item.getAnimations()[position];
//                this.repeatCount = item.getRepeatCount();
//            }
//            this.repeatTimes = 0;
//
//        }
//
//        @Override
//        public void onAnimationStart(Animator animation) {
////            ULog.log("Zhao ItemAnimatorListener onAnimationStart");
//            view.setVisibility(VISIBLE);
//        }
//
//        @Override
//        public void onAnimationEnd(Animator animation) {
////            ULog.log("Zhao ItemAnimatorListener  onAnimationEnd view.getId():" + view.getId()
////                    + " repeatCount:" + repeatCount + " repeatTimes:" + repeatTimes);
//
//            if (item == null) {
//                //容器消失
//                view.setVisibility(INVISIBLE);
////                animation.removeAllListeners();
////                animation.end();
//            } else if (anim != null && anim.isRemovedOnCompletion()) {
//                //item每个子动画执行完毕，如果设置为隐藏就隐藏掉
//                view.setVisibility(INVISIBLE);
////                animation.removeAllListeners();
////                animation.end();
//            } else if (item != null && item.isRemovedOnCompletion()) {
//                //单个item,没有动画，如果设置为到时间隐藏就隐藏掉
//                view.setVisibility(INVISIBLE);
////                animation.removeAllListeners();
////                animation.end();
//            }
//
////            switch (item.getType()) {
////                case GiftAnimConfig.ANIM_FILE_TYPE_IMAGE_ARRAY://类gif，此时才会有repeatCount
////                    repeatTimes++;
////                    if (item != null && repeatTimes > repeatCount) {
////                        if (item.isRemovedOnCompletion()) {
////                            view.setVisibility(INVISIBLE);
////                        }
////                    }
////                    break;
////                default:
////                    if (anim != null && anim.isRemovedOnCompletion()) {
////                        view.setVisibility(INVISIBLE);
////                    } else if (item != null && item.isRemovedOnCompletion()) {
////                        view.setVisibility(INVISIBLE);
////                    }
////                    break;
////            }
//
//
//        }
//
//        @Override
//        public void onAnimationCancel(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationRepeat(Animator animation) {
//
//        }
//    }
//
//
//    /**
//     * 组动画的监听，除IMAGE_ARRAY，其他类型的item都是组动画
//     */
//    public class AnimatorSetListener implements Animator.AnimatorListener {
//        private View view;
//
//        private GiftAnimConfig.AnimItem item;
//
//        public AnimatorSetListener(View view, GiftAnimConfig.AnimItem item) {
//            ULog.log("Zhao AnimatorSetListener create");
//            this.view = view;
//            this.item = item;
//        }
//
//        @Override
//        public void onAnimationStart(Animator animation) {
//            ULog.log("Zhao AnimatorSetListener onAnimationStart");
//            //TODO 到底有没有item的延时和执行时间来控制显示还不确定，现在做法是子动画做动画的时候展示子动画
////            view.setVisibility(VISIBLE);
//        }
//
//        @Override
//        public void onAnimationEnd(Animator animation) {
//            ULog.log("Zhao AnimatorSetListener onAnimationEnd view.getId():" + view.getId()
//                    + " idsLongestTime.size():" + idsLongestTime.size());
////            ULog.log("Zhao AnimatorSetListener onAnimationEnd repeatTimes:" + repeatTimes + " repeatCount:" + repeatCount);
//
//            if (idsLongestTime.contains((long) view.getId())) {
//                goneView();
//            }
//
//        }
//
//        @Override
//        public void onAnimationCancel(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationRepeat(Animator animation) {
//
//        }
//    }
//
//    /**
//     * gone GiftAnimView视图
//     */
//    private void goneView() {
//        ULog.log("Zhao GONE GONE GONE GONE");
//
//        reset();
//    }

}
