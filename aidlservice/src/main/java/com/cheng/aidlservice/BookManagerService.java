package com.cheng.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cheng.androidart.aidl.Book;
import com.cheng.androidart.aidl.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 李国财 on 2018-01-02.
 */

public class BookManagerService extends Service {

    private final static String TAG = BookManagerService.class.getCanonicalName();
    private final static String TAG_CHENG = "daxian";

    private  IBinder mBinder;
    private List<Book> mBookList ;


    @Override
    public void onCreate() {
        super.onCreate();
        mBookList = new CopyOnWriteArrayList<Book>();
        mBookList.add(new Book("The god chen!",99));
        mBookList.add(new Book("Peace and fight!",88));
        Log.i(TAG_CHENG,TAG+"onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG_CHENG,TAG+"onBind");
        return new IBookManager.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                Log.i(TAG_CHENG,TAG+"getBookList");
                return mBookList;
            }

            @Override
            public Book addBook(Book book) throws RemoteException {
                Log.i(TAG_CHENG,TAG+"addBook");
                Log.i(TAG_CHENG,TAG+book);
                if (!mBookList.contains(book)){
                    mBookList.add(book);
                }
                return book;
            }
        };
    }
}
