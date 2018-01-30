package com.cheng.androidart;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.cheng.androidart.view.AnimButton;

/**
 * Created by 李国财 on 2017-12-25.
 */

public class SecondActivity extends Activity {
    private final static String TAG = SecondActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    private AnimButton mButton;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton = findViewById(R.id.button4);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_appwidget);
        remoteViews.setImageViewResource(R.id.imageView,R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.textView,"Hello fuck!!!");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
                new Intent(this,ThirdActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.imageView,pendingIntent);
        Intent intent = new Intent("com.cheng.androidart.remotetest");
        intent.putExtra("remotetest",remoteViews);
        sendBroadcast(intent);

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.i(TAG_CHENG,TAG+"onNewIntent"+intent.getIntExtra("testKey",0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,TAG_CHENG+"onTouchEvent");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,TAG_CHENG+"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,TAG_CHENG+"ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG,TAG_CHENG+"ACTION_UP");
                break;

        }
        return super.onTouchEvent(event);
    }
}
