package com.cheng.androidart;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.androidart.aidl.Book;
import com.cheng.androidart.aidl.IBookManager;
import com.cheng.androidart.mybinder.AddTest;
import com.cheng.androidart.mybinder.AddTestImpl;
import com.cheng.androidart.view.AnimButton;

import java.util.List;

public class MainActivity extends Activity {




    private final static String TAG = MainActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian:";

   private Button mButton,mButton1;
    private AnimButton mAnimButton;
    private LinearLayout mRemoteContent;
//    private TextView mTv;
    private GestureDetector mGestureDetector;

    private BroadcastReceiver mRemoteViewsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra("remotetest");
            updateUi(remoteViews);
        }
    };

    private void updateUi(RemoteViews remoteViews){
        View view = remoteViews.apply(this,mRemoteContent);
        mRemoteContent.addView(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton1 = findViewById(R.id.button2);
        mAnimButton = findViewById(R.id.anim_button);
        mRemoteContent = findViewById(R.id.remotecontent);

        IntentFilter intentFilter = new IntentFilter("com.cheng.androidart.remotetest");
        registerReceiver(mRemoteViewsReceiver,intentFilter);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notice!")
                .setContentText("Hello World!");
        Intent intent = new Intent(this,SecondActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SecondActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
     //   PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
        mAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

        mAnimButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,TAG_CHENG+"onTouch");
                return false;
            }
        });

        ViewGroup viewGroup = (ViewGroup)(getWindow().getDecorView().findViewById(android.R.id.content));
//        mButton = viewGroup.findViewById(R.id.button);
//
//        mTv = findViewById(R.id.textView);
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

//        mButton.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG,TAG_CHENG+"mButton\n   Left:"+mButton.getLeft()+" Right:"+mButton.getRight()
//                        +" Top:"+mButton.getTop()+" Bottom:"+mButton.getBottom()+" X:"+
//                        mButton.getX()+ " Y:"+mButton.getY()+" translationX:"+mButton.getTranslationX()+" translationY:"+mButton.getTranslationY()
//                        +" Width:"+mButton.getWidth()+" Height:"+mButton.getHeight()
//                );
//            }
//        });
//
//
//        Log.i(TAG_CHENG,TAG+"TouchSlop:"+ ViewConfiguration.get(this).getScaledTouchSlop());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,TAG_CHENG+"BUTTON CLICKED!"+" mScrollX:"+mButton.getScrollX()+" mScrollY:"+mButton.getScrollY());

                mButton.scrollBy(-10,-10);
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        mTv.setText(""+getTaskId()+this);
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
        Log.i(TAG,TAG_CHENG+"onTouchEvent");
        boolean consume = mGestureDetector.onTouchEvent(event);
        return consume;


//        VelocityTracker velocityTracker = VelocityTracker.obtain();
//        velocityTracker.addMovement(event);
//        int pointId1 = -1;
//        int pointId2 = -1;
//
//
//        Log.i(TAG_CHENG,TAG+event.getPointerCount());
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.i(TAG_CHENG,TAG+"ACTION_DOWN");
//                pointId1 = event.getPointerId(0);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                velocityTracker.computeCurrentVelocity(1000);
//                int x = (int)velocityTracker.getXVelocity(pointId1);
//                int y = (int)velocityTracker.getYVelocity(pointId1);
//                Log.i(TAG_CHENG,TAG+"ACTION_MOVE"
//                        +"\n x1:"+x+" y1:"+y);
//                x = (int)velocityTracker.getXVelocity(pointId2);
//                y = (int)velocityTracker.getYVelocity(pointId2);
//                Log.i(TAG_CHENG,TAG+"ACTION_MOVE"
//                        +"\n x2:"+x+" y2:"+y);
//                break;
//
//            case MotionEvent.ACTION_UP:
//                Log.i(TAG_CHENG,TAG+"ACTION_UP");
//                velocityTracker.clear();
//                velocityTracker.recycle();
//                break;
//        }
//       return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,TAG_CHENG+"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
