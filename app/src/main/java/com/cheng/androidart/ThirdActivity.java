package com.cheng.androidart;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by 李国财 on 2018-01-30.
 */

public class ThirdActivity extends AppCompatActivity {
    private final static String TAG = ThirdActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";
    private ImageView mImge;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_third);
        mImge = findViewById(R.id.imageView2);
        mImge.setImageResource(R.drawable.shape_test);
    }
}
