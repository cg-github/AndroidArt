package com.cheng.androidart;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.androidart.aidl.Book;
import com.cheng.androidart.aidl.IBookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    private Button mButton;
    private TextView mTv;
    private IBookManager mBookManager;
    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG_CHENG,TAG+"onServiceConnected");
            mBookManager = IBookManager.Stub.asInterface(iBinder);
            Toast.makeText(getBaseContext(),"Bind service success!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG_CHENG,TAG+"onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mTv = findViewById(R.id.textView);
        final Intent intent = new Intent("com.cheng.aidlservice.BookManagerService");
        bindService(intent,mServiceConn, Service.BIND_AUTO_CREATE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<Book> books = mBookManager.getBookList();
                    Log.i(TAG_CHENG,TAG+books);
                    Book book = new Book("Ring of the King!",78);
                    book = mBookManager.addBook(book);
                    Log.i(TAG_CHENG,TAG+book);
                    books = mBookManager.getBookList();
                    Log.i(TAG_CHENG,TAG+books);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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
}
