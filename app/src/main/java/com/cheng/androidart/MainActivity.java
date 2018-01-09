package com.cheng.androidart;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.androidart.aidl.Book;
import com.cheng.androidart.aidl.IBookManager;
import com.cheng.androidart.mybinder.AddTest;
import com.cheng.androidart.mybinder.AddTestImpl;
import com.cheng.androidart.view.AnimButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private final static String TAG = MainActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian:";

    private Button mButton,mButton1;
    private AnimButton mAnimButton;
    private TextView mTv;
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton1 = findViewById(R.id.button2);
        mAnimButton = findViewById(R.id.anim_button);

        mTv = findViewById(R.id.textView);
        mGestureDetector = new GestureDetector(this, new
                GestureDetector.OnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent motionEvent) {
                        Log.i(TAG_CHENG,TAG+"onDown");
                        return false;
                    }

                    @Override
                    public void onShowPress(MotionEvent motionEvent) {
                        Log.i(TAG_CHENG,TAG+"onShowPress");
                    }

                    @Override
                    public boolean onSingleTapUp(MotionEvent motionEvent) {
                        Log.i(TAG_CHENG,TAG+"onSingleTapUp");
//                        ObjectAnimator.ofFloat(mButton,"translationX",0,100)
//                                .setDuration(100).start();
//                        mAnimButton.smoothScrollTo(500,0);
                        final int startX = 0;
                        final int deltaX=100;
                        int count=1;
                        while(count<=30){
                            float fraction = count/30;
                            mAnimButton.scrollTo(startX+(int)(fraction*deltaX),0);
                            SystemClock.sleep(90);
                            count++;
                        }
                        return false;
                    }

                    @Override
                    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                        Log.i(TAG_CHENG,TAG+"onScroll");

                        return false;
                    }

                    @Override
                    public void onLongPress(MotionEvent motionEvent) {

                        Log.i(TAG_CHENG,TAG+"onLongPress");
                    }

                    @Override
                    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                        Log.i(TAG_CHENG,TAG+"onFling");
                        return false;
                    }
                });
//        mGestureDetector.setIsLongpressEnabled(false);

        mButton.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,TAG_CHENG+"mButton\n   Left:"+mButton.getLeft()+" Right:"+mButton.getRight()
                        +" Top:"+mButton.getTop()+" Bottom:"+mButton.getBottom()+" X:"+
                        mButton.getX()+ " Y:"+mButton.getY()+" translationX:"+mButton.getTranslationX()+" translationY:"+mButton.getTranslationY()
                        +" Width:"+mButton.getWidth()+" Height:"+mButton.getHeight()
                );
            }
        });


        Log.i(TAG_CHENG,TAG+"TouchSlop:"+ ViewConfiguration.get(this).getScaledTouchSlop());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mTv.setText(""+getTaskId()+this);
        Log.i(TAG_CHENG,TAG+"onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG_CHENG,TAG+"onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG_CHENG,TAG+"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG_CHENG,TAG+"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG_CHENG,TAG+"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG_CHENG,TAG+"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG_CHENG,TAG+"onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG_CHENG,TAG+"onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consume = mGestureDetector.onTouchEvent(event);
        return consume;


//        VelocityTracker velocityTracker = VelocityTracker.obtain();
//        velocityTracker.addMovement(event);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.i(TAG_CHENG,TAG+"ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                velocityTracker.computeCurrentVelocity(1000);
//                int x = (int)velocityTracker.getXVelocity();
//                int y = (int)velocityTracker.getYVelocity();
//                Log.i(TAG_CHENG,TAG+"ACTION_MOVE"
//                +"\n x:"+x+" y:"+y);
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.i(TAG_CHENG,TAG+"ACTION_UP");
//                velocityTracker.clear();
//                velocityTracker.recycle();
//                break;
//        }
       //return super.onTouchEvent(event);
    }



}
