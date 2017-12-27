package com.talpa.myruler.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author 朱胜军
 * @date 2017/12/27
 * 描述	      TODO
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   TODO
 */

public class Ruler extends View {

    //尺子的最大长度
    private static final float mMaxLength = 10000f;
    //每一刻的长度
    private static final float scaleLength = 20f;
    //长刻度的长度
    private static final float longLength = 100f;
    //短刻度的长度
    private static final float shortLength = 80f;
    //尺子和文字的距离
    private static final float textMarge = 30;

    private Paint mLongPaint, mShortPaint, mTextPaint;
    //手势识别
    private GestureDetector mGestureDetector;


    public Ruler(Context context) {
        this(context, null);
    }

    public Ruler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        initPaint();

        mGestureDetector = new GestureDetector(new simpleGestureListener());

    }

    private void initPaint() {
        mLongPaint = new Paint();
        mLongPaint.setStrokeWidth(5);

        mShortPaint = new Paint();
        mShortPaint.setStrokeWidth(3);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawRuler(canvas);
    }

    private void drawRuler(Canvas canvas) {
        for (int i = 0; i < mMaxLength / scaleLength; i++) {
            if (i % 10 == 0) {
                canvas.drawLine(i * scaleLength, 0, i * scaleLength, longLength, mLongPaint);
                canvas.drawText("" + (i / 10), i * scaleLength, longLength + textMarge, mTextPaint);
            } else {
                canvas.drawLine(i * scaleLength, 0, i * scaleLength, shortLength, mShortPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollBy((int) distanceX,0);
            return true;
        }
    }
}
