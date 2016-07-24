package cn.dpocket.moplusand.uinew.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zq on 15/12/10.
 */
public class ImageViewEx extends ImageView {
    public Object getExTag() {
        return exTag;
    }

    public void setExTag(Object exTag) {
        this.exTag = exTag;
    }

    private Object exTag;
    public ImageViewEx(Context context) {
        super(context);
    }

    public ImageViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
