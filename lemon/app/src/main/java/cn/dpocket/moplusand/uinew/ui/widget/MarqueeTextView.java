package cn.dpocket.moplusand.uinew.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by joy on 15-11-19.
 */
public class MarqueeTextView extends TextView {

    /**
     * 是否停止滚动
     */
    private boolean mStopMarquee;
    private String mText;
    private float mCoordinateX;
    private float mTextWidth;

    private boolean isMarqueeEnalbe = true;

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setText(String text) {
        this.mText = text;
        mTextWidth = getPaint().measureText(mText);
        int width = getWidth();

        if (mTextWidth > width && isMarqueeEnalbe) {
            if (mHandler.hasMessages(0))
                mHandler.removeMessages(0);
            mHandler.sendEmptyMessageDelayed(0, 1000);
        } else {
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        mStopMarquee = false;
//        if (!StringUtils.isEmpty(mText))
//            mHandler.sendEmptyMessageDelayed(0, 2000);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mStopMarquee = true;
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (!StringUtils.isEmpty(mText))
        {
            Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
            canvas.drawText(mText, mCoordinateX, textBaseY, getPaint());
        }
    }


    public boolean isMarqueeEnalbe() {
        return isMarqueeEnalbe;
    }

    public void setMarqueeEnalbe(boolean enalbe) {
        isMarqueeEnalbe = enalbe;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (Math.abs(mCoordinateX) > (mTextWidth + 50)) {
                        mCoordinateX = 0;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        mCoordinateX -= 1;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, 20);
                        }
                    }

                    break;
                case 1:
                    mCoordinateX = 0;
                    if (mHandler.hasMessages(0))
                        mHandler.removeMessages(0);
                    invalidate();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
