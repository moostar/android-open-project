//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.dpocket.moplusand.uinew.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class AspectFrameLayout extends FrameLayout {
    private double a = -1.0D;
    private AspectFrameLayout.SHOW_MODE b;
    private Point c;
    private int d;
    private int e;
    private Rect f;
    private int g;
    private int h;

    public AspectFrameLayout(Context var1) {
        super(var1);
        this.b = AspectFrameLayout.SHOW_MODE.REAL;
        this.d = 0;
        this.e = 0;
        this.f = new Rect();
        this.c = d(var1);
    }

    public AspectFrameLayout(Context var1, AttributeSet var2) {
        super(var1, var2);
        this.b = AspectFrameLayout.SHOW_MODE.REAL;
        this.d = 0;
        this.e = 0;
        this.f = new Rect();
        this.c = d(var1);
    }

    public void a(Point var1) {
        this.c = var1;
    }

    public void setShowMode(AspectFrameLayout.SHOW_MODE var1) {
        this.b = var1;
    }

    public void setAspectRatio(double var1) {
        if(var1 < 0.0D) {
            throw new IllegalArgumentException();
        } else {
            if(this.a != var1) {
                this.a = var1;
                if(this.getHandler() != null) {
                    this.getHandler().post(new Runnable() {
                        public void run() {
                            AspectFrameLayout.this.requestLayout();
                        }
                    });
                }
            }

        }
    }

    protected void onMeasure(int var1, int var2) {
        this.getWindowVisibleDisplayFrame(this.f);
        if(this.e == 0 && this.d == 0) {
            this.e = this.getRootView().getWidth();
            this.d = this.getRootView().getHeight();
        }

        boolean var3 = false;
        int var13;
        if(this.c.x > this.c.y) {
            var13 = this.e > this.d?this.d:this.e;
        } else {
            var13 = this.e < this.d?this.d:this.e;
        }

        int var4 = this.f.bottom - this.f.top;
        if(var13 - var4 > var13 / 4) {
            super.onMeasure(this.g, this.h);
        } else {
            if(this.a > 0.0D) {
                int var5 = MeasureSpec.getSize(var1);
                int var6 = MeasureSpec.getSize(var2);
                int var7 = this.getPaddingLeft() + this.getPaddingRight();
                int var8 = this.getPaddingTop() + this.getPaddingBottom();
                var5 -= var7;
                var6 -= var8;
                double var9 = (double)var5 / (double)var6;
                double var11 = this.a / var9 - 1.0D;
                if(Math.abs(var11) >= 0.01D) {
                    if(this.b == AspectFrameLayout.SHOW_MODE.REAL) {
                        if(var11 > 0.0D) {
                            var6 = (int)((double)var5 / this.a);
                        } else {
                            var5 = (int)((double)var6 * this.a);
                        }

                        var5 += var7;
                        var6 += var8;
                    } else if(this.c.x > this.c.y) {
                        if(var5 == this.c.x) {
                            var6 = this.c.y;
                            var5 = (int)((double)var6 * this.a);
                        } else if(var5 < this.c.x) {
                            var5 = this.c.x;
                            var6 = (int)((double)var5 / this.a);
                        }
                    } else if(var5 == this.c.x) {
                        var5 = this.c.x;
                        var6 = (int)((double)var5 / this.a);
                    } else if(var5 < this.c.x) {
                        var6 = this.c.y;
                        var5 = (int)((double)var6 * this.a);
                    }

                    var1 = MeasureSpec.makeMeasureSpec(var5, MeasureSpec.EXACTLY);
                    var2 = MeasureSpec.makeMeasureSpec(var6, MeasureSpec.EXACTLY);
                }
            }

            this.g = var1;
            this.h = var2;
            super.onMeasure(var1, var2);
        }
    }

    public static enum SHOW_MODE {
        FULL,
        REAL;

        private SHOW_MODE() {
        }
    }

    @TargetApi(13)
    public static Point d(Context var0) {
        Point var1 = new Point();
        WindowManager var2 = (WindowManager)var0.getSystemService(Context.WINDOW_SERVICE);
        Display var3 = var2.getDefaultDisplay();
        var3.getSize(var1);
        return var1;
    }

}
