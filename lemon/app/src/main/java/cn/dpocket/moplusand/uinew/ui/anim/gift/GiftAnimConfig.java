/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zhao on 16/5/20.
 */
public class GiftAnimConfig implements Serializable {

    public static final String ANIM_PATH_TYPE_TRANSLATE = "POSITION";
    public static final String ANIM_PATH_TYPE_SCALE = "SCALE";
    public static final String ANIM_PATH_TYPE_ALPHA = "OPACITY";
    public static final String ANIM_PATH_TYPE_ROTATE = "ROTATION";


    public static final String ANIM_FILE_TYPE_GIF = "GIF";
    public static final String ANIM_FILE_TYPE_IMAGE = "IMAGE";
    public static final String ANIM_FILE_TYPE_IMAGE_ARRAY = "IMAGE_ARRAY";


    public static final String ANIM_INTERPOLATOR_TYPE_LINEAR = "LINEAR"; //匀速
    public static final String ANIM_INTERPOLATOR_TYPE_ACCELERATE = "EASEIN"; //先慢后快
    public static final String ANIM_INTERPOLATOR_TYPE_DECELERATE = "EASEOUT";//先快后慢
    public static final String ANIM_INTERPOLATOR_TYPE_ACCELERATEDECELERATE = "EASEINEASEOUT";//先慢后快再慢

    private static final long serialVersionUID = -7742837941161430259L;


    private String giftId;
    private float originX;
    private float originY;
    private float width;
    private float height;

    private float beginTime;
    private float duration;

    // 容器的动画
    private Anim[] animations;

