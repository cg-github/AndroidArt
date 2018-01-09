package com.cheng.androidart.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Scroller;

import com.cheng.androidart.MainActivity;


/**
 * Created by 李国财 on 2018-01-09.
 */

public class AnimButton extends android.support.v7.widget.AppCompatButton {

    private final static String TAG = AnimButton.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian:";
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;



    public AnimButton(Context context) {

        super(context);
        mLastX = (int) getX();
        mLastY = (int) getY();
        mScroller = new Scroller(context);
    }

    public AnimButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLastX = (int) getX();
        mLastY = (int) getY();
        mScroller = new Scroller(context);

    }

    public AnimButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLastX = (int) getX();
        mLastY = (int) getY();
        mScroller = new Scroller(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-mLastX;
                int deltaY = y-mLastY;
                Log.i(TAG_CHENG,TAG+" x:"+x+" y:"+y+" mLastX:"+mLastX
                        +" mLastY:"+mLastY+" deltaX:"+deltaX+" deltaY:"+deltaY);
                ObjectAnimator.ofFloat(this,"translationX",0,deltaX).setDuration(100).start();
                ObjectAnimator.ofFloat(this,"translationY",deltaY,0).setDuration(100).start();
                break;

        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            Log.i(TAG,TAG_CHENG+" CurrX:"+mScroller.getCurrX()+" CurrY:"+mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        mScroller.startScroll(scrollX,scrollY,deltaX,deltaY,1000);
        invalidate();
    }



}
