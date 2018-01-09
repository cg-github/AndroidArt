package com.cheng.androidart.mybinder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by 李国财 on 2018-01-03.
 */

public abstract class AddTestImpl extends Binder implements AddTest {
    public AddTestImpl(){
        this.attachInterface(this,AddTest.DESCRIPTOR);
    }

    public IBinder asBinder(){
        return this;
    }

    public static AddTest asInterface(IBinder binder){
        if (binder == null){
            return null;
        }
        IInterface iin = binder.queryLocalInterface(AddTest.DESCRIPTOR);
        if (iin!=null && iin instanceof AddTest){
            return (AddTest) iin;
        }else {
            return new Proxy(binder);
        }

    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
            {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case AddTest.TRANSACTION_add:
            {
                data.enforceInterface(DESCRIPTOR);
                int x = data.readInt();
                int y = data.readInt();
                reply.writeNoException();
                reply.writeInt(this.add(x,y));
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements AddTest{
        private IBinder mRemote;

        Proxy(IBinder binder){
            mRemote = binder;
        }


        @Override
        public int add(int x, int y) throws RemoteException {
            Parcel date = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            int result=0;

            date.writeInterfaceToken(AddTest.DESCRIPTOR);
            date.writeInt(x);
            date.writeInt(y);

            try {
                mRemote.transact(AddTest.TRANSACTION_add,
                            date,
                            reply,
                            0
                        );
                reply.readException();
                result = reply.readInt();
            } finally {
                date.recycle();
                reply.recycle();
            }

            return result;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }
    }
}