    //容器内的动画
    private AnimItem[] items;


    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public float getOriginX() {
        return getRealDensityX(originX);
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return getRealDensityY(originY);
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getWidth() {
        return getRealDensityX(width);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return getRealDensityY(height);
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBeginTime() {
        return beginTime * 1000;
    }

    public void setBeginTime(float beginTime) {
        this.beginTime = beginTime;
    }

    public float getDuration() {
        return duration * 1000;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Anim[] getAnimations() {
        return animations;
    }

    public void setAnimations(Anim[] animations) {
        this.animations = animations;
    }


    public AnimItem[] getItems() {
        return items;
    }

    public void setItems(AnimItem[] items) {
        this.items = items;
    }

    public class Anim {
        private String keyPath; //POSITION \ SCALE \ OPACITY \ROTATION
        private Move fromValue;
        private Move toValue;
        private float beginTime;
        private float duration;
        private boolean removedOnCompletion;
        private String timingFunction;

        @SerializedName("autoreverses")
        private boolean autoReverses;



        public String getKeyPath() {
            return keyPath;
        }

        public void setKeyPath(String keyPath) {
            this.keyPath = keyPath;
        }

        public Move getFromValue() {
            return fromValue;
        }

        public void setFromValue(Move fromValue) {
            this.fromValue = fromValue;
        }

        public Move getToValue() {
            return toValue;
        }

        public void setToValue(Move toValue) {
            this.toValue = toValue;
        }

        public float getBeginTime() {
            return beginTime * 1000;
        }

        public void setBeginTime(float beginTime) {
            this.beginTime = beginTime;
        }

        public float getDuration() {
            return duration * 1000;
        }

        public void setDuration(float duration) {
            this.duration = duration;
        }

        public boolean isRemovedOnCompletion() {
            return removedOnCompletion;
        }

        public void setRemovedOnCompletion(boolean removedOnCompletion) {
            this.removedOnCompletion = removedOnCompletion;
        }

        public String getTimingFunction() {
            return timingFunction;
        }

        public void setTimingFunction(String timingFunction) {
            this.timingFunction = timingFunction;
        }

        public boolean isAutoReverses() {
            return autoReverses;
        }

        public void setAutoReverses(boolean autoReverses) {
            this.autoReverses = autoReverses;
        }

        public Interpolator getInterpolator() {
//            if (StringUtils.isEmptyOrNullOrNullStr(timingFunction)) {
//                return null;
//            }

            switch (timingFunction) {
                case ANIM_INTERPOLATOR_TYPE_LINEAR:
                    return new LinearInterpolator();

                case ANIM_INTERPOLATOR_TYPE_ACCELERATE:
                    return new AccelerateInterpolator();

                case ANIM_INTERPOLATOR_TYPE_DECELERATE:
                    return new DecelerateInterpolator();

                case ANIM_INTERPOLATOR_TYPE_ACCELERATEDECELERATE:
                    return new AccelerateDecelerateInterpolator();

                default:
                    return new LinearInterpolator();

            }
        }
    }


    public class Move {
        private float centerX;
        private float centerY;
        private float scale;
        private float opacity;
        private float rotation;


        public float getCenterX() {
            return getRealDensityX(centerX);
        }

        public void setCenterX(float centerX) {
            this.centerX = centerX;
        }

        public float getCenterY() {
            return getRealDensityY(centerY);
        }

        public void setCenterY(float centerY) {
            this.centerY = centerY;
        }

        public float getScale() {
            return scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }

        public float getOpacity() {
            return opacity;
        }

        public void setOpacity(float opacity) {
            this.opacity = opacity;
        }

        public float getRotation() {
            return rotation;
        }

        public void setRotation(float rotation) {
            this.rotation = rotation;
        }
    }


    public class AnimItem {
        /*
        "type": "GIF",
        "fileUrl": "bg.gif",
        "originX": 0,
        "originY": 0,
        "width": 375,
        "height": 275,
        "beginTime": 0,
        "duration": 1.6,
        "repeatCount": 1,
        "removedOnCompletion": true,
        */
        private String type;//IMAGE \ GIF \IMAGE_ARRAY
        private String fileUrl;
        private String[] fileUrls; //当type为IMAGE_ARRAY时使用
        private float originX;
        private float originY;
        private float width;
        private float height;
        private float beginTime;
        private float duration;
        private int repeatCount;
        private boolean removedOnCompletion;
        private Anim[] animations;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String[] getFileUrls() {
            return fileUrls;
        }

        public void setFileUrls(String[] fileUrls) {
            this.fileUrls = fileUrls;
        }

        public float getOriginX() {
            return getRealDensityX(originX);
        }

        public void setOriginX(float originX) {
            this.originX = originX;
        }

        public float getOriginY() {
            return getRealDensityY(originY);
        }

        public void setOriginY(float originY) {
            this.originY = originY;
        }

        public float getWidth() {


            return getRealDensityX(width);
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return getRealDensityY(height);
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getBeginTime() {
            return beginTime * 1000;
        }

        public void setBeginTime(float beginTime) {
            this.beginTime = beginTime;
        }

        public float getDuration() {
            return duration * 1000;
        }

        public void setDuration(float duration) {
            this.duration = duration;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        public void setRepeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
        }

        public boolean isRemovedOnCompletion() {
            return removedOnCompletion;
        }

        public void setRemovedOnCompletion(boolean removedOnCompletion) {
            this.removedOnCompletion = removedOnCompletion;
        }

        public Anim[] getAnimations() {
            return animations;
        }

        public void setAnimations(Anim[] animations) {
            this.animations = animations;
        }
    }


    private static float getRealDensityX(float size) {
//        int w = LogicCommonUtility.getScreenWidth();
////        return DensityUtils.dip2px((float) MathExtend.divide(size, 375) *w);
//        return (float) MathExtend.divide(size, 375) * w;

        return 100;
    }


    private static float getRealDensityY(float size) {
//        int h = LogicCommonUtility.getScreenHeight();
////        return DensityUtils.dip2px((float) MathExtend.divide(size, 667) * h);
//        return (float) MathExtend.divide(size, 667) * h;

        return 100;
    }
}
