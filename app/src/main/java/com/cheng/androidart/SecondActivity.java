package com.cheng.androidart;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 李国财 on 2017-12-25.
 */

public class SecondActivity extends Activity {
    private final static String TAG = SecondActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    private Button mButton;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mTv = findViewById(R.id.textView);
        final Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("testKey",8);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.i(TAG_CHENG,TAG+"onNewIntent"+intent.getIntExtra("testKey",0));
    }
}
