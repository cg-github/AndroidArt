package com.cheng.androidart;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by 李国财 on 2018-01-30.
 */

public class ThirdActivity extends Activity {
    private final static String TAG = ThirdActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_third);
    }
}
