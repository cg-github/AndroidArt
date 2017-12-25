package com.cheng.androidart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        final Intent intent = new Intent(MainActivity.this,SecondActivity.class);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
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
}
