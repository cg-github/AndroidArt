package com.cheng.androidart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by 李国财 on 2018-01-11.
 */

public class HorizontalScrollViewEx extends LinearLayout {
    private final static String TAG = HorizontalScrollViewEx.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian:";

    public HorizontalScrollViewEx(Context context) {
        super(context);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepted = false;
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                //
//                intercepted = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if(needIntercept){
//                    intercepted=true;
//                }else {
//                    intercepted = false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted=false;
//                break;
//        }
//        return intercepted;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,TAG_CHENG+"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,TAG_CHENG+"onTouchEvent");
        return super.onTouchEvent(event);
    }



}
