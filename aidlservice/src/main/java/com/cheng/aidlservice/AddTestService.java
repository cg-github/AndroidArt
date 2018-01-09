package com.cheng.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.cheng.aidlservice.mybinder.AddTestImpl;

/**
 * Created by 李国财 on 2018-01-03.
 */

public class AddTestService extends Service {
    private IBinder mBinder = new AddTestImpl() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
