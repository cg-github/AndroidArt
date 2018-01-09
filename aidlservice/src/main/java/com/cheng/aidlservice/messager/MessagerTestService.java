package com.cheng.aidlservice.messager;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cheng.aidlservice.MyConstants;

/**
 * Created by 李国财 on 2018-01-04.
 */

public class MessagerTestService extends Service {

    private static final String TAG_CHENG ="daxian";
    private static final String TAG = MessagerTestService.class.getSimpleName();

    @SuppressLint("HandlerLeak")
    private Handler mMessageHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i(TAG,TAG_CHENG+msg.getData().getString("toServer"));
                    Messenger messenger = msg.replyTo;
                    Message message =Message.obtain(null,MyConstants.MSG_FROM_SERVER);
                    Bundle data = new Bundle();
                    data.putString("toClient","Hi,fuck you!");
                    message.setData(data);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(mMessageHandler);
        return messenger.getBinder();
    }
}
