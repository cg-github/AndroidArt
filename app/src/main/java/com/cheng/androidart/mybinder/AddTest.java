package com.cheng.androidart.mybinder;

import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by 李国财 on 2018-01-03.
 */

public interface AddTest extends IInterface {
    String DESCRIPTOR = "com.cheng.androidart.mybinder.addtest";
    int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    int add(int x , int y) throws RemoteException;
}
